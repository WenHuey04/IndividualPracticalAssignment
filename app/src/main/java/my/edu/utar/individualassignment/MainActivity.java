package my.edu.utar.individualassignment;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    DefineShapeNum defineShapeNum = new DefineShapeNum();

    ArrayList<Color> arrColor = new ArrayList<>();
    GridView gdvLisColor;
    ColorAdapter adapter;

    TextView tvLevel;
    TextView tvTimer;
    CountDownTimer cdTimer;
    Button btnPause;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        map();
        setUp();
        setClick();

    }

    private void init(){
        pattern();
        adapter = new ColorAdapter(this,0, arrColor);
    }

    private void map(){
        gdvLisColor = findViewById(R.id.gdvLisObj1);
        tvLevel = findViewById(R.id.textLevel);
        tvTimer = findViewById(R.id.textTimer);
        btnPause = findViewById(R.id.btnPause);

    }

    private void setUp(){
        gdvLisColor.setNumColumns(defineShapeNum.count);
        gdvLisColor.setAdapter(adapter);
        tvLevel.setText(""+defineShapeNum.level);
        updateTime();

        btnPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cdTimer.cancel();  // stop the timer
                pauseGame();
            }
        });
    }

    private void setClick(){
        gdvLisColor.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
                CheckColor(arrColor.get(i));

            }
        });

    }

    private void CheckColor(Color o){
        if(o.color.equals(defineShapeNum.OneColor)){
            defineShapeNum.level++;
            pattern();
            upDate();
            defineShapeNum.Run_time = defineShapeNum.Total_time*1000;
            cdTimer.cancel();
            updateTime();

        }
    }

    //set the game pattern (color of view )
    private void pattern(){
        defineShapeNum.setLevel();
        defineShapeNum.randomColor();
        arrColor.clear();
        while (arrColor.size() < defineShapeNum.num){
            arrColor.add(new Color(defineShapeNum.ManyColor));
        }
        Random r = new Random();
        arrColor.get(r.nextInt(arrColor.size())).color = defineShapeNum.OneColor;
    }

    private void upDate(){
        adapter.upDate(arrColor);
        gdvLisColor.setNumColumns(defineShapeNum.count);
        tvLevel.setText(""+defineShapeNum.level);
    }

    // update and count the time(5s per level)
    private void updateTime(){
        cdTimer = new CountDownTimer(defineShapeNum.Run_time,1){

            @Override
            public void onTick(long millisUntilFinished) {
                defineShapeNum.Run_time = (int) millisUntilFinished;
                if(defineShapeNum.Run_time >= 0){
                    int Runtimer = defineShapeNum.Run_time/1000;
                    int RunMilitimer = defineShapeNum.Run_time%1000/10;
                    String times = Runtimer + ":" + RunMilitimer;
                    tvTimer.setText(times);

                }
                else {
                    tvTimer.setText("00:00");
                }

            }

            @Override
            public void onFinish() {
                tvTimer.setText("00:00");
                pause();
            }
        }.start();
    }
    private void pause(){

        //stop the GridView click listener
        gdvLisColor.setOnItemClickListener(null);

        //display the game over activity
        Intent intent = new Intent(this,GameOverActivity.class);
        intent.putExtra("level",defineShapeNum.level);
        startActivity(intent);
        finish();
    }

    private void pauseGame(){
        // stop the GridView click listener
        gdvLisColor.setOnItemClickListener(null);

        // create dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Pause");
        builder.setMessage("What do you want to do?");

        // add resume button
        builder.setPositiveButton("Resume", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // continue the game
                updateTime();
                setClick();
            }
        });

        // add Restart button
        builder.setNegativeButton("Restart", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // restart the game
                defineShapeNum.level = 1;
                pattern();
                upDate();
                defineShapeNum.Run_time = defineShapeNum.Total_time*1000;
                updateTime();
                setClick();
            }
        });

        // add the End Game button
        builder.setNeutralButton("End Game", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                pause();
            }
        });

        // show the dialog
        AlertDialog dialog = builder.create();
        dialog.show();
    }

}
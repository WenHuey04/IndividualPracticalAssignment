package my.edu.utar.individualassignment;


import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;


public class GameOverActivity extends AppCompatActivity {

    TextView tvLevel;
    int level = 0;
    int level2;

    private ArrayList<Integer> reclevels = new ArrayList<Integer>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);
        init();
        map();
        setUp();
        dialog();

    }

    private void init() {

        level = getIntent().getIntExtra("level", 00);
    }

    private void map() {

        tvLevel = findViewById(R.id.textLevel);
    }

    private void setUp() {

        level2 = level - 1;
        tvLevel.setText("" + (level2));
    }


    // restart the game
    public void restart(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    // return to main page
    public void returnMainPage(View view) {
        Intent intent2 = new Intent(this, MainPageActivity.class);
        startActivity(intent2);
        finish();
    }

    // show the leaderboard activity
    public void leaderboard(View view) {

        Intent intent3 = new Intent(this, RankingActivity.class);
        startActivity(intent3);
    }

    private void dialog() {

        readFile("records.txt");


        boolean isGreater = false;

        // check the number is bigger or not
        for (int num : reclevels) {
                if (level2 >= num) {
                    isGreater = true;
                    break;
                }
            }



        // Check the level num is top 25?
        //If the level num is top 25, the display the dialog to let user key in their name
        //and save the data into the database
        if (isGreater) {
            reclevels.add(level2);
            Collections.sort(reclevels, Collections.reverseOrder()); // sort the numbers
            ArrayList<Integer> top25 = new ArrayList<Integer>(reclevels.subList(0, Math.min(25, reclevels.size()))); // 获取前25个数字
            writeFile("records.txt", top25);

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Congratulations on reaching Top 25!");
            builder.setMessage("Please enter your name: ");

            EditText editText = new EditText(this);
            editText.setInputType(InputType.TYPE_CLASS_TEXT);
            builder.setView(editText);

            builder.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    String name = editText.getText().toString();
                    int userlevel = level2; // get the level

                    // Write the name and level score into SQLite database
                    SQLiteDatabase db = new DatabaseHelper(GameOverActivity.this).getWritableDatabase();
                    ContentValues values = new ContentValues();
                    values.put("name", name);
                    values.put("level", level2);
                    db.insert("rank", null, values);
                    db.close();

                }
            });

            // show dialog
            builder.show();
        }

    }

    // read all the numbers from records.txt
    private void readFile(String filename) {
        try {
            File file = new File(getFilesDir(), filename);
            FileReader fileReader;
            if (file.exists()) {
                fileReader = new FileReader(file);
            } else {
                FileWriter fileWriter = new FileWriter(file);
                for (int i = 0; i < 25; i++) {
                    fileWriter.write("1\n");
                }
                fileWriter.close();
                fileReader = new FileReader(file);
            }
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String numline;
            while ((numline = bufferedReader.readLine()) != null) {
                reclevels.add(Integer.parseInt(numline));
            }
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // write the top 25 numbers into records.txt
    private void writeFile(String filename, ArrayList<Integer> numbers) {
        try {
            File file = new File(getFilesDir(), filename);
            FileWriter fileWriter = new FileWriter(file);
            for (int num : numbers) {
                fileWriter.write(Integer.toString(num));
                fileWriter.write("\n");
            }
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
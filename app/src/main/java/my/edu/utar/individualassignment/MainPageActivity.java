package my.edu.utar.individualassignment;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainPageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
    }

    //start the game
    public void startGame(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    //show the leaderboard
    public void leaderboard(View view) {

        Intent intent3 = new Intent(this, RankingActivity.class);
        startActivity(intent3);
    }

    //exit the application
    public void exitGame(View view) {
        finish();
    }


}
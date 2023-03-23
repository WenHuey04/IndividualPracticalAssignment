package my.edu.utar.individualassignment;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.LinkedList;
import java.util.List;

public class RankingActivity extends AppCompatActivity {

    private List<Rank> rankData = null;
    private RankAdapter rankAdapter = null;
    private ListView listView;
    private DatabaseHelper dbHelper;
    private SQLiteDatabase database;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking);
        listView = findViewById(R.id.listView);
        dbHelper = new DatabaseHelper(this);
        database = dbHelper.getWritableDatabase();
        String [] columns = new String[]{"name", "printf('%02d', level) as level"};
        Cursor cursor = database.query("rank",columns,null,null,null,null,"level");
        rankData = new LinkedList<>();
        rankData.add(new Rank("Rank","Username","Level"));

        // display the ranking from 1 to 25

        int i = 1;
        if(i<=25) {
            if (cursor.moveToLast()) {
                do {
                    String id = String.valueOf(i++);
                    @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex("name"));
                    @SuppressLint("Range") String level = cursor.getString(cursor.getColumnIndex("level"));
                    rankData.add(new Rank(id, name, level));
                } while (cursor.moveToPrevious());
            }
        }
        cursor.close();
        rankAdapter = new RankAdapter((LinkedList<Rank>) rankData, this);
        listView.setAdapter(rankAdapter);

    }
}
package my.edu.utar.individualassignment;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "mySQLiteDatabase.db";
    private static final int DATABASE_VERSION = 1;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void  onCreate(SQLiteDatabase database){
        database.execSQL("create table rank(id integer primary key, name varchar(50),level varchar(50))");
        ContentValues conV = new ContentValues();
        conV.put("name","whuey");
        conV.put("level","01");
        database.insert("rank",null,conV);
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion){

    }

}

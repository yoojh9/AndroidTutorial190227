package com.example.a13_sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class TestSQLiteOpenHelper extends SQLiteOpenHelper {

    public TestSQLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE student (id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "name TEXT , age INTEGER, address TEXT);";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS student";
        db.execSQL(sql);

        onCreate(db);
    }
}

package com.example.a13_sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class TestDBHandler {
    TestSQLiteOpenHelper helper;

    public TestDBHandler(Context context) {
        helper = new TestSQLiteOpenHelper(context, "people.db", null, 1);
    }

    public void insert(String name, int age, String address){
       SQLiteDatabase db = helper.getWritableDatabase();
       ContentValues values = new ContentValues();
       values.put("name", name);
       values.put("age", age);
       values.put("address", address);
       db.insert("student", null, values);
    }

    public void delete(String name){
        SQLiteDatabase db = helper.getWritableDatabase();
        db.delete("student", "name = ?", new String[]{name});
        // db.delete("student", "name = ? and address = ?", new String[]{name, address});
    }

    public void update(String name, int newAge){
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("age", newAge);
        db.update("student", values, "name = ?", new String[]{name});
    }

    public String selectAll(){
        String res = "";
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor c = db.query("student", null, null, null, null, null, null);

        while(c.moveToNext()){
            String name = c.getString(c.getColumnIndex("name"));
            int age = c.getInt(c.getColumnIndex("age"));
            String address = c.getString(c.getColumnIndex("address"));

            res += "name : " + name + " , age : " + age + ", address : " + address + "\n";
        }

        return res;
    }
}

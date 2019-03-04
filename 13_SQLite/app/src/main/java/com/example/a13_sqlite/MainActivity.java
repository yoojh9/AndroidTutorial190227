package com.example.a13_sqlite;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TestDBHandler dbHandler = new TestDBHandler(this);
        dbHandler.insert("Jeonghyun", 30, "서울시");
        dbHandler.insert("Yong", 31, "서울시");
        dbHandler.insert("정현", 30, "인천시");

        dbHandler.update("정현", 29);

        String res = dbHandler.selectAll();
        Log.i("SQLite : " , res);
    }
}

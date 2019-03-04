package com.example.a13_sqlite;

import android.content.Context;

public class TestDBHandler {
    TestSQLiteOpenHelper helper;

    public TestDBHandler(Context context) {
        helper = new TestSQLiteOpenHelper(context, "people.db", null, 1);
    }
}

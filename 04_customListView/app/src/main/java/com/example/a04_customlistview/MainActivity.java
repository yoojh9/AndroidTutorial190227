package com.example.a04_customlistview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<MyData> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addMyData();
    }

    private void addMyData(){
        for(int i=0; i<100; i++){
            int iconId = (i%2==0)?R.mipmap.ic_launcher : R.mipmap.ic_launcher_round;
            list.add(new MyData("title"+i, "desc"+i, iconId));
        }
    }
}

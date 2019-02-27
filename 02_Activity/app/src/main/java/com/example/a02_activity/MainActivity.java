package com.example.a02_activity;

import android.content.Intent;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final int MY_REQ_CODE = 1001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnStart = findViewById(R.id.btnStart);
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 액티비티 호출 (명시적 인텐트)
                Intent intent = new Intent(MainActivity.this, MyActivity.class);
                intent.putExtra("myValue", "Hello World");
                intent.putExtra("intValue", 100);
                startActivityForResult(intent, MY_REQ_CODE); // requestCode: request 요청 구분 용
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
       if(requestCode == MY_REQ_CODE && resultCode == RESULT_OK){
           String str = data.getStringExtra("myResult");
           Toast.makeText(MainActivity.this, str, Toast.LENGTH_SHORT).show();
       }
    }
}
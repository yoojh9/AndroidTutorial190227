package com.example.a02_activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        Intent intent = getIntent(); // MainActivity에서 전달한 인텐트를 받아올 수 있음
        String value = intent.getStringExtra("myValue");
        int intValue = intent.getIntExtra("intValue", 0);   // defaultValue 설정 안해주면 오류 남
        Toast.makeText(MyActivity.this, "myValue: " + value + ", intValue: "+ intValue, Toast.LENGTH_SHORT).show();

        Button btnRes = findViewById(R.id.btnRes);
        btnRes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent resIntent = new Intent();
                resIntent.putExtra("myResult", "MY RESULT!");
                setResult(RESULT_OK, resIntent);    // 자기를 호출한 액티비티에 결과값을 보내거나 추가적인 데이터를 보낼 수 있다. 역시 인텐트를 이용해야 함
                finish(); // 종료
            }
        });
    }
}

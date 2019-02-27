package com.example.a06_thread;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final int MY_COUNT = 1;
    Button btnStart;
    ProgressBar progressBar;

    Handler handler = new Handler(){
        // sendMessage() 해놓으면 킵해놓고 있다가 처리할 수 있는 상황이 오면 handleMessage()를 이용해 처리
        @Override
        public void handleMessage(Message msg) {
            if(msg.what == MY_COUNT){
                btnStart.setText("count : " + msg.arg1);
                progressBar.setProgress(msg.arg1);
            }
        }
    };

    // Logical한 코드만 쓰레드에 넣고, UI 조작 관련 코드는 Handler에 넣는 것을 권장함
    class MyThread extends Thread {
        @Override
        public void run() {
            for(int i=0; i<=100; i++){
                Log.d("thread", "count: " + i);

                Message msg = new Message();
                msg.what = MY_COUNT;    // 어떤 카테고리의 메세지인지 정의
                msg.arg1 = i;
                handler.sendMessage(msg); // 핸들러에게 메세지를 보냄

                // btnStart.setText("count : " + i);   // 직접 만든 thread에서는 UI를 바로 접근할 수 없음. 핸들러가 필요
                try {
                    sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnStart = findViewById(R.id.btnStart);
        btnStart.setOnClickListener(this);

        progressBar = findViewById(R.id.progressBar);
    }

    @Override
    public void onClick(View v) {
        Thread myThread = new MyThread();
        myThread.start();
    }
}

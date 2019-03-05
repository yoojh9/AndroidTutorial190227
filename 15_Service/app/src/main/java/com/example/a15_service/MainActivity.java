package com.example.a15_service;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    // 바인드 서비스
    MyBoundService boundService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void btnStartClick(View v){
        Intent intent = new Intent(this, MyService.class);
        startService(intent);
    }

    public void btnStopClick(View v){
        Intent intent = new Intent(this, MyService.class);
        stopService(intent);
    }

    public void btnRandomClick(View v){
        if(boundService != null) {
            int number = boundService.getRandomNumber();
            Log.d("bound service", "random number : " + number);
        }
    }


    ServiceConnection conn = new ServiceConnection() {
        // connect 됐을 때 하고 싶은 것
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            MyBoundService.MyBinder binder = (MyBoundService.MyBinder) service;
            boundService = binder.getService();
        }

        // disconnect 됐을 때 하고 싶은 것
        @Override
        public void onServiceDisconnected(ComponentName name) {
            boundService = null;
        }
    };

    // 화면이 보이면 onBind()
    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = new Intent(this, MyBoundService.class);
        bindService(intent, conn, BIND_AUTO_CREATE);
    }

    // 화면이 안보이면 unBind()
    @Override
    protected void onStop() {
        super.onStop();
        unbindService(conn);
    }
}

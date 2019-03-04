package com.example.a15_service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import java.util.Random;

public class MyBoundService extends Service {
    public MyBoundService() {
    }

    // Binder도 IBinder 구현체임
    // 액티비티에서 연결될 때 Binder를 받을 수 있고, Binder 받을 때 Service 인스턴스를 넘길 수 있음.
    public class MyBinder extends Binder {
        public MyBoundService getService(){
            return MyBoundService.this;
        }
    }

    MyBinder binder = new MyBinder();

    // 액티비티에서 bindService() 호출 할 경우 호출 됨
    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    private Random random = new Random();

    public int getRandomNumber(){
        return random.nextInt(100);
    }

}

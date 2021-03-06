package com.example.a14_mediaplayer;

import android.Manifest;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;

import java.io.IOException;
import java.security.Permissions;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final int PERMISSION_REQ_CODE = 100;

    MediaPlayer mp = null;
    SeekBar seekBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        seekBar = findViewById(R.id.seekBar);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            // 손으로 드래그 했을 때
            // fromUser: 사람이 interaction 한것에만 반응하고 싶을 경우
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(fromUser){
                    if(mp != null){
                        mp.seekTo(progress);
                    }
                }
            }

            // 손을 한번 눌렀을 때
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            // 손을 뗐을 때
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        setupPermission();

        findViewById(R.id.btnPlay).setOnClickListener(this);
        findViewById(R.id.btnPlayFile).setOnClickListener(this);
        findViewById(R.id.btnStop).setOnClickListener(this);
    }


    private String getPath(int id){
        if(id==R.id.btnPlay) {
            return "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4";
        }
        else if(id==R.id.btnPlayFile) {
            // 디바이스 내 모든 앱들이 공통적으로 접근할 수 있는 디렉토리  /storage/emulated/0/Kalimba.mp3 ??
            Log.i("file ", Environment.getExternalStorageDirectory()+"/Kalimba.mp3");

            // 아래 코드로 해야지 되네..
            return "/sdcard/Download/Kalimba.mp3";
        } else {
            return null;
        }
    }

    @Override
    public void onClick(View v) {

        if(v.getId()==R.id.btnPlay || v.getId() == R.id.btnPlayFile){
            mp = new MediaPlayer();
            try {

                mp.setDataSource(getPath(v.getId()));
                mp.prepare();
                // prepare를 하고 나면 mp.getDuration()으로 총 재생시간을 알 수 있음.
                mp.start();

                seekBar.setMax(mp.getDuration());
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        while(mp!=null){
                            // mp.getCurrentPosition() : 현재 재생되고 있는 위치
                            seekBar.setProgress(mp.getCurrentPosition());
                            try {
                                Thread.sleep(100);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }).start();
            } catch(IOException e){
                e.printStackTrace();
                Log.e("IOException", e.getMessage());
            }

        } else if(v.getId()==R.id.btnStop){
            mp.stop();
            mp.release();
            mp = null;
        }
    }

    // Runtime Permission 얻는 과정
    private void setupPermission(){
       int permission = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE);

        // 권한 부여받지 않았을 경우 권한 요청
       if(permission != PackageManager.PERMISSION_GRANTED){

           // 사용자가 퍼미션 거절을 한 적이 있는 경우에는 퍼미션이 필요한 이유를 설명해줘야 한다.
           // 설명 다이얼로그를 띄우고 사용자가 동의 버튼을 클릭 했을 경우에는 다시 퍼미션을 요청한다.
           if(ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_EXTERNAL_STORAGE)){
               AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
               builder.setMessage("Permission to read external storage is required for this app");
               builder.setTitle("Permission required");
               builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                   @Override
                   public void onClick(DialogInterface dialog, int which) {
                       requestReadExternalStoragePermission();
                   }
               });

               Log.i("Permission", "show diagram");
               builder.create().show();
           }
           // 사용자가 퍼미션 거부를 한 적이 없는 경우에는 퍼미션 요청을 바로 한다.
           else {
               requestReadExternalStoragePermission();
           }
        } else {
           Log.i("Permission", "already granted");
       }
    }

    private void requestReadExternalStoragePermission(){
        ActivityCompat.requestPermissions(MainActivity.this,
                new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                PERMISSION_REQ_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == PERMISSION_REQ_CODE){
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Log.i("Permission", "granted");
            } else {
                Log.i("Permission", "not granted");
            }
        }
    }

}

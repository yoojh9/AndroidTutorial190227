package com.example.a16_br;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class BatteryChangedReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if(intent.getAction().equals(Intent.ACTION_BATTERY_CHANGED)) {
            int level = intent.getIntExtra("level", 0);
            Log.d("Battery Level", "battery level : "+level);
            Toast.makeText(context, "Battery Level : "+ level, Toast.LENGTH_SHORT).show();
        }
        if(intent.getAction().equals(Intent.ACTION_BATTERY_LOW)){
            Log.d("Battery Low", "battery Low");
            Toast.makeText(context, "Battery Low", Toast.LENGTH_LONG).show();
        }
        if(intent.getAction().equals("custom_action")){
            Toast.makeText(context, "custom broadcast", Toast.LENGTH_SHORT).show();
        }

    }
}

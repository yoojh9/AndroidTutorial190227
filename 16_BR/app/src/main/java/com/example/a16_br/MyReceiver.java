package com.example.a16_br;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

public class MyReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        String str = "";
        Object[] pdus = (Object[]) intent.getExtras().get("pdus");
        SmsMessage[] msgs = new SmsMessage[pdus.length];


        for(int i=0; i<msgs.length; i++){
            msgs[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);

            str += "SMS from : " + msgs[i].getOriginatingAddress();
            str += " : " + msgs[i].getMessageBody()+"\n";
        }

        Log.d("SMS log", str);
        Toast.makeText(context, str, Toast.LENGTH_LONG).show();
    }
}

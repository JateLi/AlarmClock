package com.example.tengzheli.alarmclock;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by tengzheli on 22/03/18.
 */

public class Alarm_Receiver extends BroadcastReceiver{
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.e("Receiver Works", "hello!!");

        //fetch extra string from the intent
         String get_your_string = intent.getExtras().getString("extra");
        Log.e("what is the key", get_your_string);

        //Create an intent to the ringtone service
        Intent service_intent = new Intent(context,RingtoneService.class);

        //pass the extra string from main to ringtone service
        service_intent.putExtra("extra", get_your_string);


        //Start ringtone service
        context.startService(service_intent);


    }
}

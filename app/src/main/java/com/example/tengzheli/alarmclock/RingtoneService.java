package com.example.tengzheli.alarmclock;


import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by tengzheli on 22/03/18.
 */

public class RingtoneService extends Service{

    MediaPlayer media_song;
    boolean isRunning = false;
    int startId;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {

        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i("LocalService", "Received start id " + startId + ": " + intent);

        //fetch the extra string valuse
        String state = intent.getExtras().getString("extra");
        Log.e("ringtone extra is " , state);
        Log.e("song is  " , String.valueOf(this.isRunning));


        // Convert extra to start ID
        assert state != null;
        switch (state){
            case "on":
                startId = 1;
                break;
            case "off":
                startId = 0;
                Log.e("startId is ", state);
                break;
            default:
                startId = 0;
                break;
        }




        //if the music is playing and user press button
        if(!this.isRunning && startId == 1){
            Log.e("there is no music","start one");
            //instance of media player
            media_song = MediaPlayer.create(this, R.raw.three);
            media_song.start();

            this.isRunning = true;
            this.startId = 0;



            //notification service
            //Set up and intent to main
            Intent intent_main_activity = new Intent(this.getApplicationContext(), MainActivity.class);
            //
            PendingIntent pending_intent_main_activity = PendingIntent.getActivity(this,0,
                    intent_main_activity, 0);

            String NOTIFICATION_CHANNEL_ID = "my_channel_id_01";

            NotificationManager notificationManager = (NotificationManager)
                    getSystemService(Context.NOTIFICATION_SERVICE);

            //notification channel
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                NotificationChannel notificationChannel = new NotificationChannel(NOTIFICATION_CHANNEL_ID, "My Notifications", NotificationManager.IMPORTANCE_MAX);

                // Configure the notification channel.
                notificationChannel.setDescription("Channel description");
                notificationChannel.enableLights(true);
                notificationChannel.setLightColor(Color.RED);
                notificationChannel.setVibrationPattern(new long[]{0, 1000, 500, 1000});
                notificationChannel.enableVibration(true);
                notificationManager.createNotificationChannel(notificationChannel);
            }

          //  make the notification parameters
           NotificationCompat.Builder notification_popup =  new NotificationCompat.Builder(this,
                   NOTIFICATION_CHANNEL_ID);

            Notification mNotification = notification_popup
                    .setContentTitle("An alarm is going off")
                    .setContentText("Click me")
                    .setContentIntent(pending_intent_main_activity)
                    .setAutoCancel(true)
                    .setSmallIcon(R.mipmap.ic_launcher)
                  //  .setLargeIcon(BitmapFactory.decodeResource(context.getResources(), R.mipmap.ic_launcher))
                    .build();

           notificationManager.notify(0,mNotification);


        }else if(this.isRunning && startId == 0){
            Log.e("there is music", "stop it");

            media_song.stop();
            media_song.reset();

            this.isRunning = false;
            this.startId = 0;

        }else if(!this.isRunning && startId == 0){
            Log.e("no music is playing", "stop it");
            this.isRunning = false;
            this.startId = 0;
        }else{
            media_song.stop();
            media_song.reset();
            media_song = MediaPlayer.create(this, R.raw.three);
            media_song.start();

            this.isRunning = true;
            this.startId = 0;
            Log.e("else","nothing");

        }


        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
        // Tell the user we stopped.
      //  Toast.makeText(this, "On Destroy called", Toast.LENGTH_SHORT).show();
        Log.e("on destroy called", "destroy");
        super.onDestroy();
        this.isRunning = false;
    }
}

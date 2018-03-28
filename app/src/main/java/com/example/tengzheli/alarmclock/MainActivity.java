package com.example.tengzheli.alarmclock;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    //to make our alarm manager
    AlarmManager alarm_manager;
    TimePicker alarm_timepicker;
    TextView update_text;
    Context context;
    PendingIntent pending_intent;

    private static final String TAG = "MainActivity";
    private SectionsPageAdapter msection;
private  ViewPager mViewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //What's this?
        this.context = this;
        msection = new SectionsPageAdapter(getSupportFragmentManager());

        mViewPager = (ViewPager)findViewById(R.id.container);
        setupViewPager(mViewPager);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        setupLaunchButton();




        // initialize
    alarm_manager = (AlarmManager) getSystemService(ALARM_SERVICE);
    alarm_timepicker = (TimePicker) findViewById(R.id.timePicker);
    update_text = (TextView) findViewById(R.id.textView);

    //Create an instance of calender
        final Calendar calendar = Calendar.getInstance();
    //Create an intent to Alarm Receiver class
        final Intent my_intent = new Intent(this.context, Alarm_Receiver.class);

        //initialise start & stop button
        Button alarm_on = (Button)findViewById(R.id.alarm_on);
        Button alarm_off = (Button)findViewById(R.id.alarm_off);

        //Create an onClick listener to start
        alarm_on.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calendar.set(Calendar.HOUR_OF_DAY, alarm_timepicker.getHour());
                calendar.set(Calendar.MINUTE, alarm_timepicker.getMinute());

                int hour = alarm_timepicker.getHour();
                int minute = alarm_timepicker.getMinute();

                String hour_Sting = String.valueOf(hour);
                String minute_String = String.valueOf(minute);

                //Convert 24-hour to 12-hour time form
                if(hour > 12){
                    hour_Sting = String.valueOf(hour-12);
                }
                if(minute < 10){
                    minute_String ="0" + String.valueOf(minute);
                }

                set_alarm_text("alarm....on!" + hour_Sting + ":" + minute_String);

                //tell clock "alarm_on" pressed
                my_intent.putExtra("extra", "on");


                pending_intent = PendingIntent.getBroadcast(MainActivity.this,
                        0,my_intent, PendingIntent.FLAG_UPDATE_CURRENT);

                // Set the alarm manager
                alarm_manager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                        pending_intent);


            }
        });

        //Create an onClick listener to stop
        alarm_off.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                set_alarm_text("alarm....off!");

                if(pending_intent != null) {
                    alarm_manager.cancel(pending_intent);
                }
                //put extra to my_intent, "alarm_off" pressed
               my_intent.putExtra("extra", "off");

                //stop ringtone
               sendBroadcast(my_intent);
            }
        });

    }

    private  void setupLaunchButton(){
        Button launchButton = (Button)findViewById(R.id.launchToSecond);
        launchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Clicked to launchSecond",
                        Toast.LENGTH_SHORT).show();

                //Launch the second activity
               // Intent intent = new Intent(MainActivity.this, Main2Activity.class);
               Intent intent = Main2Activity.makeIntent(MainActivity.this);

                startActivity(intent);
                //end mainActivity
                finish();
            }
        });

    }

    private void set_alarm_text(String output) {

        update_text.setText(output);

    }

    private void setupViewPager(ViewPager viewPager){
        SectionsPageAdapter adapter = new SectionsPageAdapter(getSupportFragmentManager());
        adapter.addFragment(new Tag1Fragment(),"TAB1");
        adapter.addFragment(new Tag2Fragment(),"TAB2");
      //  adapter.addFragment(new MainActivity(), "Main");
        viewPager.setAdapter(adapter);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

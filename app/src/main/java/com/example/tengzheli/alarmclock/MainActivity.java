package com.example.tengzheli.alarmclock;

import android.app.AlarmManager;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    //to make our alarm manager
    AlarmManager alarm_manager;
    TimePicker alarm_timepicker;
    TextView update_text;
    Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //What's this?
        this.context = this;

        // initialize
    alarm_manager = (AlarmManager) getSystemService(ALARM_SERVICE);
    alarm_timepicker = (TimePicker) findViewById(R.id.timePicker);
    update_text = (TextView) findViewById(R.id.textView);

    //Create an instance of calender
        final Calendar calendar = Calendar.getInstance();



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

            }
        });

        //Create an onClick listener to stop
        alarm_off.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                set_alarm_text("alarm....off!");
            }
        });

    }

    private void set_alarm_text(String output) {

        update_text.setText(output);

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

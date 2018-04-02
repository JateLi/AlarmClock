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
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {

    //to make our alarm manager
 //   AlarmManager alarm_manager;
    TimePicker alarm_timepicker;
    TextView update_text;
    Context context;
    PendingIntent pending_intent;
    ArrayList<Alarm_Infor> alarm_array;

    AlarmManager[] alarmManagers = new AlarmManager[12];
    PendingIntent[] pendArray = new PendingIntent[12];


    private static final String TAG = "MainActivity";
    private SectionsPageAdapter msection;
    private  ViewPager mViewPager;

    protected void onResume(){
        super.onResume();
     //   this.onCreate(null);
    }

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

       alarm_array = new ArrayList<Alarm_Infor>();


        // initialize
    alarm_timepicker = (TimePicker) findViewById(R.id.timePicker);
    update_text = (TextView) findViewById(R.id.textView);


    setupAlarmOnButton();

    //Create an intent to Alarm Receiver class
        Button alarm_off = (Button)findViewById(R.id.alarm_off);



        //Create an onClick listener to stop
        alarm_off.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                set_alarm_text("alarm....off!");
                 Intent my_intent = new Intent(MainActivity.this, Alarm_Receiver.class);
                if(pending_intent != null) {
                    //alarm_manager.cancel(pending_intent);
                }
                //put extra to my_intent, "alarm_off" pressed
               my_intent.putExtra("extra", "off");

                //stop ringtone
               sendBroadcast(my_intent);
            }
        });

    }

    private void setupAlarmOnButton(){
        //initialise start & stop button
        Button alarm_on = (Button)findViewById(R.id.alarm_on);
        //Create an instance of calender
        final Calendar calendar = Calendar.getInstance();
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



                Log.e("hahah", String.valueOf(alarm_array.size()));

                if(alarm_array.size() == 0){
                    //tell clock "alarm_on" pressed
                    Intent my_intent = new Intent(MainActivity.this, Alarm_Receiver.class);
                    my_intent.putExtra("extra", "on");

                    //request code could be a unique number
                    pending_intent = PendingIntent.getBroadcast(MainActivity.this,
                            0, my_intent, 0);

                    // Set the alarm manager
                    alarmManagers[0] = (AlarmManager)context.getSystemService(ALARM_SERVICE);
                    alarmManagers[0].set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                            pending_intent);

                    pendArray[0] = pending_intent;
                    Alarm_Infor af = new Alarm_Infor(pending_intent, 0, calendar);
                    alarm_array.add(af);

                }else {
                    //tell clock "alarm_on" pressed
                    Intent my_intent = new Intent(MainActivity.this, Alarm_Receiver.class);
                    my_intent.putExtra("extra", "on");

//                    for (int i = 0; i < alarm_array.size(); i++) {
//                        // Set the alarm manager
//                        alarmManagers[i] = (AlarmManager)context.getSystemService(ALARM_SERVICE);
//                        alarmManagers[i].set(AlarmManager.RTC_WAKEUP, alarm_array.get(i).getCalendar().getTimeInMillis(),
//                                alarm_array.get(i).getPending_intent());
//
//                    }

                    pending_intent = PendingIntent.getBroadcast(MainActivity.this,
                            alarm_array.size(), my_intent, 0);

                    // Set the alarm manager
                    alarmManagers[alarm_array.size()] = (AlarmManager)context.getSystemService(ALARM_SERVICE);
                    alarmManagers[alarm_array.size()].set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                            pending_intent);

                    pendArray[alarm_array.size()] = pending_intent;
                    Alarm_Infor af = new Alarm_Infor(pending_intent, alarm_array.size(),calendar);
                    alarm_array.add(af);

                }

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

    private void setupAlarmOffButton(){

    }

    //get time interval to trigger alarm manager
//    private int getTimeInterval(String getInterval) {
//        int interval = Integer.parseInt(getInterval);//convert string interval into integer
//
//        //Return interval on basis of radio button selection
//        if (secondsRadioButton.isChecked())
//            return interval;
//        if (minutesRadioButton.isChecked())
//            return interval * 60;//convert minute into seconds
//        if (hoursRadioButton.isChecked()) return interval * 60 * 60;//convert hours into seconds
//
//        //else return 0
//        return 0;
//    }

    //Trigger alarm manager with entered time interval
//    public void triggerAlarmManager(int alarmTriggerTime) {
//        // get a Calendar object with current time
//        Calendar cal = Calendar.getInstance();
//        // add alarmTriggerTime seconds to the calendar object
//        cal.add(Calendar.SECOND, alarmTriggerTime);
//
//        AlarmManager manager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);//get instance of alarm manager
//        manager.set(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), pendingIntent);//set alarm manager with entered timer by converting into milliseconds
//
//        Toast.makeText(this, "Alarm Set for " + alarmTriggerTime + " seconds.", Toast.LENGTH_SHORT).show();
//    }

    private void save_alarmInfor(){
        /////save button
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

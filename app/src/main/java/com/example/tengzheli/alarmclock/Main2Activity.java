package com.example.tengzheli.alarmclock;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class Main2Activity extends AppCompatActivity {
    private ArrayList<TestInfo> tString = new ArrayList<TestInfo>();
    public static  final  String Shared_Prefs = "sharedPrefs";

    ArrayList<TestInfo> testInfoList;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        this.context = this;

//        loadData();
        FloatingActionButton  fab = (FloatingActionButton)findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Main2Activity.this, "Add a new alarm",
                        Toast.LENGTH_SHORT).show();
            }
        });

        ListView list = (ListView)findViewById(R.id.listiew);

        TestInfo  a = new TestInfo("a", "10:11", "1");
        TestInfo  b = new TestInfo("b", "10:12", "2");
        TestInfo  c = new TestInfo("c", "10:13", "3");
        TestInfo  d = new TestInfo("d", "10:14", "4");
        TestInfo  e = new TestInfo("e", "10:15", "5");
        TestInfo  f = new TestInfo("f", "10:16", "6");

        loadData();
//        testInfoList.add(a);
//        testInfoList.add(b);
//        testInfoList.add(c);
//        testInfoList.add(d);
//        testInfoList.add(e);
//        testInfoList.add(f);
//        testInfoList.add(a);
//        testInfoList.add(b);
//        testInfoList.add(c);
//        testInfoList.add(d);
//        testInfoList.add(e);
//        testInfoList.add(f);

        TestInfoArrayAdapter adapter = new TestInfoArrayAdapter(this, R.layout.list_item_layout,
                tString);
        list.setAdapter(adapter);



        setupEndActivityButton();

    }

    public void loadData(){
        SharedPreferences sharedPreferences =  getSharedPreferences(Shared_Prefs, MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("task_list", null);
        Type type = new TypeToken<ArrayList<TestInfo>>(){}.getType();
        tString = gson.fromJson(json, type);

        Log.e("Load array size", String.valueOf(tString.size()));
    }


private  void setupEndActivityButton(){
    Button launchBack = (Button) findViewById(R.id.launchToFirst);
    launchBack.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(Main2Activity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    });
}



    public static Intent makeIntent(Context context){
        return  new Intent(context, Main2Activity.class);
    }

}



package com.example.tengzheli.alarmclock;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ListView list = (ListView)findViewById(R.id.listiew);

        TestInfo  a = new TestInfo("a", "10:11", "1");
        TestInfo  b = new TestInfo("b", "10:12", "2");
        TestInfo  c = new TestInfo("c", "10:13", "3");
        TestInfo  d = new TestInfo("d", "10:14", "4");
        TestInfo  e = new TestInfo("e", "10:15", "5");
        TestInfo  f = new TestInfo("f", "10:16", "6");



        ArrayList<TestInfo> testInfoList = new ArrayList<>();
        testInfoList.add(a);
        testInfoList.add(b);
        testInfoList.add(c);
        testInfoList.add(d);
        testInfoList.add(e);
        testInfoList.add(f);
        testInfoList.add(a);
        testInfoList.add(b);
        testInfoList.add(c);
        testInfoList.add(d);
        testInfoList.add(e);
        testInfoList.add(f);

        TestInfoArrayAdapter adapter = new TestInfoArrayAdapter(this, R.layout.list_item_layout,
                testInfoList);
        list.setAdapter(adapter);



        setupEndActivityButton();

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



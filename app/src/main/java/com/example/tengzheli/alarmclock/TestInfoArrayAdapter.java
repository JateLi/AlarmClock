package com.example.tengzheli.alarmclock;

import android.content.Context;
import android.nfc.Tag;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tengzheli on 2/04/18.
 */

public class TestInfoArrayAdapter extends ArrayAdapter<TestInfo> {

private  static  final String TAG = "TestInfoArrayAdapter";

private Context mContext;
int mResource;


    public TestInfoArrayAdapter(@NonNull Context context, int resource, ArrayList<TestInfo> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        String name = getItem(position).getName();
        String time = getItem(position).getTime();
        String number = getItem(position).getNumber();

        TestInfo ti = new TestInfo(name, time , number);

        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource, parent, false);

        TextView tvname  = (TextView) convertView.findViewById(R.id.textView1);
        TextView tvtime = (TextView) convertView.findViewById(R.id.textView2);
        TextView tvnumber  = (TextView) convertView.findViewById(R.id.textView3);

        tvname.setText(name);
        tvtime.setText(time);
        tvnumber.setText(number);

        return convertView;




    }
}

package com.example.tengzheli.alarmclock;

import android.content.Context;
import android.support.annotation.AnimatorRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by tengzheli on 2/04/18.
 */

public class TestInfoArrayAdapter extends ArrayAdapter<TestInfo> {

private  static  final String TAG = "TestInfoArrayAdapter";

private Context mContext;
private int mResource;
private  int lastPosition = -1;

    static class ViewHolder {
        TextView name;
        TextView time;
        TextView number;
    }

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


        //create the view result for showing the animation
        final View result;
        ViewHolder holder;

        if(convertView == null){
            LayoutInflater inflater = LayoutInflater.from(mContext);
            convertView = inflater.inflate(mResource, parent, false);

            holder = new ViewHolder();
            holder.name = (TextView) convertView.findViewById(R.id.textView1);
            holder.time = (TextView) convertView.findViewById(R.id.textView2);
            holder.number = (TextView) convertView.findViewById(R.id.textView3);

            result = convertView;
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
            result = convertView;
        }


        Animation animation = AnimationUtils.loadAnimation(mContext,
                (position> lastPosition) ? R.anim.load_down_anim: R.anim.load_up_anim);

        result.startAnimation(animation);
        lastPosition = position;

        holder.name.setText(ti.getName());
        holder.time.setText(ti.getTime());
        holder.number.setText(ti.getNumber());


        return convertView;




    }
}

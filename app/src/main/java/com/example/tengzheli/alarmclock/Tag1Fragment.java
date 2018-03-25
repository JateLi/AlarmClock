package com.example.tengzheli.alarmclock;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by tengzheli on 25/03/18.
 */

public class Tag1Fragment extends Fragment{

    private static final String TAG = "Tag1Fragment";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tag1_fragment, container, false);

        return  view;
    }
}

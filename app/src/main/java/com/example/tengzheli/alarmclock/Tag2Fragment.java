package com.example.tengzheli.alarmclock;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by tengzheli on 25/03/18.
 */

public class Tag2Fragment extends Fragment{

    private  static  final  String TAG = "Tab2Fragment";
    private Button tag2b;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.tag2_fragment, container, false);
      //  tag2b = (Button) view.findViewById(R.id.tag2_button);
//       tag2b.setOnClickListener(new View.OnClickListener() {
//           @Override
//           public void onClick(View view) {
//               Log.e("Tag2", "Show Up");
//           }
//       });
//
    return  view;
   }
}

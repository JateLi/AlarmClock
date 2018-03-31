package com.example.tengzheli.alarmclock;

import android.app.PendingIntent;

import java.util.Calendar;

/**
 * Created by tengzheli on 30/03/18.
 */

public class Alarm_Infor {

    private PendingIntent pending_intent;
    private  int request_code;
    private Calendar calendar;

    public PendingIntent getPending_intent() {
        return pending_intent;
    }

    public int getRequest_code() {
        return request_code;
    }


    public Alarm_Infor(PendingIntent pending_intent, int request_code, Calendar calendar) {
        this.pending_intent = pending_intent;
        this.request_code = request_code;
        this.calendar = calendar;
    }

    public Calendar getCalendar() {
        return calendar;
    }
}

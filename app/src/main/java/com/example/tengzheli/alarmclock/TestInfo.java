package com.example.tengzheli.alarmclock;

/**
 * Created by tengzheli on 2/04/18.
 */

public class TestInfo {

    private  String name;
    private  String time;
    private  String number;

    public TestInfo(String name, String time, String number) {
        this.name = name;
        this.time = time;
        this.number = number;
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public String getTime() {
        return time;
    }

    public String getNumber() {
        return number;
    }
}

package com.example.laakepaivakirja;

import java.util.Calendar;

public class Medicine {
    private String name;
    private String instruction;
    private int hours;
    private int mins;
    //private Calendar time;



    public Medicine (String n, String i, int hours, int mins) {
        this.name = n;
        this.instruction = i;
        this.hours = hours;
        this.mins = mins;
        //this.time.set(Calendar.HOUR, hours);
        //this.time.set(Calendar.MINUTE, mins);
    }

    @Override
    public String toString () {
        return this.name + ", " + this.hours + ":" + this.mins;
    }

}

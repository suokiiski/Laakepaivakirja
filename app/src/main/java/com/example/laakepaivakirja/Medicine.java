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
    public boolean equals (Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof  Medicine)) {
            return false;
        }
        Medicine m = (Medicine) obj;

        if (this.name.equals(m.name) && this.instruction.equals(m.instruction) && this.hours == m.hours && this.mins == m.mins) {
            return true;
        }
        return false;
    }

    public String getName () {
        return this.name;
    }

    public String getInstruction () {
        return this.instruction;
    }

    public String getHours () {
        return Integer.toString(this.hours);
    }

    public String getMins () {
        return Integer.toString(this.mins);
    }

    @Override
    public String toString () {
        return this.name + ", " + this.hours + ":" + this.mins;
    }

}

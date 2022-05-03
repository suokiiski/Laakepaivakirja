package com.example.laakepaivakirja;

import java.util.Calendar;

/**
 * Luokka kuvaa Medicine(lääke)-olioita
 * @author Vera Finogenova
 */
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

    /**
     * Vertailee parametrina saatun objektin tähän olioon
     * @param obj, johon vertaillaan
     * @return vertailun tulos (true tai false)
     */
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

    /**
     * Palauttaa lääkkeen nimi
     * @return String name
     */
    public String getName () {
        return this.name;
    }

    /**
     * Palauttaa lääkkeen käyttöohjeet
     * @return String instruction
     */
    public String getInstruction () {
        return this.instruction;
    }

    /**
     * Palauttaa lääkkeen käyttöajan tunnit merkkijono-muodossa
     * @return int hours muunnettuna String-tyyppiin
     */
    public String getHours () {
        return Integer.toString(this.hours);
    }

    /**
     * Palauttaa lääkkeen käyttöajan minuutit merkkijono-muodossa
     * @return int mins muunnettuna String-tyyppiin
     */
    public String getMins () {
        return Integer.toString(this.mins);
    }

    /**
     * Palauttaa merkkijonon, jonka käytetään ListView näkymässä
     * @return name, hours:mins
     */
    @Override
    public String toString () {
        return this.name + ", " + this.hours + ":" + this.mins;
    }

}

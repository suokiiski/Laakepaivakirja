package com.example.laakepaivakirja;

import java.util.Calendar;

/**
 * Luokka kuvaa Medicine(lääke)-olioita
 * @author Vera Finogenova
 */
public class Medicine {
    private String name;
    private String instruction;
    private String time;

    public Medicine (String n, String i, String time) {
        this.name = n;
        this.instruction = i;
        this.time = time;
        //this.hours = hours;
        //this.mins = mins;
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

        if (this.name.equals(m.name) && this.instruction.equals(m.instruction) && this.time.equals(m.time)) {
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

    public String getTime() {
        return this.time;
    }

    /**
     * Palauttaa merkkijono, jonka käytetään ListView näkymässä
     * @return name, hours:mins
     */
    @Override
    public String toString () {
        return this.name;
    }

}
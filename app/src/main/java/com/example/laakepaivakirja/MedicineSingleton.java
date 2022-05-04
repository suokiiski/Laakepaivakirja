package com.example.laakepaivakirja;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;
import com.google.gson.Gson;

/**
 * @author Vera Finogenova
 * Singleton-luokka, jossa sijaitsee Medicine-olioita sisältävä lista
 */
public class MedicineSingleton {
    private static final MedicineSingleton ourInstance = new MedicineSingleton();
    private ArrayList<Medicine> meds;

    /**
     * Palauttaa Medicine-olioita sisältävän listan
     * @return ourInstance
     */
    public static MedicineSingleton getInstance() {
        return ourInstance;
    }

    //konstruktori
    private MedicineSingleton () {
        this.meds = new ArrayList<>();
    }

    /**
     * Tekee uuden olion saatujen parametrien avulla ja lisää se listaan
     * @param name Medicine-olion name-muuttuja
     * @param instruction Medicine-olion instruction-muuttuja
     * @param time Medicine-olion time-muuttuja
     */
    public void addMedicine (String name, String instruction, String time) {
        this.meds.add(new Medicine(name, instruction, time));
    }

    /**
     * Saa parametrina Medicine-olion ja lisää se listaan, jos se ei ole vielä lisätty
     * @param m lisättävä Medicine-olio
     */
    public void addMedicine (Medicine m) {
        if (!this.meds.contains(m)) {
            this.meds.add(m);
        }
    }

    /**
     * Muunna ArrayList String merkkijonoksi .toJson metodilla
     * @return lista lääke-olioita String-merkkijonona
     */
    public String getMedicinesJson () {
        Gson gson = new Gson();
        String jsonMeds = gson.toJson(meds);
        return jsonMeds;
    }

    /**
     * Palauttaa lääkkeet listana
     * @return this.meds
     */
    public ArrayList<Medicine> getMedicines () {
        return this.meds;
    }

    /**
     * Palauttaa Medicine-olion listalta sen indeksin perusteella
     * @param i indeksi
     * @return Medicine-olio, jonka indeksi on i
     */
    public Medicine getMedicine (int i) {
        return this.meds.get(i);
    }
}

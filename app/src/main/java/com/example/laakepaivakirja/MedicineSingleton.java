package com.example.laakepaivakirja;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;
import com.google.gson.Gson;

/**
 * @author Vera Finogenova
 * Singleton-luokka, jossa sijaitsee Medicine-olioita
 */
public class MedicineSingleton {
    private static final MedicineSingleton ourInstance = new MedicineSingleton();
    private ArrayList<Medicine> meds;

    public static MedicineSingleton getInstance() {
        return ourInstance;
    }

    private MedicineSingleton () {
        this.meds = new ArrayList<>();
    }

    public void addMedicine (String name, String instruction, int hours, int mins) {
        this.meds.add(new Medicine(name, instruction, hours, mins));
    }

    public void addMedicine (Medicine m) {
        if (!this.meds.contains(m)) {
            this.meds.add(m);
        }
    }

    public String getMedicinesJson () {
        Gson gson = new Gson();
        String jsonMeds = gson.toJson(meds);
        return jsonMeds;
    }

    public ArrayList<Medicine> getMedicines () {
        return this.meds;
    }

    public Medicine getMedicine (int i) {
        return this.meds.get(i);
    }
}

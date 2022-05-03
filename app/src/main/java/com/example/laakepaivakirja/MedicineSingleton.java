package com.example.laakepaivakirja;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;
import com.google.gson.Gson;

public class MedicineSingleton {
    private static final MedicineSingleton ourInstance = new MedicineSingleton();
    private ArrayList<Medicine> meds;

    public static MedicineSingleton getInstance() {
        return ourInstance;
    }

    private MedicineSingleton () {
        this.meds = new ArrayList<>();
    }

    public void addMedicine (String name, String instruction, String time) {
        this.meds.add(new Medicine(name, instruction, time));
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

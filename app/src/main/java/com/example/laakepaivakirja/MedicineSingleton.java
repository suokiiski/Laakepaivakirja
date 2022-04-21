package com.example.laakepaivakirja;

import java.util.ArrayList;
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

    public ArrayList<Medicine> getMedicines () {
        return this.meds;
    }

    public Medicine getMedicine (int i) {
        return this.meds.get(i);
    }
}

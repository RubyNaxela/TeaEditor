/*=================================================================
 = This file is subject to the terms and conditions defined in    =
 = file 'LICENSE.txt', which is part of this source code package. =
 =================================================================*/

package com.rubynaxela.teaeditor.datatypes.database;

@SuppressWarnings("unused")
public final class BrewingInstruction {
    private int temperature, time;
    private String reuses, grams;

    public BrewingInstruction() {
    }

    public BrewingInstruction(int temperature, int time, String reuses, String grams) {
        this.temperature = temperature;
        this.time = time;
        this.reuses = reuses;
        this.grams = grams;
    }

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public String getReuses() {
        return reuses;
    }

    public void setReuses(String reuses) {
        this.reuses = reuses;
    }

    public String getGrams() {
        return grams;
    }

    public void setGrams(String grams) {
        this.grams = grams;
    }
}

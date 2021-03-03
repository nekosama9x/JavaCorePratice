package com.company.dto;

public class Cleaning {
    private int classClearning;
    private String namePersonClean;
    private int numBerOfTime;

    public Cleaning(int classClearning, String namePersonClean, int numBerOfTime) {
        this.classClearning = classClearning;
        this.namePersonClean = namePersonClean;
        this.numBerOfTime = numBerOfTime;
    }

    public Cleaning() {
    }

    public int getClassClearning() {
        return classClearning;
    }

    public void setClassClearning(int classClearning) {
        this.classClearning = classClearning;
    }

    public String getNamePersonClean() {
        return namePersonClean;
    }

    public void setNamePersonClean(String namePersonClean) {
        this.namePersonClean = namePersonClean;
    }

    public int getNumBerOfTime() {
        return numBerOfTime;
    }

    public void setNumBerOfTime(int numBerOfTime) {
        this.numBerOfTime = numBerOfTime;
    }
}

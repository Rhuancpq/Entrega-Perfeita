package com.oofga.ep.utilidade;//

// Created by rhuan on 17/05/19.
// ep2
//
public class Resposta {
    private boolean valid;
    private String string;
    private double first;
    private double second;

    public Resposta(boolean valid, String string, double first, double second){
        this.valid = valid;
        this.string = string;
        this.first = first;
        this.second = second;
    }

    public boolean isValid() {
        return valid;
    }

    public String getString() {
        return string;
    }

    public double getFirst() {
        return first;
    }

    public double getSecond() {
        return second;
    }
}


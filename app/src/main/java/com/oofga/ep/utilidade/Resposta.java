package com.oofga.ep.utilidade;//

// Created by rhuan on 17/05/19.
// ep2
//
public class Resposta {
    private boolean valid;
    private String string;
    private Double tempo;
    private Double custoTotal;

    public Resposta(boolean valid, String string, Double tempo, Double custoTotal){
        this.valid = valid;
        this.string = string;
        this.tempo = tempo;
        this.custoTotal = custoTotal;
    }

    public boolean isValid() {
        return valid;
    }

    public String getString() {
        return string;
    }

    public Double getTempo() {
        return tempo;
    }

    public Double getCustoTotal() {
        return custoTotal;
    }
}


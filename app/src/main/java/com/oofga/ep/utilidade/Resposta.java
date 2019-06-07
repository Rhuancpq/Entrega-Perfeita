package com.oofga.ep.utilidade;//

import java.math.BigDecimal;
import java.math.RoundingMode;

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
        this.tempo = BigDecimal.valueOf(this.tempo).
                setScale(5, RoundingMode.HALF_UP).doubleValue();
        this.custoTotal = BigDecimal.valueOf(this.custoTotal).
                setScale(5, RoundingMode.HALF_UP).doubleValue();
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


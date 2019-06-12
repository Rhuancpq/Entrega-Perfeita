package com.oofga.ep.Model.Fretes;//

import java.math.BigDecimal;
import java.math.RoundingMode;

// Created by rhuan on 17/05/19.
// ep2
//
public class Registro {
    private boolean valid;
    private String tipoVeiculo;
    private Double tempo;
    private Double custoTotal;

    public Registro(boolean valid, String tipoVeiculo, Double tempo, Double custoTotal){
        this.valid = valid;
        this.tipoVeiculo = tipoVeiculo;
        this.tempo = tempo;
        this.custoTotal = custoTotal;
        this.tempo = BigDecimal.valueOf(this.tempo).
                setScale(2, RoundingMode.HALF_UP).doubleValue();
        this.custoTotal = BigDecimal.valueOf(this.custoTotal).
                setScale(2, RoundingMode.HALF_UP).doubleValue();
    }

    public boolean isValid() {
        return valid;
    }

    public String getTipoVeiculo() {
        return tipoVeiculo;
    }

    public Double getTempo() {
        return tempo;
    }

    public Double getCustoTotal() {
        return custoTotal;
    }
}


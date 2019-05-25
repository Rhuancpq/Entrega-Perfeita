package com.oofga.ep.veiculos;//

// Created by rhuan on 17/05/19.
// ep2
//
public class Van extends VeiculoDiesel {
    Van() {
        setCarga_maxima(3500);
        setVelocidade_media(80);
        setTipo("Van");
        setConstanteDiesel(0.001d);
        setRendimentoDiesel(10d);
    }
}

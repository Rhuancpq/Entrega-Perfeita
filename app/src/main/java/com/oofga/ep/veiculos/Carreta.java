package com.oofga.ep.veiculos;//

// Created by rhuan on 17/05/19.
// ep
//
public class Carreta extends VeiculoDiesel {
    Carreta() {
        setCarga_maxima(30000d);
        setVelocidade_media(60d);
        setTipo("Carreta");
        setConstanteDiesel(0.0002d);
        setRendimentoDiesel(8d);
    }
}

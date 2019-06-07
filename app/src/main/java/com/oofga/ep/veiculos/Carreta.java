package com.oofga.ep.veiculos;//

// Created by rhuan on 17/05/19.
// ep
//
public class Carreta extends VeiculoDiesel {
    Carreta() {
        setCargaMaxima(30000d);
        setVelocidadeMedia(60d);
        setTipo("Carreta");
        setConstanteDiesel(0.0002d);
        setRendimentoDiesel(8d);
    }
}

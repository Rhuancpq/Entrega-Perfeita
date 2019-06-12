package com.oofga.ep.Model.Veiculos;//

// Created by rhuan on 17/05/19.
// ep2
//
public class Van extends VeiculoDiesel {
    Van() {
        setCargaMaxima(3500);
        setVelocidadeMedia(80);
        setTipo("Van");
        setConstanteDiesel(0.001d);
        setRendimentoDiesel(10d);
    }
}

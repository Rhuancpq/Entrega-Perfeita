package com.oofga.ep.Model.Veiculos;//

// Created by rhuan on 17/05/19.
// ep2
//
public class Moto extends VeiculoFlex {
    Moto(){
        setCargaMaxima(50);
        setVelocidadeMedia(110);
        setTipo("Moto");
        setRendimentoAlcool(43);
        setRendimentoGasolina(50);
        setConstanteAlcool(0.4d);
        setConstanteGasolina(0.3d);
    }

}

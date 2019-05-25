package com.oofga.ep.veiculos;//

// Created by rhuan on 17/05/19.
// ep2
//
public class Carro extends VeiculoFlex {
    Carro() {
        setCarga_maxima(360);
        setVelocidade_media(100);
        setTipo("Carro");
        setRendimentoAlcool(12);
        setRendimentoGasolina(14);
        setConstanteAlcool(0.0231d);
        setConstanteGasolina(0.025d);
    }

}

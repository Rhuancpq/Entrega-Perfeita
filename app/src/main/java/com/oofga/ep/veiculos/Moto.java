package com.oofga.ep.veiculos;//

// Created by rhuan on 17/05/19.
// ep2
//
public class Moto extends VeiculoFlex {
    Moto(){
        setCarga_maxima(50);
        setVelocidade_media(110);
        setTipo("Moto");
        setRendimentoAlcool(43);
        setRendimentoGasolina(50);
        setConstanteAlcool(0.4d);
        setConstanteGasolina(0.3d);
    }

}

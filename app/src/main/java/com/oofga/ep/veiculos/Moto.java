package com.oofga.ep.veiculos;//

// Created by rhuan on 17/05/19.
// ep2
//
public class Moto extends Veiculo {
    Moto(){
        setCarga_maxima(50);
        setRendimento(50);
        setVelocidade_media(110);
        setConstante_variacao(0.3d);
        setTipo("Moto");
    }

    @Override
    double calculaCusto(double distancia, double carga, int tipo_combustivel) {
        return 0;
    }
}

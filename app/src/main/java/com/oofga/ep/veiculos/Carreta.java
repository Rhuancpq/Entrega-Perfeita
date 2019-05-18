package com.oofga.ep.veiculos;//

// Created by rhuan on 17/05/19.
// ep
//
public class Carreta extends Veiculo {
    Carreta() {
        setCarga_maxima(30000d);
        setRendimento(8);
        setVelocidade_media(60d);
        setConstante_variacao(0.0002d);
        setTipo("Carreta");
    }

    @Override
    double calculaCusto(double distancia, double carga, int tipo_combustivel) {
        if (tipo_combustivel == 0d) {
            return (distancia / calculaRendimento(carga)) * preco_diesel;
        } else if (tipo_combustivel == 1d) {
            return -1d;
        } else if (tipo_combustivel == 2d) {
            return -1d;
        } else {
            //throw
            return -1d;
        }
    }
}

package com.oofga.ep.veiculos;//

// Created by rhuan on 17/05/19.
// ep2
//
public class Van extends Veiculo {
    Van() {
        setCarga_maxima(3500);
        setRendimento(10);
        setVelocidade_media(80);
        setConstante_variacao(0.001d);
    }

    @Override
    double calculaCusto(double distancia, double carga, int tipo_combustivel) {
        if (tipo_combustivel == 0) {
            return (distancia / calculaRendimento(carga)) * preco_diesel;
        } else if (tipo_combustivel == 1) {
            return -1;
        } else if (tipo_combustivel == 2) {
            return -1;
        } else {
            //throw
            return -1;
        }
    }
}

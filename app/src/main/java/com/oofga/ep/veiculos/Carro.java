package com.oofga.ep.veiculos;//

// Created by rhuan on 17/05/19.
// ep2
//
public class Carro extends Veiculo {
    Carro() {
        setCarga_maxima(360);
        setRendimento(14);
        setVelocidade_media(100);
        setConstante_variacao(0.025d);
        setTipo("Carro");
    }

    @Override
    double calculaCusto(double distancia, double carga, int tipo_combustivel) {
        if (tipo_combustivel == 0) {
            return -1;
        } else if (tipo_combustivel == 1) {
            return distancia / calculaRendimento(carga) * preco_alcool;
        } else if (tipo_combustivel == 2) {
            return distancia / calculaRendimento(carga) * preco_gasolina;
        } else {
            //throw
            return -1;
        }
    }
}

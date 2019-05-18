package com.oofga.ep.veiculos;//

import com.oofga.ep.entrega.Resposta;

import java.util.ArrayList;

// Created by rhuan on 17/05/19.
// ep2
//
public class Frota {
    private ArrayList<Veiculo> lista = new ArrayList<>();

    public void adicionarVeiculo(Veiculo temp){
        lista.add(temp);
    }

    public void removerVeiculo(int index){
        lista.remove(index);
    }

    public Veiculo getVeiculo(int index){
        return lista.get(index);
    }

    private double calculaCusto(Veiculo x, double distancia, double carga){
        if (x.getTipo().equals("Carreta") || x.getTipo().equals("Van")) {
            return x.calculaCusto(distancia, carga, x.COMBUSTIVEL_DIESEL);
        }else{
            double custoGas,custoAlc;
            custoGas = x.calculaCusto(distancia, carga, x.COMBUSTIVEL_GASOLINA);
            custoAlc = x.calculaCusto(distancia, carga, x.COMBUSTIVEL_ALCOOL);
            if(custoGas <= custoAlc){
                return custoGas;
            }else{
                return custoAlc;
            }
        }
    }

    public Resposta veiculoMenorCusto(double carga, double distancia, double tempoMax){
        double menorCusto = 0;
        String tipo_veiculo = "";
        double tempo = 0;
        for (Veiculo x:lista) {
            if(tipo_veiculo.isEmpty()) {
                menorCusto = calculaCusto(x,distancia,carga);
                tipo_veiculo = x.getTipo();
                tempo = x.calculaTempo(distancia);
            }else{
                double custoTemp = calculaCusto(x,distancia,carga);
                if(custoTemp <= menorCusto){
                    menorCusto = custoTemp;
                    tipo_veiculo = x.getTipo();
                    tempo = x.calculaTempo(distancia);
                }
            }
        }
        return new Resposta(tipo_veiculo,tempo,menorCusto);
    }

    public Resposta veiculoMaisRapido(double carga, double distancia, double tempoMax){
        double custo = 0;
        String tipoVeiculo = "";
        double menorTempo = 0;
        for (Veiculo x:lista) {
            if(tipoVeiculo.isEmpty()){
                menorTempo = x.calculaTempo(distancia);
                tipoVeiculo = x.getTipo();
                custo = calculaCusto(x,distancia,carga);
            }else{
                double tempo = x.calculaTempo(distancia);
                if (tempo <= menorTempo){
                    menorTempo = tempo;
                    tipoVeiculo = x.getTipo();
                    custo = calculaCusto(x,distancia,carga);
                }
            }
        }
        return new Resposta(tipoVeiculo,menorTempo, custo);
    }

    /* String veiculoMelhorBeneficio(){
        return;
    }*/
}

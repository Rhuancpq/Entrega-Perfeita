package com.oofga.ep.veiculos;//

import com.oofga.ep.entrega.Resposta;

import java.util.ArrayList;

// Created by rhuan on 17/05/19.
// ep2
//
public class Frota {
    private ArrayList<Veiculo> lista = new ArrayList<>();

    public void adicionarVeiculo(Veiculo temp) {
        lista.add(temp);
    }

    public void removerVeiculo(int index) {
        lista.remove(index);
    }

    public void ocuparVeiculo(String tipo) {
        for (Veiculo x : lista) {
            if (x.getTipo().equals(tipo) && x.estaDisponivel()) {
                x.tornaIndisponivel();
                break;
            }
        }
    }

    public Veiculo getVeiculo(int index) {
        return lista.get(index);
    }

    private double calculaCusto(Veiculo x, double distancia, double carga) {
        if (x.getTipo().equals("Carreta") || x.getTipo().equals("Van")) {
            return x.calculaCusto(distancia, carga, x.COMBUSTIVEL_DIESEL);
        } else {
            double custoGas, custoAlc;
            custoGas = x.calculaCusto(distancia, carga, x.COMBUSTIVEL_GASOLINA);
            custoAlc = x.calculaCusto(distancia, carga, x.COMBUSTIVEL_ALCOOL);
            if (custoGas <= custoAlc) {
                return custoGas;
            } else {
                return custoAlc;
            }
        }
    }

    public Resposta veiculoMenorCusto(double carga, double distancia, double tempoMax, double margemLucro) {
        double menorCusto = 0;
        String tipo_veiculo = "";
        double tempo = 0;
        for (Veiculo x : lista) {
            if (!x.suportaCarga(carga)) {
                continue;
            }
            if (x.calculaTempo(distancia) > tempoMax) {
                continue;
            }
            if (tipo_veiculo.isEmpty()) {
                menorCusto = calculaCusto(x, distancia, carga);
                tipo_veiculo = x.getTipo();
                tempo = x.calculaTempo(distancia);
            } else {
                double custoTemp = calculaCusto(x, distancia, carga);
                if (custoTemp <= menorCusto) {
                    menorCusto = custoTemp;
                    tipo_veiculo = x.getTipo();
                    tempo = x.calculaTempo(distancia);
                }
            }
        }
        if (tipo_veiculo.isEmpty()) {
            return new Resposta(false, tipo_veiculo, tempo, menorCusto);
        } else {
            return new Resposta(true, tipo_veiculo, tempo, menorCusto * margemLucro);
        }
    }

    public Resposta veiculoMaisRapido(double carga, double distancia, double tempoMax, double margemLucro) {
        double custo = 0;
        String tipoVeiculo = "";
        double menorTempo = 0;
        for (Veiculo x : lista) {
            if (!x.suportaCarga(carga)) {
                continue;
            }
            if (x.calculaTempo(distancia) > tempoMax) {
                continue;
            }
            if (tipoVeiculo.isEmpty()) {
                menorTempo = x.calculaTempo(distancia);
                tipoVeiculo = x.getTipo();
                custo = calculaCusto(x, distancia, carga);
            } else {
                double tempo = x.calculaTempo(distancia);
                if (tempo <= menorTempo) {
                    menorTempo = tempo;
                    tipoVeiculo = x.getTipo();
                    custo = calculaCusto(x, distancia, carga);
                }
            }
        }
        if (tipoVeiculo.isEmpty()) {
            return new Resposta(false, tipoVeiculo, menorTempo, custo);
        } else {
            return new Resposta(true, tipoVeiculo, menorTempo, custo * margemLucro);
        }
    }

    public Resposta veiculoMelhorBeneficio(double carga, double distancia, double tempoMax, double margemLucro) {
        double menorCusto = 0;
        String tipoVeiculo = "";
        double menorTempo = 0;
        for (Veiculo x : lista) {
            if (!x.suportaCarga(carga)) {
                continue;
            }
            if (x.calculaTempo(distancia) > tempoMax) {
                continue;
            }
            if (tipoVeiculo.isEmpty()) {
                menorTempo = x.calculaTempo(distancia);
                tipoVeiculo = x.getTipo();
                menorCusto = calculaCusto(x, distancia, carga);
            } else {
                double tempo = x.calculaTempo(distancia);
                double custoTemp = calculaCusto(x, distancia, carga);
                if (tempo <= menorTempo && custoTemp <= menorCusto) {
                    menorTempo = tempo;
                    tipoVeiculo = x.getTipo();
                    menorCusto = custoTemp;
                }
            }
        }
        if (tipoVeiculo.isEmpty()) {
            return new Resposta(false, tipoVeiculo, menorTempo, menorCusto);
        } else {
            return new Resposta(true, tipoVeiculo, menorTempo, menorCusto * margemLucro);
        }
    }
}

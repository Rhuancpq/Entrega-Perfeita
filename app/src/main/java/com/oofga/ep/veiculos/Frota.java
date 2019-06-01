package com.oofga.ep.veiculos;//

import com.oofga.ep.utilidade.Resposta;

import java.util.ArrayList;
import java.util.Iterator;

// Created by rhuan on 17/05/19.
// ep2
//
public class Frota {
    private ArrayList<Veiculo> lista = new ArrayList<>();
    private int qntCarretas = 0, qntCarros = 0, qntMotos = 0, qntVans = 0, carretasOcupadas = 0,
            carrosOcupados = 0, motosOcupadas = 0, vansOcupadas = 0;

    public int getCarretasOcupadas() {
        return carretasOcupadas;
    }

    public int getCarrosOcupados() {
        return carrosOcupados;
    }

    public int getMotosOcupadas() {
        return motosOcupadas;
    }

    public int getVansOcupadas() {
        return vansOcupadas;
    }

    public int getQntCarretas() {
        return qntCarretas;
    }

    public int getQntCarros() {
        return qntCarros;
    }

    public int getQntMotos() {
        return qntMotos;
    }

    public int getQntVans() {
        return qntVans;
    }

    public int getQntCarretasDisp() {
        return qntCarretas - carretasOcupadas;
    }

    public int getQntCarrosDisp() {
        return qntCarros - carrosOcupados;
    }

    public int getQntMotosDisp() {
        return qntMotos - motosOcupadas;
    }

    public int getQntVansDisp() {
        return qntVans - vansOcupadas;
    }

    public void adicionarVeiculos(String tipo, int quantidade) {
        if (tipo.equals("Carreta")) {
            for (int i = 0; i < quantidade; i++) {
                lista.add(new Carreta());
                qntCarretas++;
            }
        } else if (tipo.equals("Carro")) {
            for (int i = 0; i < quantidade; i++) {
                lista.add(new Carro());
                qntCarros++;
            }
        } else if (tipo.equals("Moto")) {
            for (int i = 0; i < quantidade; i++) {
                lista.add(new Moto());
                qntMotos++;
            }
        } else if (tipo.equals("Van")) {
            for (int i = 0; i < quantidade; i++) {
                lista.add(new Van());
                qntVans++;
            }
        } else {

        }
    }

    public void removerVeiculos(String tipo, int quantidade) {
        for (int i = 0; i < quantidade; i++) {
            Iterator itr = lista.iterator();
            while (itr.hasNext()) {
                Veiculo x = (Veiculo) itr.next();
                if (x.getTipo().equals(tipo)) {
                    itr.remove();
                    break;
                }
            }
        }
    }

    public void desocuparVeiculos(String tipo, int quantidade) {
        for (int i = 0; i < quantidade; i++) {
            for (Veiculo x : lista) {
                if (x.getTipo().equals(tipo) && !(x.estaDisponivel())) {
                    switch (tipo) {
                        case "Carreta":
                            carretasOcupadas--;
                            break;
                        case "Carro":
                            carrosOcupados--;
                            break;
                        case "Moto":
                            motosOcupadas--;
                            break;
                        case "Van":
                            vansOcupadas--;
                            break;
                    }
                    x.tornaDisponivel();
                    break;
                }
            }
        }
    }

    public void ocuparVeiculo(String tipo) {
        for (Veiculo x : lista) {
            if (x.getTipo().equals(tipo) && x.estaDisponivel()) {
                switch (tipo) {
                    case "Carreta":
                        carretasOcupadas++;
                        break;
                    case "Carro":
                        carrosOcupados++;
                        break;
                    case "Moto":
                        motosOcupadas++;
                        break;
                    case "Van":
                        vansOcupadas++;
                        break;
                }
                x.tornaIndisponivel();
                break;
            }
        }
    }

    private double lucro(double val, double percent) {
        if (percent >= 1) {
            throw new ClassCastException(" Margem de Lucro não suportada");
        }
        return val / (1 - percent);
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
            if (x.naoSuportaCarga(carga)) {
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
            return new Resposta(true, tipo_veiculo, tempo, lucro(menorCusto,margemLucro));
        }
    }

    public Resposta veiculoMaisRapido(double carga, double distancia, double tempoMax, double margemLucro) {
        double custo = 0;
        String tipoVeiculo = "";
        double menorTempo = 0;
        for (Veiculo x : lista) {
            if (!x.naoSuportaCarga(carga)) {
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
            return new Resposta(true, tipoVeiculo, menorTempo, lucro(custo,margemLucro));
        }
    }

    public Resposta veiculoMelhorBeneficio(double carga, double distancia, double tempoMax, double margemLucro) {
        double menorCusto = 0;
        String tipoVeiculo = "";
        double menorTempo = 0;
        for (Veiculo x : lista) {
            if (!x.naoSuportaCarga(carga)) {
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
            return new Resposta(true, tipoVeiculo, menorTempo, lucro(menorCusto,margemLucro));
        }
    }
}

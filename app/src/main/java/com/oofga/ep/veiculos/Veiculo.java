package com.oofga.ep.veiculos;//

// Created by rhuan on 17/05/19.
// ep2
//
public abstract class Veiculo {
    public static final int COMBUSTIVEL_DIESEL = 0;
    public static final int COMBUSTIVEL_ALCOOL = 1;
    public static final int COMBUSTIVEL_GASOLINA = 2;

    protected static double preco_alcool = 3.499d;
    protected static double preco_gasolina = 4.449d;
    protected static double preco_diesel = 3.869d;

    private double rendimento;
    private double carga_maxima;
    private double velocidade_media;
    private double constante_variacao;

    public double getRendimento() {
        return rendimento;
    }

    public void setRendimento(double rendimento) {
        this.rendimento = rendimento;
    }

    public double getCarga_maxima() {
        return carga_maxima;
    }

    public void setCarga_maxima(double carga_maxima) {
        this.carga_maxima = carga_maxima;
    }

    public double getVelocidade_media() {
        return velocidade_media;
    }

    public void setVelocidade_media(double velocidade_media) {
        this.velocidade_media = velocidade_media;
    }

    public double getConstante_variacao() {
        return constante_variacao;
    }

    public void setConstante_variacao(double constante_variacao) {
        this.constante_variacao = constante_variacao;
    }

    double calculaRendimento(double carga) {
        return rendimento - carga * constante_variacao;
    }

    abstract double calculaCusto(double distancia, double carga, int tipo_combustivel);

    double calculaTempo(double distancia) {
        return distancia / velocidade_media;
    }

}

package com.oofga.ep.veiculos;//

// Created by rhuan on 17/05/19.
// ep2
//
public abstract class Veiculo {
    public final int COMBUSTIVEL_DIESEL = 0;
    public final int COMBUSTIVEL_ALCOOL = 1;
    public final int COMBUSTIVEL_GASOLINA = 2;

    protected static double preco_alcool = 3.499d;
    protected static double preco_gasolina = 4.449d;
    protected static double preco_diesel = 3.869d;

    private String tipo;
    private double carga_maxima;
    private double velocidade_media;
    private boolean disponivel = true;

    public boolean estaDisponivel(){
        return disponivel;
    }

    public void tornaIndisponivel(){
        disponivel = false;
    }

    public boolean naoSuportaCarga(double carga){
        return carga > carga_maxima;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
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

    abstract double calculaRendimento(double carga, int tipoCombustivel);

    abstract double calculaCusto(double distancia, double carga, int tipoCombustivel);

    double calculaTempo(double distancia) {
        return distancia / velocidade_media;
    }

}

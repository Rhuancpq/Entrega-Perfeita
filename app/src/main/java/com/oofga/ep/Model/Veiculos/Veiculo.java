package com.oofga.ep.Model.Veiculos;//

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
    private double cargaMaxima;
    private double velocidadeMedia;
    private boolean disponivel = true;

    public boolean estaDisponivel(){
        return disponivel;
    }

    public void tornaIndisponivel(){
        disponivel = false;
    }

    public void tornaDisponivel(){
        disponivel = true;
    }

    public boolean naoSuportaCarga(double carga){
        return carga > cargaMaxima;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setCargaMaxima(double cargaMaxima) {
        this.cargaMaxima = cargaMaxima;
    }

    public void setVelocidadeMedia(double velocidadeMedia) {
        this.velocidadeMedia = velocidadeMedia;
    }

    abstract double calculaRendimento(double carga, int tipoCombustivel);

    abstract double calculaCusto(double distancia, double carga, int tipoCombustivel);

    double calculaTempo(double distancia) {
        return distancia / velocidadeMedia;
    }

}

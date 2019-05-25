package com.oofga.ep.veiculos;

public abstract class VeiculoFlex extends Veiculo {
    private double rendimentoAlcool, rendimentoGasolina,constanteAlcool, constanteGasolina;

    @Override
    public double calculaRendimento(double carga, int tipoCombustivel){
        if(tipoCombustivel == COMBUSTIVEL_ALCOOL){
            return rendimentoAlcool - carga*constanteAlcool;
        }else if(tipoCombustivel == COMBUSTIVEL_GASOLINA){
            return rendimentoGasolina - carga*constanteGasolina;
        }else{
            return -1;
        }
    }

    @Override
    public double calculaCusto(double distancia, double carga, int tipoCombustivel) {
        if (tipoCombustivel == 0) {
            return -1;
        } else if (tipoCombustivel == 1) {
            return distancia / calculaRendimento(carga,COMBUSTIVEL_ALCOOL) * preco_alcool;
        } else if (tipoCombustivel == 2) {
            return distancia / calculaRendimento(carga,COMBUSTIVEL_GASOLINA) * preco_gasolina;
        } else {
            return -1;
        }
    }

    public double getRendimentoAlcool() {
        return rendimentoAlcool;
    }

    public void setRendimentoAlcool(double rendimentoAlcool) {
        this.rendimentoAlcool = rendimentoAlcool;
    }

    public double getRendimentoGasolina() {
        return rendimentoGasolina;
    }

    public void setRendimentoGasolina(double rendimentoGasolina) {
        this.rendimentoGasolina = rendimentoGasolina;
    }

    public double getConstanteAlcool() {
        return constanteAlcool;
    }

    public void setConstanteAlcool(double constanteAlcool) {
        this.constanteAlcool = constanteAlcool;
    }

    public double getConstanteGasolina() {
        return constanteGasolina;
    }

    public void setConstanteGasolina(double constanteGasolina) {
        this.constanteGasolina = constanteGasolina;
    }
}

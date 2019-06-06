package com.oofga.ep.veiculos;

public abstract class VeiculoDiesel extends Veiculo {
    private double rendimentoDiesel, constanteDiesel;

    @Override
    public double calculaRendimento(double carga, int tipoCombustivel) {
        return rendimentoDiesel - (carga * constanteDiesel);
    }

    @Override
    public double calculaCusto(double distancia, double carga, int tipoCombustivel) {
        if (tipoCombustivel == COMBUSTIVEL_DIESEL) {
            return (distancia / calculaRendimento(carga, COMBUSTIVEL_DIESEL)) * preco_diesel;
        } else if (tipoCombustivel == COMBUSTIVEL_ALCOOL) {
            return -1d;
        } else if (tipoCombustivel == COMBUSTIVEL_GASOLINA) {
            return -1d;
        } else {
            //throw
            return -1d;
        }
    }

    public double getRendimentoDiesel() {
        return rendimentoDiesel;
    }

    public void setRendimentoDiesel(double rendimentoDiesel) {
        this.rendimentoDiesel = rendimentoDiesel;
    }

    public double getConstanteDiesel() {
        return constanteDiesel;
    }

    public void setConstanteDiesel(double constanteDiesel) {
        this.constanteDiesel = constanteDiesel;
    }
}

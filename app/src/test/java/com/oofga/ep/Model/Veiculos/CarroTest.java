package com.oofga.ep.Model.Veiculos;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CarroTest {
    public double distancia;
    public double carga;
    public Carro carro;

    @Before
    public void beforeTests(){
        distancia = 906d;
        carga = 76.5d;
        carro = new Carro();
    }

    @Test
    public void testaDisponibilidade(){
        assertTrue(carro.estaDisponivel());
    }

    @Test
    public void testaNaoSuportaCarga(){
        assertFalse(carro.naoSuportaCarga(carga));
    }

    @Test
    public void testaCalculaRendimento(){
        double rendimento = 10.23285d;
        assertEquals(rendimento,carro.calculaRendimento(carga,carro.COMBUSTIVEL_ALCOOL),10e-5);
    }

    @Test
    public void testaCalculaCusto(){
        double custo = 309.795804688d;
        assertEquals(custo,carro.calculaCusto(distancia,carga,carro.COMBUSTIVEL_ALCOOL),10e-5);
    }

    @Test
    public void testaCalculaTempo(){
        double tempo = 9.06d;
        assertEquals(tempo,carro.calculaTempo(distancia),10e-5);
    }

}
package com.oofga.ep.frota;//

import com.oofga.ep.veiculos.Veiculo;

import java.lang.reflect.Array;
import java.util.ArrayList;

// Created by rhuan on 17/05/19.
// ep2
//
public class Frota {
    private ArrayList<Veiculo> frota = new ArrayList<>();


    public void adicionarVeiculo(Veiculo temp){
        frota.add(temp);
    }

    public void removerVeiculo(int index){
        frota.remove(index);
    }
}

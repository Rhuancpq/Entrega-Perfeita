package com.oofga.ep.Controller;

import android.content.SharedPreferences;

import com.oofga.ep.Model.Veiculos.Frota;

public class FrotaController {

    public static void carregarFrota(SharedPreferences sharedPreferences, Frota frota){
        int qntCarretas = 0, qntCarros = 0, qntMotos = 0, qntVans = 0;
        qntCarretas = sharedPreferences.getInt("qntCarretas", 0);

        qntCarros = sharedPreferences.getInt("qntCarros", 0);

        qntMotos = sharedPreferences.getInt("qntMotos", 0);

        qntVans = sharedPreferences.getInt("qntVans", 0);

        if (qntCarretas != 0) {
            frota.adicionarVeiculos("Carreta", qntCarretas);
        }

        if (qntCarros != 0) {
            frota.adicionarVeiculos("Carros", qntCarros);
        }

        if (qntMotos != 0) {
            frota.adicionarVeiculos("Carreta", qntMotos);
        }

        if (qntVans != 0) {
            frota.adicionarVeiculos("Carreta", qntVans);
        }
    }

    private static boolean verificarVeiculos(Frota frota, String tipo, int quantidade) {
        switch (tipo) {
            case "Carreta":
                return frota.getQntCarretasDisp() >= quantidade;
            case "Carro":
                return frota.getQntCarrosDisp() >= quantidade;
            case "Moto":
                return frota.getQntMotosDisp() >= quantidade;
            case "Van":
                return frota.getQntVansDisp() >= quantidade;
            default:
                return false;

        }
    }

    public static boolean frotaAction(Frota frota, String tipo, int quantidade, String tipoAcao){
        switch (tipoAcao) {
            case "Adicionar":
                frota.adicionarVeiculos(tipo, quantidade);
                return true;
            case "Remover":
                if (verificarVeiculos(frota,tipo, quantidade)) {
                    frota.removerVeiculos(tipo, quantidade);
                    return true;
                } else {
                    return false;
                }
        }
        return false;
    }
}

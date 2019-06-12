package com.oofga.ep.Controller;

import com.oofga.ep.Model.Fretes.Frete;
import com.oofga.ep.Model.Fretes.Registro;
import com.oofga.ep.Model.Veiculos.Frota;

import java.util.ArrayList;
import java.util.Iterator;

public class FreteController {

    public static boolean removerFrete(Frota frota, ArrayList<Frete> fretes, int Id){
        boolean invalid = true;
        Iterator itr = fretes.iterator();
        while (itr.hasNext()) {
            Frete x = (Frete) itr.next();
            if (x.getId() == Id) {
                itr.remove();
                frota.desocuparVeiculos(x.getVeiculo(),1);
                invalid = false;
                break;
            }
        }
        return invalid;
    }

    public static void finalizarFrete(Frete frete, Registro registro, int Id){
        frete.setCusto(registro.getCustoTotal());
        frete.setTempo(registro.getTempo());
        frete.setVeiculo(registro.getTipoVeiculo());
        frete.setId(Id);
    }

    public static Frete registrarFrete(String name,
                                         double carga, double distancia){
        Frete frete = new Frete();
        frete.setNome(name);
        frete.setCarga(carga);
        frete.setDistancia(distancia);
        return frete;
    }
}

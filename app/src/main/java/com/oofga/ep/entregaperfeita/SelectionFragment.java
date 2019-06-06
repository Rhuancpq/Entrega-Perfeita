package com.oofga.ep.entregaperfeita;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.oofga.ep.utilidade.Resposta;
import com.oofga.ep.veiculos.Frota;

public class SelectionFragment extends Fragment {
    Resposta veiculoRapido, veiculoBarato, veiculoBeneficio;
    SelectionListener selectionCallback;

    interface SelectionListener{
        void onBtnVeiculoClick(Resposta resposta);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            selectionCallback = (SelectionListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " Deve implementar Selection Listener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.selection_fragment, container, false);
    }

    public void setVeiculoRapido(Resposta veiculoRapido) {
        this.veiculoRapido = veiculoRapido;
    }

    public void setVeiculoBarato(Resposta veiculoBarato) {
        this.veiculoBarato = veiculoBarato;
    }

    public void setVeiculoBeneficio(Resposta veiculoBeneficio) {
        this.veiculoBeneficio = veiculoBeneficio;
    }
}

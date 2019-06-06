package com.oofga.ep.entregaperfeita;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.oofga.ep.utilidade.Resposta;

public class SelectionFragment extends Fragment {
    Resposta veiculoRapido, veiculoBarato, veiculoBeneficio;
    SelectionListener selectionCallback;
    Button btnVBeneficio, btnVCusto, btnVRapido;
    TextView tipoVBeneficio, tipoVCusto, tipoVRapido,
            tempoVBeneficio, tempoVCusto, tempoVRapido,
            custoVBeneficio, custoVCusto, custoVRapido,
            txtBeneficio, txtCusto, txtRapido;

    interface SelectionListener {
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
        View view = inflater.inflate(R.layout.selection_fragment, container, false);
        tipoVBeneficio = view.findViewById(R.id.tipoVBeneficio);
        tipoVCusto = view.findViewById(R.id.tipoVCusto);
        tipoVRapido = view.findViewById(R.id.tipoVRapido);
        tempoVBeneficio = view.findViewById(R.id.tempoVBeneficio);
        tempoVCusto = view.findViewById(R.id.tempoVCusto);
        tempoVRapido = view.findViewById(R.id.tempoVRapido);
        custoVBeneficio = view.findViewById(R.id.custoVBeneficio);
        custoVCusto = view.findViewById(R.id.custoVCusto);
        custoVRapido = view.findViewById(R.id.custoVRapido);
        btnVBeneficio = view.findViewById(R.id.btnVBeneficio);
        btnVCusto = view.findViewById(R.id.btnVCusto);
        btnVRapido = view.findViewById(R.id.btnVRapido);
        txtBeneficio = view.findViewById(R.id.txtBeneficio);
        txtCusto = view.findViewById(R.id.txtBarato);
        txtRapido = view.findViewById(R.id.txtRapido);
        btnVRapido.setClickable(false);
        btnVCusto.setClickable(false);
        btnVBeneficio.setClickable(false);
        atualizarExibicao();
        return view;
    }

    public void atualizarExibicao() {
        if(veiculoRapido.isValid() || veiculoBarato.isValid()
        || veiculoBeneficio.isValid()) {
            if (veiculoRapido.isValid()) {
                String temp;
                tipoVRapido.setText(veiculoRapido.getString());
                temp = veiculoRapido.getTempo().toString() + "h";
                tempoVRapido.setText(temp);
                temp = veiculoRapido.getCustoTotal().toString() + "R$";
                custoVRapido.setText(temp);
                tipoVRapido.setVisibility(EditText.VISIBLE);
                tempoVRapido.setVisibility(EditText.VISIBLE);
                custoVRapido.setVisibility(EditText.VISIBLE);
                txtRapido.setVisibility(EditText.VISIBLE);
            }

            if (veiculoBarato.isValid()) {
                String temp;
                tipoVCusto.setText(veiculoBarato.getString());
                temp = veiculoBarato.getTempo().toString() + "h";
                tempoVCusto.setText(temp);
                temp = veiculoBarato.getCustoTotal().toString() + "R$";
                custoVCusto.setText(temp);
                tipoVCusto.setVisibility(EditText.VISIBLE);
                tempoVCusto.setVisibility(EditText.VISIBLE);
                custoVCusto.setVisibility(EditText.VISIBLE);
                txtCusto.setVisibility(EditText.VISIBLE);
            }

            if (veiculoBeneficio.isValid()) {
                String temp;
                tipoVBeneficio.setText(veiculoBeneficio.getString());
                temp = veiculoBeneficio.getTempo().toString() + "h";
                tempoVBeneficio.setText(temp);
                temp = veiculoBeneficio.getCustoTotal().toString() + "R$";
                custoVBeneficio.setText(temp);
                tipoVBeneficio.setVisibility(EditText.VISIBLE);
                tempoVBeneficio.setVisibility(EditText.VISIBLE);
                custoVBeneficio.setVisibility(EditText.VISIBLE);
                txtBeneficio.setVisibility(EditText.VISIBLE);
            }
        }else {
            String temp;
            temp = "Não há nenhum veículo possível para esta entrega";
            txtRapido.setText(temp);
            txtRapido.setVisibility(EditText.VISIBLE);
        }
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

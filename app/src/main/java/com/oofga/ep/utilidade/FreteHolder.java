package com.oofga.ep.utilidade;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.oofga.ep.entregaperfeita.R;

import java.util.Locale;

public class FreteHolder extends RecyclerView.ViewHolder {
    private TextView txtNome, txtId, txtVeiculo, txtDistancia,
            txtCarga, txtTempo, txtCusto;

    public FreteHolder(View view){
        super(view);
        txtNome = view.findViewById(R.id.txtNome);
        txtId = view.findViewById(R.id.txtId);
        txtVeiculo = view.findViewById(R.id.txtVeiculo);
        txtDistancia = view.findViewById(R.id.txtDistancia);
        txtCarga = view.findViewById(R.id.txtCarga);
        txtTempo = view.findViewById(R.id.txtTempo);
        txtCusto = view.findViewById(R.id.txtCusto);
    }

    public void setDetails(Frete frete){
        txtNome.setText(frete.getNome());
        txtId.setText(String.format(Locale.getDefault(),"Id: %d", frete.getId()));
        txtDistancia.setText(String.format(Locale.getDefault(),
                "Distância: %fKm",frete.getDistancia()));
        txtCusto.setText(String.format(Locale.getDefault(),
                "Custo: %fR$",frete.getCusto()));
        txtTempo.setText(String.format(Locale.getDefault(),"Tempo: %fh",frete.getTempo()));
        txtVeiculo.setText(String.format(Locale.getDefault(),"Veículo: %s",frete.getVeiculo()));
        txtCarga.setText(String.format(Locale.getDefault(), "Carga %fKg",frete.getCarga()));
    }
}

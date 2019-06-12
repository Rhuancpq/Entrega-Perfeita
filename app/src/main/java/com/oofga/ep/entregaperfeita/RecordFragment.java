package com.oofga.ep.entregaperfeita;


import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.oofga.ep.Model.Fretes.Frete;
import com.oofga.ep.Model.Fretes.FreteAdapter;

import java.util.ArrayList;
import java.util.Locale;

public class RecordFragment extends Fragment {
    private RecyclerView recyclerView;
    private FreteAdapter adapter;
    private ArrayList<Frete> freteArrayList;
    private TextView txtCusto;
    private FloatingActionButton fbtnRemover;
    private double custoTotal;

    RecordListener RecorderCallback;
    public interface RecordListener{
        void onFbtnRemoverClick();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            RecorderCallback = (RecordListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " Deve implementar RecordListener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.record_fragment, container, false);
        adapter = new FreteAdapter(getActivity().getBaseContext(), freteArrayList);
        txtCusto = view.findViewById(R.id.txtCusto);
        txtCusto.setText(String.format(Locale.getDefault(),
                "%.2fR$", custoTotal));
        fbtnRemover = view.findViewById(R.id.fbtnRemover);
        fbtnRemover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RecorderCallback.onFbtnRemoverClick();
            }
        });
        recyclerView = view.findViewById(R.id.listaFretes);
        recyclerView.setLayoutManager(new LinearLayoutManager(
                getActivity().getBaseContext()));
        recyclerView.addItemDecoration(new DividerItemDecoration
                (getActivity().getBaseContext(), LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(adapter);
        return view;
    }

    public void presetValues(ArrayList<Frete> fretes, double custoTotal){
        freteArrayList = fretes;
        this.custoTotal = custoTotal;
    }

}

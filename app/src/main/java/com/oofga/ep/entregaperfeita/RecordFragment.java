package com.oofga.ep.entregaperfeita;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.oofga.ep.utilidade.Frete;
import com.oofga.ep.utilidade.FreteAdapter;

import java.util.ArrayList;

public class RecordFragment extends Fragment {
    private RecyclerView recyclerView;
    private FreteAdapter adapter;
    private ArrayList<Frete> freteArrayList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.record_fragment, container, false);
        adapter = new FreteAdapter(getActivity().getBaseContext(), freteArrayList);
        recyclerView = view.findViewById(R.id.listaFretes);
        recyclerView.setLayoutManager(new LinearLayoutManager(
                getActivity().getBaseContext()));
        recyclerView.addItemDecoration(new DividerItemDecoration
                (getActivity().getBaseContext(), LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(adapter);
        return view;
    }

    public void presetValues(ArrayList<Frete> fretes){
        freteArrayList = fretes;
    }

}

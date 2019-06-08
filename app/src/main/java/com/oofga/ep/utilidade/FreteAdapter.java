package com.oofga.ep.utilidade;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.oofga.ep.entregaperfeita.R;

import java.util.ArrayList;

public class FreteAdapter extends RecyclerView.Adapter<FreteHolder> {
    private Context context;

    private ArrayList<Frete> fretes;

    public FreteAdapter(Context context, ArrayList<Frete> fretes){
        this.context = context;
        this.fretes = fretes;
    }

    @Override
    public FreteHolder onCreateViewHolder(ViewGroup viewGroup, int a){
        View view = LayoutInflater.from(context).inflate(R.layout.item_row,
                viewGroup, false);
        return new FreteHolder(view);
    }

    @Override
    public void onBindViewHolder(FreteHolder holder, int pos){
        Frete frete = fretes.get(pos);
        holder.setDetails(frete);
    }

    @Override
    public int getItemCount(){
        return fretes.size();
    }
}

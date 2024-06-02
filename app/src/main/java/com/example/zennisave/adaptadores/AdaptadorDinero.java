package com.example.zennisave.adaptadores;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.zennisave.R;
import com.example.zennisave.entidades.Total;

import java.util.ArrayList;

public class AdaptadorDinero extends RecyclerView.Adapter<AdaptadorDinero.TotalViewHolder> {
    ArrayList<Total> listaDineroT;

    public AdaptadorDinero(ArrayList<Total> listaDineroT) {
        this.listaDineroT = listaDineroT;
    }
    @NonNull
    @Override
    public TotalViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_objeto_dinerototal, null, false);
        return new TotalViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TotalViewHolder holder, int position) {
        holder.Dtotal.setText(String.valueOf(listaDineroT.get(position).getDineroT()));
    }

    @Override
    public int getItemCount() {
       return listaDineroT.size();
    }

    public class TotalViewHolder extends RecyclerView.ViewHolder {
        TextView Dtotal;
        public TotalViewHolder(@NonNull View itemView) {
            super(itemView);
            Dtotal=itemView.findViewById(R.id.Dtotal);
        }
    }
}

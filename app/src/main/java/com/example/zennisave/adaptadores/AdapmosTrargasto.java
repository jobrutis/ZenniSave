package com.example.zennisave.adaptadores;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.zennisave.R;
import com.example.zennisave.entidades.Gastos;
import com.example.zennisave.entidades.Ingresos;

import java.text.BreakIterator;
import java.util.ArrayList;

public class AdapmosTrargasto extends RecyclerView.Adapter<AdapmosTrargasto.ResumGViewHolder> {
    ArrayList<Gastos> listagastos;
    public AdapmosTrargasto(ArrayList<Gastos> listagastos){
        this.listagastos=listagastos;
    }
    @NonNull
    @Override
    public ResumGViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_objeto_resumensemanagasto,null,false);
        return new ResumGViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ResumGViewHolder holder, int position) {
        holder.viewConcepto.setText(listagastos.get(position).getConcepto());
        holder.viewFecha.setText(listagastos.get(position).getFecha());
        holder.viewDinero.setText(String.valueOf(listagastos.get(position).getDinerogastos()));
    }
    @Override
    public int getItemCount() {
        return listagastos.size();
    }

    public static class ResumGViewHolder extends RecyclerView.ViewHolder {
        TextView viewConcepto,viewFecha,viewDinero;

        public ResumGViewHolder(@NonNull View itemView) {
            super(itemView);
            viewConcepto=itemView.findViewById(R.id.viewConcepto);
            viewFecha=itemView.findViewById(R.id.viewFecha);
            viewDinero=itemView.findViewById(R.id.viewDinero);
        }
    }
}
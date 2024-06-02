package com.example.zennisave.adaptadores;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.zennisave.R;
import com.example.zennisave.entidades.Ingresos;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class AdresumenSing extends RecyclerView.Adapter<AdresumenSing.ResumIViewHolder> {
    ArrayList<Ingresos> listaingreso;
    public AdresumenSing(ArrayList<Ingresos> listaingreso){
        this.listaingreso=listaingreso;
    }
    @NonNull
    @Override
    public ResumIViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_objeto_resumensemanalingreso,null,false);

        return new ResumIViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ResumIViewHolder holder, int position) {
        holder.viewConcepto.setText(listaingreso.get(position).getConcepto());
        holder.viewFecha.setText(listaingreso.get(position).getFecha());
        holder.viewDinero.setText(String.valueOf(listaingreso.get(position).getDineroingresos()));

    }

    @Override
    public int getItemCount() {
       return listaingreso.size();

    }

    public class ResumIViewHolder extends RecyclerView.ViewHolder {
        TextView viewConcepto,viewFecha,viewDinero;
        public ResumIViewHolder(@NonNull View itemView) {
            super(itemView);
            viewConcepto=itemView.findViewById(R.id.viewConcepto);
            viewFecha=itemView.findViewById(R.id.viewFecha);
            viewDinero=itemView.findViewById(R.id.viewDinero);
        }
    }
}

package com.example.zennisave.adaptadores;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.zennisave.R;
import com.example.zennisave.entidades.Ingresos;

import java.util.ArrayList;

public class Listaingresosadaptador extends RecyclerView.Adapter<Listaingresosadaptador.ingresoViewHolder> {
    ArrayList<Ingresos> listaingreso;
    public Listaingresosadaptador(ArrayList<Ingresos>listaingreso){
        this.listaingreso = listaingreso;
    }
    @NonNull
    @Override
    public ingresoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_resumen_m2, null,false);
        return new ingresoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ingresoViewHolder holder, int position) {
    holder.datos.setText(listaingreso.get(position).getConcepto());
    holder.datos.setText(listaingreso.get(position).getDineroingresos());
    }

    @Override
    public int getItemCount() {
        return listaingreso.size();
    }

    public class ingresoViewHolder extends RecyclerView.ViewHolder {
    TextView datos,listaD;
        public ingresoViewHolder(@NonNull View itemView) {
            super(itemView);
            datos=itemView.findViewById(R.id.datos);
            listaD=itemView.findViewById(R.id.listaDi);
        }
    }
}

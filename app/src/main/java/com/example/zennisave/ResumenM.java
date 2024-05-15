package com.example.zennisave;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.zennisave.DBGestion.Db_Ingresos;
import com.example.zennisave.adaptadores.Listaingresosadaptador;
import com.example.zennisave.entidades.Ingresos;

import java.util.ArrayList;

public class ResumenM extends AppCompatActivity {
RecyclerView listaingresos;
ArrayList<Ingresos> listaArrayI;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_resumen_m);
        listaingresos=findViewById(R.id.listaD);
        listaingresos.setLayoutManager(new LinearLayoutManager(this));
        Db_Ingresos listaI = new Db_Ingresos(ResumenM.this);
        listaArrayI=new ArrayList<>();
        Listaingresosadaptador adapter =new Listaingresosadaptador(listaI.Mostrardatos());
        listaingresos.setAdapter(adapter);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}
package com.example.zennisave;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.zennisave.DBGestion.DB_Gastos;
import com.example.zennisave.DBGestion.Db_Ingresos;
import com.example.zennisave.adaptadores.AdapmosTrargasto;
import com.example.zennisave.adaptadores.AdresumenSing;
import com.example.zennisave.entidades.Gastos;
import com.example.zennisave.entidades.Ingresos;

import java.util.ArrayList;

public class MovimientoDeDinero extends AppCompatActivity {
    RecyclerView resumenSingreso, resumenSgasto;
    ArrayList<Gastos> resumengastos;
    ArrayList<Ingresos>resumeningresos;
Button BatrasS;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_resumen_s);
        BatrasS =findViewById(R.id.AtrasS);

        resumenSingreso=findViewById(R.id.resumenSingreso);
        resumenSgasto=findViewById(R.id.resumenSgasto);
        resumenSingreso.setLayoutManager(new LinearLayoutManager(this));
        resumenSgasto.setLayoutManager(new LinearLayoutManager(this));
        Db_Ingresos dbIngresos=new Db_Ingresos(MovimientoDeDinero.this);
        DB_Gastos dbGastos=new DB_Gastos(MovimientoDeDinero.this);
        resumengastos=new ArrayList<>();
        resumeningresos=new ArrayList<>();
        AdresumenSing adaptadorI = new AdresumenSing(dbIngresos.Mostrardatos());
        resumenSingreso.setAdapter(adaptadorI);
        AdapmosTrargasto adaptadorG= new AdapmosTrargasto(dbGastos.Mostrardatosgastos());
        resumenSgasto.setAdapter(adaptadorG);
        BatrasS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(MovimientoDeDinero.this,PaginaPrincipal.class);
                startActivity(i);
            }
        });
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}
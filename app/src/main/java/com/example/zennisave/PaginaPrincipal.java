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

public class PaginaPrincipal extends AppCompatActivity {

    Button Bañadir,BResumenM,BResumenS, Benviar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_pagina_principal);
        Bañadir= findViewById(R.id.añadir);
        Benviar=findViewById(R.id.enviar);
        BResumenM=findViewById(R.id.ResumenM);
        BResumenS=findViewById(R.id.ResumenS);
        Bañadir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent i= new Intent(PaginaPrincipal.this,InsertarDinero.class);
                startActivity(i);
            }
        });
        BResumenM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(PaginaPrincipal.this,ResumenM.class);
                startActivity(i);
            }
        });
        BResumenS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(PaginaPrincipal.this,ResumenS.class);
                startActivity(i);
            }
        });
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.logo), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}
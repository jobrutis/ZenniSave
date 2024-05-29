package com.example.zennisave;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class PaginaPrincipal extends AppCompatActivity {

    Button Bañadir,BResumenM,BResumenS, Benviar;
    CalendarView calendario;
    SimpleDateFormat formato;
    Date formatDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_pagina_principal);
        Bañadir= findViewById(R.id.añadir);
        Benviar=findViewById(R.id.enviar);
        BResumenM=findViewById(R.id.ResumenM);
        BResumenS=findViewById(R.id.ResumenS);
        calendario = findViewById(R.id.calendario);
        formato =new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
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
        calendario.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int mes, int dia) {
                Calendar selectedDate = Calendar.getInstance();
                selectedDate.set(year, mes, dia);
                formatDate = selectedDate.getTime();
                Log.w("FECHA", String.valueOf(formatDate));
                //fecha.setText(formato.format(selectedDate.getTime())); // Asignar la fecha formateada al EditText fecha
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
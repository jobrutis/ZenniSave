package com.example.zennisave;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.zennisave.DBGestion.DB_Gastos;
import com.example.zennisave.DBGestion.DB_Total;
import com.example.zennisave.DBGestion.Db_Ingresos;
import com.example.zennisave.adaptadores.AdaptadorDinero;
import com.example.zennisave.entidades.Total;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.math.RoundingMode;
import java.util.Locale;

public class PaginaPrincipal extends AppCompatActivity {
    RecyclerView verTotal;

    ArrayList <Total> totallista;
    Button Bañadir,BResumenM,BResumenS, Benviar;
    EditText Cconcepto,Cdinero;
    CalendarView calendario;
    SimpleDateFormat formato;
    Date formatDate;
    private void limpiar(){
        Cdinero.setText("");
        Cconcepto.setText("");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_pagina_principal);
        Bañadir= findViewById(R.id.añadir);
        Benviar=findViewById(R.id.enviar);
        Cconcepto=findViewById(R.id.concepdar);
        Cdinero=findViewById(R.id.Dinerodar);
        BResumenM=findViewById(R.id.ResumenM);
        BResumenS=findViewById(R.id.ResumenS);
        verTotal=findViewById(R.id.verTotal);
        verTotal.setLayoutManager(new LinearLayoutManager(this));
        DB_Total pikachu=new DB_Total(PaginaPrincipal.this);
        totallista =new ArrayList<>();
        AdaptadorDinero adaptadorDinero= new AdaptadorDinero(pikachu.mostrarTotal());
        verTotal.setAdapter(adaptadorDinero);
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
        Benviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    DB_Gastos dbrestado = new DB_Gastos(PaginaPrincipal.this);
                    String conceptoStr = Cconcepto.getText().toString();
                    String fechaStr = "";
                if (formatDate != null) {
                    // Usa SimpleDateFormat para formatear la fecha correctamente
                    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
                    fechaStr = formatter.format(formatDate);
                } else {
                    Date hoy = new Date();
                    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
                    fechaStr = formatter.format(hoy);
                }
                String dineroStr = Cdinero.getText().toString();
                try {
                    // Convertir la cadena a BigDecimal y redondear a 2 decimales
                    BigDecimal dinero = new BigDecimal(dineroStr).setScale(2, RoundingMode.HALF_UP);
                    float dineroFloat = dinero.floatValue();
                    // Llamar a los métodos de la base de datos utilizando BigDecimal
                    dbrestado.restarDinero(dineroFloat, conceptoStr, fechaStr);
                    dbrestado.registrarGasto(dineroFloat);
                } catch (NumberFormatException e) {
                    System.err.println("Formato de número inválido: " + dineroStr);
                }
                    Toast.makeText(PaginaPrincipal.this,"Restado correctamente",Toast.LENGTH_LONG).show();
                    limpiar();

            }

        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.logo), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}
package com.example.zennisave;

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
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.zennisave.DBGestion.Db_Ingresos;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class InsertarDinero extends AppCompatActivity {
  Button atras;
    EditText dinero, concepto;
    Button Binsertar;
    CalendarView calendario;
    SimpleDateFormat formato;
    Date formatDate;
    private void limpiar(){
        dinero.setText("");
        concepto.setText("");
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_insertardinero);
        dinero= findViewById(R.id.dineroIns);
        Binsertar =findViewById(R.id.botoninsertar);
        concepto=findViewById(R.id.concepIn);
        calendario = findViewById(R.id.caleIngreso);
        formato =new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        atras= findViewById(R.id.Atras);
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

        atras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(InsertarDinero.this,PaginaPrincipal.class);
                startActivity(i);
            }
        });
        Binsertar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Db_Ingresos dbingresado = new Db_Ingresos(InsertarDinero.this);
                String conceptoStr = concepto.getText().toString();
                String fechaStr = formatDate.toString(); // Si tienes un TextView o EditText para la fecha
                String dineroStr = dinero.getText().toString();
                int dineroInt = Integer.parseInt(dineroStr);
                dbingresado.insertarDinero(dineroInt,conceptoStr,fechaStr);
                limpiar();
                Toast.makeText(InsertarDinero.this,"Insertado correctamente",Toast.LENGTH_LONG).show();
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });



    }

}


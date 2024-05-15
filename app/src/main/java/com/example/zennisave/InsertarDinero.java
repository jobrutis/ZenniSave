package com.example.zennisave;

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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

public class InsertarDinero extends AppCompatActivity {



    String fecha;
    private void limpiar(){
        dinero.setText("");
        concepto.setText("");
    }
    EditText dinero, concepto;
    Button Bdinero;
    CalendarView calendario;
    SimpleDateFormat formato;
    Date fechaDate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main2);
        dinero = (EditText) findViewById(R.id.dineroIns);
        concepto = (EditText) findViewById(R.id.concepIn);
        calendario = findViewById(R.id.caleIngreso);
        formato =new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        Bdinero = findViewById(R.id.botoninsertar);
        Bdinero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), (CharSequence) dinero,Toast.LENGTH_LONG).show();
                Db_Ingresos ingreso = new Db_Ingresos(InsertarDinero.this);
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                Date parsed = null;
                try {

                    parsed = sdf.parse(fecha);
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }

                java.sql.Date data = new java.sql.Date(parsed.getTime());

                ingreso.insertarDinero(Integer.parseInt(dinero.getText().toString()),concepto.getText().toString(),data);
            }
        });
        calendario.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                fecha = dayOfMonth+"/"+month+"/"+year;
                {
                    try {
                        fechaDate = formato.parse(fecha);
                    } catch (ParseException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

}

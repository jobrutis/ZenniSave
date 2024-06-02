package com.example.zennisave.DBGestion;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Calendar;

import androidx.annotation.Nullable;

import com.example.zennisave.InsertarDinero;
import com.example.zennisave.ResumenS;
import com.example.zennisave.entidades.Ingresos;

import java.text.SimpleDateFormat;
import java.sql.Date;
import java.util.ArrayList;

public class Db_Ingresos extends DBhelper {
    private static final String TAG = "Db_Ingresos";
    Context context;

    public Db_Ingresos(@Nullable InsertarDinero context){
        super((Context) context);
        this.context = context;
    }
    public Db_Ingresos(@Nullable ResumenS context){
        super((Context) context);
        this.context = context;
    }

    public void insertarDinero(float dineroingresos, String fecha, String concepto){
       try{
           DBhelper db =new DBhelper(context);
           SQLiteDatabase dbz= db.getWritableDatabase();
           ContentValues values = new ContentValues();
           values.put("concepto", concepto);
           values.put("fecha", fecha);
           values.put("dineroingresos", dineroingresos);
           dbz.insert(TABLE_INGRESOS, null, values);
           dbz.close();
       }catch (Exception ex){
           ex.toString();
       }
    }
    public void obtenerdinerototal(float ingreso) {
        DBhelper dbHelper = new DBhelper(context);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        SQLiteDatabase dbWritable = dbHelper.getWritableDatabase();
        ContentValues ingresoValues = new ContentValues();
        ingresoValues.put("monto", ingreso);
        dbWritable.insert(TABLE_INGRESOS, null, ingresoValues);
        String checkQuery = "SELECT dineroT FROM " + TABLE_DINEROTOTAL;
        Cursor checkCursor = db.rawQuery(checkQuery, null);
        BigDecimal dineroTotal = BigDecimal.ZERO;
        if (checkCursor.moveToFirst()) {
            dineroTotal = new BigDecimal(checkCursor.getString(0));
        }
        checkCursor.close();
        BigDecimal ingresoBD = new BigDecimal(Float.toString(ingreso)).setScale(2, RoundingMode.HALF_UP);
        dineroTotal = dineroTotal.add(ingresoBD);
        ContentValues values = new ContentValues();
        values.put("dineroT", dineroTotal.toPlainString());
        int rows = db.update(TABLE_DINEROTOTAL, values, null, null);
        if (rows == 0) {
            dbWritable.insert(TABLE_DINEROTOTAL, null, values);
        }
        db.close();
        dbWritable.close();
    }
    public ArrayList<Ingresos> Mostrardatos() {
        DBhelper dBhelper = new DBhelper(context);
        SQLiteDatabase db = dBhelper.getWritableDatabase();
        ArrayList<Ingresos> resumenMS = new ArrayList<>();
        Ingresos ingresos = null;
        Cursor cursoringreso = null;
        String fechaI="";
        String fechaF="";
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_WEEK, cal.getFirstDayOfWeek());

        String fechaInicioSemanaActual = new SimpleDateFormat("dd/MM/yyyy").format(cal.getTime());
        cal.add(Calendar.DATE, 6);
        String fechaFinSemanaActual = new SimpleDateFormat("dd/MM/yyyy").format(cal.getTime());
        String consultaSQL = "SELECT * FROM " + TABLE_INGRESOS + " WHERE fecha BETWEEN '" + fechaInicioSemanaActual + "' AND '" + fechaFinSemanaActual + "' ORDER BY fecha DESC";
        cursoringreso = db.rawQuery(consultaSQL, null);
        if (cursoringreso.moveToFirst()) {
            do {
                ingresos = new Ingresos();
                ingresos.setConcepto(cursoringreso.getString(1));
                ingresos.setFecha(Date.valueOf(cursoringreso.getString(2)));
                ingresos.setDineroingresos(cursoringreso.getFloat(3));
                resumenMS.add(ingresos);
            } while (cursoringreso.moveToNext());
        }
        cursoringreso.close();
        return resumenMS;
    }
}
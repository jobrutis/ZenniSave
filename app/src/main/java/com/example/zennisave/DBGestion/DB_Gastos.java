package com.example.zennisave.DBGestion;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Calendar;

import androidx.annotation.Nullable;

import com.example.zennisave.PaginaPrincipal;
import com.example.zennisave.MovimientoDeDinero;
import com.example.zennisave.entidades.Gastos;

import java.text.SimpleDateFormat;
import java.sql.Date;
import java.util.ArrayList;

public class DB_Gastos extends DBhelper {
    Context context;

    public DB_Gastos(@Nullable PaginaPrincipal context){
        super((Context) context);
        this.context = context;
    }
    public DB_Gastos(@Nullable MovimientoDeDinero context){
        super((Context) context);
        this.context = context;
    }

    public void restarDinero(float dineroGastos, String fecha, String concepto){
        try{
            DBhelper db =new DBhelper(context);
            SQLiteDatabase dbz= db.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("concepto", concepto);
            values.put("fecha", fecha);
            values.put("dinerogastos", dineroGastos);
            dbz.insert(TABLE_GASTOS, null, values);
            dbz.close();

        }catch (Exception ex){
            ex.toString();
        }
    }
    public void registrarGasto(float gasto) {
        DBhelper dbHelper = new DBhelper(context);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        SQLiteDatabase dbWritable = dbHelper.getWritableDatabase();
        ContentValues gastoValues = new ContentValues();
        gastoValues.put("monto", gasto);
        dbWritable.insert(TABLE_GASTOS, null, gastoValues);
        String checkQuery = "SELECT dineroT FROM " + TABLE_DINEROTOTAL;
        Cursor checkCursor = db.rawQuery(checkQuery, null);
        BigDecimal dineroTotal = BigDecimal.ZERO;
        if (checkCursor.moveToFirst()) {
            dineroTotal = new BigDecimal(checkCursor.getString(0));
        }
        checkCursor.close();
        BigDecimal gastoBD = new BigDecimal(Float.toString(gasto)).setScale(2, RoundingMode.HALF_UP);
        dineroTotal = dineroTotal.subtract(gastoBD);
        ContentValues values = new ContentValues();
        values.put("dineroT", dineroTotal.toPlainString());
        int rows = db.update(TABLE_DINEROTOTAL, values, null, null);
        if (rows == 0) {
            dbWritable.insert(TABLE_DINEROTOTAL, null, values);
        }
        db.close();
        dbWritable.close();
    }

    public ArrayList<Gastos> Mostrardatosgastos() {
        DBhelper dBhelper = new DBhelper(context);
        SQLiteDatabase db = dBhelper.getWritableDatabase();
        ArrayList<Gastos> resumenMS = new ArrayList<>();
        Gastos gastos = null;
        Cursor cursorgastos = null;
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_WEEK, cal.getFirstDayOfWeek());
        String fechaInicioSemanaActual = new SimpleDateFormat("dd/MM/yyyy").format(cal.getTime());
        cal.add(Calendar.DATE, 6);
        String fechaFinSemanaActual = new SimpleDateFormat("dd/MM/yyyy").format(cal.getTime());
        cursorgastos = db.rawQuery("SELECT * FROM " + TABLE_GASTOS, null);
        if (cursorgastos.moveToFirst()) {
            do {
                gastos = new Gastos();
                gastos.setId(cursorgastos.getInt(0));
                gastos.setConcepto(cursorgastos.getString(1));
                gastos.setFecha(cursorgastos.getString(2));
                gastos.setDinerogastos(cursorgastos.getFloat(3));
                resumenMS.add(gastos);
            } while (cursorgastos.moveToNext());
        }
        cursorgastos.close();
        return resumenMS;
    }
}

package com.example.zennisave.DBGestion;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.zennisave.InsertarDinero;
import com.example.zennisave.entidades.Ingresos;
import com.example.zennisave.entidades.Total;

import java.util.ArrayList;

public class Db_Ingresos extends DBhelper {
    private static final String TAG = "Db_Ingresos";
    Context context;

    public Db_Ingresos(@Nullable InsertarDinero context){
        super((Context) context);
        this.context = context;
    }

    public void insertarDinero(int dineroingresos, String fecha, String concepto){
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
    public void obtenerdinerototal(Integer ingreso) {
        DBhelper dbHelper = new DBhelper(context);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        SQLiteDatabase dbWritable = dbHelper.getWritableDatabase();


        ContentValues ingresoValues = new ContentValues();
        ingresoValues.put("monto", ingreso); // Asumiendo que el campo se llama "monto"
        dbWritable.insert(TABLE_INGRESOS, null, ingresoValues);


        String checkQuery = "SELECT dineroT FROM " + TABLE_DINEROTOTAL;
        Cursor checkCursor = db.rawQuery(checkQuery, null);
        int dineroTotal = 0;

        if (checkCursor.moveToFirst()) {
            dineroTotal = checkCursor.getInt(0);
        }
        checkCursor.close();
        dineroTotal += ingreso;
        ContentValues values = new ContentValues();
        values.put("dineroT", dineroTotal);
        int rows = db.update(TABLE_DINEROTOTAL, values, null, null);
        if (rows == 0) {
            dbWritable.insert(TABLE_DINEROTOTAL, null, values);
        }

        db.close();
        dbWritable.close();
    }



    public ArrayList<Ingresos>Mostrardatos(){
        DBhelper dBhelper = new DBhelper(context);
        SQLiteDatabase db = dBhelper.getWritableDatabase();
        ArrayList<Ingresos> resumenMS= new ArrayList<>();
        Ingresos ingresos= null;
        Cursor cursoringreso=null;
        cursoringreso=db.rawQuery("SELECT* FROM "+ TABLE_INGRESOS, null);
        if (cursoringreso.moveToFirst()){
            do{
                ingresos = new Ingresos();
                ingresos.setConcepto(cursoringreso.getString(1));
                ingresos.setDineroingresos(cursoringreso.getInt(3));
                resumenMS.add(ingresos);
            }while(cursoringreso.moveToNext());
        }
        cursoringreso.close();
        return resumenMS;
    }
}

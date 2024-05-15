package com.example.zennisave.DBGestion;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.zennisave.ResumenM;
import com.example.zennisave.entidades.Ingresos;

import java.util.ArrayList;
import java.util.Currency;
import java.util.Date;

public class Db_Ingresos extends DBhelper {
    private static final String TAG = "Db_Dinerototal";
    Context context;

    public Db_Ingresos(@Nullable Context context){
        super(context);
        this.context = context;
    }

    public long insertarDinero(int dineroT, String dineroIngresos, Date fecha){
        long id = 0;
        try {
            Log.w("TAG", "Este es un mensaje de advertencia.");
            DBhelper dBhelper = new DBhelper(context);
            SQLiteDatabase db = dBhelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("concepto", dineroT);
            values.put("fecha", fecha.getTime()); // Almacenar la fecha como long
            values.put("dineroingresos", dineroIngresos);
            id = db.insert("TABLE_INGRESOS", null, values);

        } catch (Exception ex) {
            Log.e(TAG, "Error insertando datos" + ex.toString());
        }
        return id;
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

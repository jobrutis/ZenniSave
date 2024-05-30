package com.example.zennisave.DBGestion;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.example.zennisave.InsertarDinero;
import com.example.zennisave.PaginaPrincipal;

public class DB_Gastos extends DBhelper {
    Context context;

    public DB_Gastos(@Nullable PaginaPrincipal context){
        super((Context) context);
        this.context = context;
    }
    public void restarDinero(int dineroGastos, String fecha, String concepto){
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
    public void registrarGasto(Integer gasto) {
        DBhelper dbHelper = new DBhelper(context);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        SQLiteDatabase dbWritable = dbHelper.getWritableDatabase();

        // 1. Insertar el nuevo gasto en TABLE_GASTOS
        ContentValues gastoValues = new ContentValues();
        gastoValues.put("monto", gasto); // Asumiendo que el campo se llama "monto"
        dbWritable.insert(TABLE_GASTOS, null, gastoValues);

        // 2. Obtener el valor actual de dineroT en TABLE_DINEROTOTAL
        String checkQuery = "SELECT dineroT FROM " + TABLE_DINEROTOTAL;
        Cursor checkCursor = db.rawQuery(checkQuery, null);
        int dineroTotal = 0;

        if (checkCursor.moveToFirst()) {
            dineroTotal = checkCursor.getInt(0);
        }
        checkCursor.close();

        // 3. Restar el valor del parámetro gasto del dineroTotal
        dineroTotal -= gasto;

        // 4. Preparar los valores para insertar o actualizar
        ContentValues values = new ContentValues();
        values.put("dineroT", dineroTotal);

        // 5. Comprobar si TABLE_DINEROTOTAL ya tiene registros
        int rows = db.update(TABLE_DINEROTOTAL, values, null, null);
        if (rows == 0) {
            // Si no existe, insertar el nuevo valor
            dbWritable.insert(TABLE_DINEROTOTAL, null, values);
        }

        db.close();
        dbWritable.close();
    }




}

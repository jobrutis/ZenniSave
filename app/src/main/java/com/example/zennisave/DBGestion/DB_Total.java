package com.example.zennisave.DBGestion;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.example.zennisave.entidades.Total;

import java.util.ArrayList;

public class DB_Total extends DBhelper{
    Context context;
    public DB_Total(@Nullable Context context) {
        super((Context) context);
        this.context = context;
    }
    public ArrayList<Total> mostrarTotal(){

        DBhelper dbHelper = new DBhelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ArrayList<Total> totaldinero = new ArrayList<>();
        Total dineroT = null;
        Cursor cursorTotal=null;

        cursorTotal = db.rawQuery("SELECT * FROM " + TABLE_DINEROTOTAL, null);

        if(cursorTotal.moveToFirst()){
            do{
                dineroT = new Total();
                dineroT.setId(cursorTotal.getInt(0));
                dineroT.setDineroT(cursorTotal.getFloat(1));
                totaldinero.add(dineroT);
            } while (cursorTotal.moveToNext());
        }
        cursorTotal.close();

        return totaldinero;
    }
}

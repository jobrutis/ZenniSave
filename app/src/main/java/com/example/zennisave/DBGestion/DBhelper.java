package com.example.zennisave.DBGestion;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBhelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION =2;
    private static final String DATABASE_NOMBRE ="DineroZ.db";
    public static final String TABLE_DINEROTOTAL ="t_finanzas";//Dinero que se va guardar
    public static final String TABLE_GASTOS ="t_gastos";//dinero que se va a gastar
    public static final String TABLE_INGRESOS ="t_ingreso";


    public DBhelper(@Nullable Context context) {
        super(context, DATABASE_NOMBRE, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_DINEROTOTAL + " (" +
                "id_dinerototal INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "dineroT FLOAT NOT NULL)");

        db.execSQL("CREATE TABLE " + TABLE_GASTOS + " (" +
                "id_gastos INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "concepto TEXT NOT NULL, " +
                "fecha TEXT NOT NULL, " +
                "dinerogastos FLOAT NOT NULL)");

        db.execSQL("CREATE TABLE " + TABLE_INGRESOS + " (" +
                "id_ingresos INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "concepto TEXT NOT NULL, " +
                "fecha TEXT NOT NULL, " +
                "dineroingresos FLOAT NOT NULL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE "+ TABLE_INGRESOS);
        db.execSQL("DROP TABLE "+ TABLE_GASTOS);
        db.execSQL("DROP TABLE "+ TABLE_DINEROTOTAL);
        onCreate(db);
    }
}

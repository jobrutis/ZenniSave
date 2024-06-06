package com.example.zennisave.entidades;

import java.sql.Date;

public class Gastos {
    private int id;
    private String concepto;
    private String fecha;
    private float dinerogastos;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getConcepto() {
        return concepto;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public float getDinerogastos() {
        return dinerogastos;
    }

    public void setDinerogastos(float dinerogastos) {
        this.dinerogastos = dinerogastos;
    }
}


package com.example.zennisave.entidades;

import java.sql.Date;

public class Ingresos {
    private int id;
    private String concepto;
    private Date fecha;
    private float dineroingresos;

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

    public CharSequence getFecha() {
        return (CharSequence) fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public float getDineroingresos() {
        return dineroingresos;
    }

    public void setDineroingresos(float dineroingresos) {
        this.dineroingresos = dineroingresos;
    }
}

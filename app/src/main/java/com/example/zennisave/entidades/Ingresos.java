package com.example.zennisave.entidades;

import java.sql.Date;

public class Ingresos {
    private int id;
    private String concepto;
    private Date fecha;
    private int dineroingresos;

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

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getDineroingresos() {
        return dineroingresos;
    }

    public void setDineroingresos(int dineroingresos) {
        this.dineroingresos = dineroingresos;
    }
}

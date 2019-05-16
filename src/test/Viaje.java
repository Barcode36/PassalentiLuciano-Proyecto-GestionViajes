/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package test;

import java.sql.Date;

/**
 *
 * @author DAW
 */
public class Viaje {
    private int idViaje;
    private String tipo;
    private int duracion;
    private int duracionTotal;
    private int idSalida;
    private int idLlegada;
    private int kilometros;
    private Object fechaLlegada;
    private Object fechaSalida;
    
//    java.util.Date dt = new java.util.Date();
//    java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//    String currentTime = sdf.format(dt);

    public Viaje(String tipo, int duracion, int duracionTotal, int idSalida, int idLlegada, int kilometros, Object fechaLlegada, Object fechaSalida) {
        this.idViaje = idViaje;
        this.tipo = tipo;
        this.duracion = duracion;
        this.duracionTotal = duracionTotal;
        this.idSalida = idSalida;
        this.idLlegada = idLlegada;
        this.kilometros = kilometros;
        this.fechaLlegada = fechaLlegada;
        this.fechaSalida = fechaSalida;
    }
    
    public Viaje(int idViaje, String tipo, int duracion, int duracionTotal, int idSalida, int idLlegada, int kilometros, Object fechaLlegada, Object fechaSalida) {
        this.idViaje = idViaje;
        this.tipo = tipo;
        this.duracion = duracion;
        this.duracionTotal = duracionTotal;
        this.idSalida = idSalida;
        this.idLlegada = idLlegada;
        this.kilometros = kilometros;
        this.fechaLlegada = fechaLlegada;
        this.fechaSalida = fechaSalida;
    }

    public int getIdViaje() {
        return idViaje;
    }

    public String getTipo() {
        return tipo;
    }

    public int getDuracion() {
        return duracion;
    }

    public int getDuracionTotal() {
        return duracionTotal;
    }

    public int getIdSalida() {
        return idSalida;
    }

    public int getIdLlegada() {
        return idLlegada;
    }

    public int getKilometros() {
        return kilometros;
    }

    public Object getFechaLlegada() {
        return fechaLlegada;
    }

    public Object getFechaSalida() {
        return fechaSalida;
    }

    public void setIdViaje(int idViaje) {
        this.idViaje = idViaje;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public void setDuracionTotal(int duracionTotal) {
        this.duracionTotal = duracionTotal;
    }

    public void setIdSalida(int idSalida) {
        this.idSalida = idSalida;
    }

    public void setIdLlegada(int idLlegada) {
        this.idLlegada = idLlegada;
    }

    public void setKilometros(int kilometros) {
        this.kilometros = kilometros;
    }

    public void setFechaLlegada(Object fechaLlegada) {
        this.fechaLlegada = fechaLlegada;
    }

    public void setFechaSalida(Object fechaSalida) {
        this.fechaSalida = fechaSalida;
    }
    
    
    
}

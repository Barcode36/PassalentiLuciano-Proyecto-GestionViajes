/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package test;

import java.util.HashMap;
import model.Database;

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
    private String nombreSalida;
    private int idLlegada;
    private String nombreLlegada;
    private int kilometros;
    private Object fechaLlegada;
    private Object fechaSalida;
    private String duracionFormato;
    private String duracionTotalFormato;
    private Double gastoTotal;
    
//    java.util.Date dt = new java.util.Date();
//    java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//    String currentTime = sdf.format(dt);
    
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
        this.nombreSalida = idToNombreLugar(idSalida);
        this.nombreLlegada = idToNombreLugar(idLlegada);
        this.duracionFormato = getDuracionEnFormato(duracion);
        this.duracionTotalFormato = getDuracionEnFormato(duracionTotal);
        this.gastoTotal = getGastoTotal(idViaje);
    }
    public Double getGastoTotal(int idViaje){
        return getCostoPeajes()+getCostoCombustible();
    }
    public double getCostoPeajes(){
        Double retorno=(double)0;
        Double costePeaje = (Double) Database.consulta("SELECT SUM(p.costo) AS suma FROM peaje p WHERE p.idViaje = ?", new Object[]{idViaje}).get(0).get("suma");
        if(costePeaje!=null){
            retorno+=costePeaje;
        }
        return retorno;
    }
    public double getCostoCombustible(){
        Double retorno=(double)0;
        Double costeCombustible = (Double) Database.consulta("SELECT SUM(c.precio) AS suma FROM combustible c WHERE c.idViaje = ?", new Object[]{idViaje}).get(0).get("suma");
        if(costeCombustible!=null){
            retorno+= costeCombustible;
        }
        return retorno;
    }
    public double getGastoTotal(){
        return gastoTotal;
    }
    
    private String idToNombreLugar(int id){
        HashMap<Integer,HashMap<String,Object>> salida = Database.consulta("SELECT * FROM lugar WHERE idLugar = ?", new Object[]{id});
        return (String)salida.get(0).get("ciudad")+"/"+(String)salida.get(0).get("direccion")+" "+(int)salida.get(0).get("nDireccion");
    }
    
    public String getNombreSalida() {
        return nombreSalida;
    }
    
    public String getNombreLlegada() {
        return nombreLlegada;
    }
    
    public String getDuracionEnFormato(int segundos){
        String seg="",min="",hr="";
        int horas =0,minutos=0;
        
        while(segundos>=60){
            if(segundos>=3600) {
                horas++;
                segundos-=3600;
            }
            else if(segundos>=60) {
                minutos++;
                segundos-=60;
            }
        }
        if(segundos<=9) {seg = "0";}
        if(minutos<=9) {min = "0";}
        if(horas<=9) {hr = "0";}
        
        return (hr+horas+":"+min+minutos+":"+seg+segundos);
    }
    
    public String getDuracionFormato() {
        return duracionFormato;
    }
    public String getDuracionTotalFormato() {
        return duracionTotalFormato;
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

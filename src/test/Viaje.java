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
    private Lugar salida;
    private Lugar llegada;
    
    /**
     *
     * @param idViaje
     * @param tipo
     * @param duracion
     * @param duracionTotal
     * @param idSalida
     * @param idLlegada
     * @param kilometros
     * @param fechaLlegada
     * @param fechaSalida
     */
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
        this.salida = new Lugar(idSalida);
        this.llegada = new Lugar(idLlegada);
    }

    /**
     *
     * @return El objeto de Tipo Lugar correspondiente a la salida del viaje
     */
    public Lugar getSalida(){
        return this.salida;
    }

    /**
     *
     * @return El objeto de Tipo Lugar correspondiente a la llegada del viaje
     */
    public Lugar getLlegada(){
        return this.llegada;
    }
    
    /**
     *
     * @param idViaje
     * @return La suma de todos los peajes y todas las cargas de combustible del viaje dado un id
     */
    public Double getGastoTotal(int idViaje){
        return getCostoPeajes()+getCostoCombustible();
    }

    /**
     *
     * @return El costo total de todos los peajes del viaje
     */
    public double getCostoPeajes(){
        Double retorno=(double)0;
        Double costePeaje = (Double) Database.consulta("SELECT SUM(p.costo) AS suma FROM peaje p WHERE p.idViaje = ?", new Object[]{idViaje}).get(0).get("suma");
        if(costePeaje!=null){
            retorno+=costePeaje;
        }
        return retorno;
    }

    /**
     *
     * @return El costo total de todos las cargas de combustible del viaje
     */
    public double getCostoCombustible(){
        Double retorno=(double)0;
        Double costeCombustible = (Double) Database.consulta("SELECT SUM(c.precio) AS suma FROM combustible c WHERE c.idViaje = ?", new Object[]{idViaje}).get(0).get("suma");
        if(costeCombustible!=null){
            retorno+= costeCombustible;
        }
        return retorno;
    }

    /**
     *
     * @return La suma de todos los peajes y todas las cargas de combustible del viaje
     */
    public double getGastoTotal(){  
        return gastoTotal;
    }
    
    /**
     *
     * @param id
     * @return la ciudad/direccion nDireccion de un lugar dado un id
     */
    public String idToNombreLugar(int id){
        HashMap<Integer,HashMap<String,Object>> salida = Database.consulta("SELECT * FROM lugar WHERE idLugar = ?", new Object[]{id});
        return (String)salida.get(0).get("ciudad")+"/"+(String)salida.get(0).get("direccion")+" "+(int)salida.get(0).get("nDireccion");
    }
    
    /**
     *
     * @return la salida en formado String (ciudad/direccion nDireccion)
     */
    public String getNombreSalida() {
        return nombreSalida;
    }
    
    /**
     *
     * @return la llegada en formado String (ciudad/direccion nDireccion)
     */
    public String getNombreLlegada() {
        return nombreLlegada;
    }
    
    /**
     *
     * @param segundos
     * @return convierte segundos a (hh:mm:ss)
     */
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
    
    /**
     *
     * @return la duracion del viaje (hh:mm:ss)
     */
    public String getDuracionFormato() {
        return duracionFormato;
    }

    /**
     *
     * @return la duracionTotal del viaje (hh:mm:ss)
     */
    public String getDuracionTotalFormato() {
        return duracionTotalFormato;
    }
    
    /**
     *
     * @return el id del viaje
     */
    public int getIdViaje() {
        return idViaje;
    }
    
    /**
     *
     * @return el tipo de viaje
     */
    public String getTipo() {
        return tipo;
    }
    
    /**
     *
     * @return la duracion en segundos
     */
    public int getDuracion() {
        return duracion;
    }
    
    /**
     *
     * @return la duracionTotal en segundos
     */
    public int getDuracionTotal() {
        return duracionTotal;
    }
    
    /**
     *
     * @return el id del lugar de salida
     */
    public int getIdSalida() {
        return idSalida;
    }
    
    /**
     *
     * @return el id del lugar de llegada
     */
    public int getIdLlegada() {
        return idLlegada;
    }
    
    /**
     *
     * @return los kilometros del viaje
     */
    public int getKilometros() {
        return kilometros;
    }
    
    /**
     *
     * @return el objeto de tipo datetime de la fecha/hora de llegada
     */
    public Object getFechaLlegada() {
        return fechaLlegada;
    }
    
    /**
     *
     * @return el objeto de tipo datetime de la fecha/hora de salida
     */
    public Object getFechaSalida() {
        return fechaSalida;
    }
    
    /**
     *
     * @param idViaje
     */
    public void setIdViaje(int idViaje) {
        this.idViaje = idViaje;
    }
    
    /**
     *
     * @param tipo
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
    /**
     *
     * @param duracion
     */
    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }
    
    /**
     *
     * @param duracionTotal
     */
    public void setDuracionTotal(int duracionTotal) {
        this.duracionTotal = duracionTotal;
    }
    
    /**
     *
     * @param idSalida
     */
    public void setIdSalida(int idSalida) {
        this.idSalida = idSalida;
    }
    
    /**
     *
     * @param idLlegada
     */
    public void setIdLlegada(int idLlegada) {
        this.idLlegada = idLlegada;
    }
    
    /**
     *
     * @param kilometros
     */
    public void setKilometros(int kilometros) {
        this.kilometros = kilometros;
    }
    
    /**
     *
     * @param fechaLlegada
     */
    public void setFechaLlegada(Object fechaLlegada) {
        this.fechaLlegada = fechaLlegada;
    }
    
    /**
     *
     * @param fechaSalida
     */
    public void setFechaSalida(Object fechaSalida) {
        this.fechaSalida = fechaSalida;
    }
    
    
    
}

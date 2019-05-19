/*
 To change this license header, choose License Headers in Project Properties.
 To change this template file, choose Tools | Templates
 and open the template in the editor.
 */
package test;

/**

 @author Luciano
 */
public class Peaje{
    private int idPeaje;
    private int idViaje;
    private double costo;
    private Object fecha;
    
    public Peaje(int idPeaje, int idViaje, double costo, Object fecha){
        this.idPeaje = idPeaje;
        this.idViaje = idViaje;
        this.costo = costo;
        this.fecha = fecha;
    }
    
    public int getIdPeaje(){
        return idPeaje;
    }
    public void setIdPeaje(int idPeaje){
        this.idPeaje = idPeaje;
    }
    public int getIdViaje(){
        return idViaje;
    }
    public void setIdViaje(int idViaje){
        this.idViaje = idViaje;
    }
    public double getCosto(){
        return costo;
    }
    public void setCosto(double costo){
        this.costo = costo;
    }
    public Object getFecha(){
        return fecha;
    }
    public void setFecha(Object fecha){
        this.fecha = fecha;
    }
    
    
    
}

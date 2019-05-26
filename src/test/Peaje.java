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
    
    /**
     *
     * @param idPeaje
     * @param idViaje
     * @param costo
     * @param fecha
     */
    public Peaje(int idPeaje, int idViaje, double costo, Object fecha){
        this.idPeaje = idPeaje;
        this.idViaje = idViaje;
        this.costo = costo;
        this.fecha = fecha;
    }
    
    /**
     *
     * @return el id del peaje
     */
    public int getIdPeaje(){
        return idPeaje;
    }

    /**
     *
     * @param idPeaje
     */
    public void setIdPeaje(int idPeaje){
        this.idPeaje = idPeaje;
    }

    /**
     *
     * @return
     */
    public int getIdViaje(){
        return idViaje;
    }

    /**
     *
     * @param idViaje
     */
    public void setIdViaje(int idViaje){
        this.idViaje = idViaje;
    }

    /**
     *
     * @return
     */
    public double getCosto(){
        return costo;
    }

    /**
     *
     * @param costo
     */
    public void setCosto(double costo){
        this.costo = costo;
    }

    /**
     *
     * @return
     */
    public Object getFecha(){
        return fecha;
    }

    /**
     *
     * @param fecha
     */
    public void setFecha(Object fecha){
        this.fecha = fecha;
    }
    
    
    
}

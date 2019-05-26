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
public class Lugar {
    private int iDlugar;
    private String ciudad;
    private String direccion;
    private int nDireccion;

    /**
     *
     * @param iDlugar
     * @param ciudad
     * @param direccion
     * @param nDireccion
     */
    public Lugar(int iDlugar, String ciudad, String direccion, int nDireccion) {
        this.iDlugar = iDlugar;
        this.ciudad = ciudad;
        this.direccion = direccion;
        this.nDireccion = nDireccion;
    }

    /**
     * Construye un lugar con un id.
     * @param id
     */
    public Lugar(int id){
        HashMap<Integer,HashMap<String,Object>> salida = Database.consulta("SELECT * FROM lugar WHERE idLugar = ?", new Object[]{id});
        this.iDlugar = id;
        this.ciudad = (String)salida.get(0).get("ciudad");
        this.direccion = (String)salida.get(0).get("direccion");
        this.nDireccion = (int)salida.get(0).get("nDireccion");
    }

    /**
     * Constructor Default con datos invalidos.  
     */
    public Lugar(){
        this.iDlugar = -1;
        this.ciudad = "Invalido";
        this.direccion = "Invalido";
        this.nDireccion = 000;
    }
    
    /**
    * ciudad/direccion nDireccion.
    * @return String
    */
    @Override
    public String toString() {
        return ciudad + "/" + direccion +" "+ nDireccion;
    }
    
    /**
     * El id del lugar.
     * @return Integer
     */
    public Integer getiDlugar() {
        return iDlugar;
    }

    /**
     * El nombre de la ciudad.
     * @return String
     */
    public String getCiudad() {
        return ciudad;
    }

    /**
     * La direccion.
     * @return String
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * El numero de la direccion.
     * @return int
     */
    public int getnDireccion() {
        return nDireccion;
    }

    /**
     *
     * @param iDlugar
     */
    public void setiDlugar(int iDlugar) {
        this.iDlugar = iDlugar;
    }

    /**
     *
     * @param ciudad
     */
    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    /**
     *
     * @param direccion
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    /**
     *
     * @param nDireccion
     */
    public void setnDireccion(int nDireccion) {
        this.nDireccion = nDireccion;
    }
    
    
    
}

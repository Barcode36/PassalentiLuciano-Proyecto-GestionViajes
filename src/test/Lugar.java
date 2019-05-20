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

    public Lugar(int iDlugar, String ciudad, String direccion, int nDireccion) {
        this.iDlugar = iDlugar;
        this.ciudad = ciudad;
        this.direccion = direccion;
        this.nDireccion = nDireccion;
    }
    public Lugar(int id){
        HashMap<Integer,HashMap<String,Object>> salida = Database.consulta("SELECT * FROM lugar WHERE idLugar = ?", new Object[]{id});
        this.iDlugar = id;
        this.ciudad = (String)salida.get(0).get("ciudad");
        this.direccion = (String)salida.get(0).get("direccion");
        this.nDireccion = (int)salida.get(0).get("nDireccion");
    }
    @Override
    public String toString() {
        return ciudad + "/" + direccion +" "+ nDireccion;
    }
    
    public Integer getiDlugar() {
        return iDlugar;
    }

    public String getCiudad() {
        return ciudad;
    }

    public String getDireccion() {
        return direccion;
    }

    public int getnDireccion() {
        return nDireccion;
    }

    public void setiDlugar(int iDlugar) {
        this.iDlugar = iDlugar;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setnDireccion(int nDireccion) {
        this.nDireccion = nDireccion;
    }
    
    
    
}

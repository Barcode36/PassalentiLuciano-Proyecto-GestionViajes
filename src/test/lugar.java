/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

/**
 *
 * @author DAW
 */
public class lugar {
    private String iDlugar;
    private String ciudad;
    private String direccion;
    private int nDireccion;

    public lugar(String iDlugar, String ciudad, String direccion, int nDireccion) {
        this.iDlugar = iDlugar;
        this.ciudad = ciudad;
        this.direccion = direccion;
        this.nDireccion = nDireccion;
    }

    @Override
    public String toString() {
        return ciudad + "/" + direccion +" "+ nDireccion;
    }
    
    public String getiDlugar() {
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

    public void setiDlugar(String iDlugar) {
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

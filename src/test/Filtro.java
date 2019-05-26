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
public class Filtro {
    private String nombre;
    private String value;

    /**
     *
     * @param nombre
     * @param value
     */
    public Filtro(String nombre, String value) {
        this.nombre = nombre;
        this.value = value;
    }

    @Override
    public String toString() {
        return nombre;
    }
    
    /**
     *
     * @return
     */
    public String getNombre() {
        return nombre;
    }

    /**
     *
     * @param nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     *
     * @return
     */
    public String getValue() {
        return value;
    }

    /**
     *
     * @param value
     */
    public void setValue(String value) {
        this.value = value;
    }
    
}

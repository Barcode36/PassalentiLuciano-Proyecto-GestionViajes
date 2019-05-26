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
     * @param nombre nombre que se va a mostrar en el comboBox
     * @param value nombre que tiene la columna en la base de datos
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
     * @return el nombre del filtro
     */
    public String getNombre() {
        return nombre;
    }

    /**
     *
     * @param nombre nombre que se va a mostrar en el comboBox
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     *
     * @return String value
     */
    public String getValue() {
        return value;
    }

    /**
     *
     * @param value Nombre que tiene la columna en la base de datos
     */
    public void setValue(String value) {
        this.value = value;
    }
    
}

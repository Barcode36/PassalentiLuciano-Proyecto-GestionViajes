/*
 To change this license header, choose License Headers in Project Properties.
 To change this template file, choose Tools | Templates
 and open the template in the editor.
 */
package test;

/**

 @author Luciano
 */
public class Combustible{
    private int idCombustible;
    private int idViaje;
    private double litros;
    private int kilometros;
    private double precio;
    private Object fecha;
    
    /**
     *
     * @param idCombustible
     * @param idViaje
     * @param litros
     * @param kilometros
     * @param precio
     * @param fecha
     */
    public Combustible(int idCombustible, int idViaje, double litros, int kilometros, double precio, Object fecha){
        this.idCombustible = idCombustible;
        this.idViaje = idViaje;
        this.litros = litros;
        this.kilometros = kilometros;
        this.precio = precio;
        this.fecha = fecha;
    }
    
    /**
     * 
     * @return int
     */
    public int getIdCombustible(){
        return idCombustible;
    }

    /**
     *
     * @return int
     */
    public int getIdViaje(){
        return idViaje;
    }

    /**
     *
     * @return double
     */
    public double getLitros(){
        return litros;
    }

    /**
     *
     * @return int
     */
    public int getKilometros(){
        return kilometros;
    }

    /**
     *
     * @return double
     */
    public double getPrecio(){
        return precio;
    }

    /**
     *
     * @return datetime
     */
    public Object getFecha(){
        return fecha;
    }
    
    
}

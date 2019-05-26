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
     * Constructor.
     * @param idCombustible int que reprensenta su id en la bd.
     * @param idViaje int que reprensenta el id en la bd que hace referencia al viaje con el que se relaciona.
     * @param litros double que representa la cant de litros.
     * @param kilometros int que representa los kilometros.
     * @param precio double que representa el precio
     * @param fecha Objeto datetime que representa la fecha
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

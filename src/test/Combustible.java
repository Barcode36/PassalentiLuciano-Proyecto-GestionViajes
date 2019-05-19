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
    
    public Combustible(int idCombustible, int idViaje, double litros, int kilometros, double precio, Object fecha){
        this.idCombustible = idCombustible;
        this.idViaje = idViaje;
        this.litros = litros;
        this.kilometros = kilometros;
        this.precio = precio;
        this.fecha = fecha;
    }
    
    public int getIdCombustible(){
        return idCombustible;
    }
    public int getIdViaje(){
        return idViaje;
    }
    public double getLitros(){
        return litros;
    }
    public int getKilometros(){
        return kilometros;
    }
    public double getPrecio(){
        return precio;
    }
    public Object getFecha(){
        return fecha;
    }
    
    
}

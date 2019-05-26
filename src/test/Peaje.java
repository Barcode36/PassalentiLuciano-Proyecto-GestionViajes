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
     * @param idPeaje int id que corresponde al peaje
     * @param idViaje int id del viaje que le corresponde en la bd
     * @param costo double costo
     * @param fecha Object datetime en el que fue efectuado
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
     * @return
     */
    public int getIdViaje(){
        return idViaje;
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
     * @return
     */
    public Object getFecha(){
        return fecha;
    }
    
    
    
}

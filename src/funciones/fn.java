/*
 To change this license header, choose License Headers in Project Properties.
 To change this template file, choose Tools | Templates
 and open the template in the editor.
 */
package funciones;

/**

 @author Luciano
 */
public class fn{
    
    /**
    * Checkea si es un numero entero
    * @param s String
    * @return true si lo es
    */
    public static boolean checkINT(String s){
        boolean valido;
        try{
            Integer.parseInt(s);
            valido=true;
        }
        catch (Exception e){
            valido=false;
            System.out.println(e.getMessage());
        }
        return valido;
    }
    
    /**
    * checkea si es un numero double
    * @param s String
    * @return true si lo es
    */
    public static boolean checkDouble(String s){
        boolean valido;
        try{
            Double.parseDouble(s);
            valido=true;
        }
        catch (Exception e){
            valido=false;
            System.out.println(e.getMessage());
        }
        return valido;
    }
    
}

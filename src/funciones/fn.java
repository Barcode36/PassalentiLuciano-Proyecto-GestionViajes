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
    
    public static boolean checkINT(String s){
        //checkear si es un numero
        //retorna true si lo es
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
}

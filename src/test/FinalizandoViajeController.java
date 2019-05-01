/*
 To change this license header, choose License Headers in Project Properties.
 To change this template file, choose Tools | Templates
 and open the template in the editor.
 */
package test;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;

/**
 FXML Controller class

 @author Luciano
 */
public class FinalizandoViajeController implements Initializable{

    /**
     Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb){
        
        
        
        
    }
    public void loadData(Object[] datosViaje, ArrayList<Object[]> combustible, ArrayList<Object[]> peajes){
        
        System.out.println("--------Datos del viaje:------");
        for (int i = 0; i < datosViaje.length; i++){
            if(datosViaje[i]!=null){
                System.out.println(datosViaje[i].toString());
            }
        }
       
        System.out.println("--------Cargas de combustible------");
        for (Object[] obj : combustible){
            for (int i = 0; i < obj.length; i++){
                System.out.println(obj[i].toString());
            }
        }
        
        System.out.println("--------Peajes------");
        for (Object[] obj : peajes){
            for (int i = 0; i < obj.length; i++){
                System.out.println(obj[i].toString());
            }
        }
        
    }
    
}

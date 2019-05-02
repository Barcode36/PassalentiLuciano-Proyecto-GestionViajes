/*
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
*/
package test;

import funciones.fn;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import model.*;

/**
FXML Controller class
@author Luciano
*/
public class FinalizandoViajeController implements Initializable{
    
    private Object[] datosViaje = new Object[8];
    private ArrayList<Object[]> combustibles = new ArrayList<Object[]>();
    private ArrayList<Object[]> peajes = new ArrayList<Object[]>();
    @FXML
    private TextField kmFinales;
    /**
    Initializes the controller class.
    */
    @Override
    public void initialize(URL url, ResourceBundle rb){
        
    }
    public void loadData(Object[] datosViaje, ArrayList<Object[]> combustible, ArrayList<Object[]> peajes){
        
            System.out.println("--------Datos del viaje:------");
            for (int i = 0; i < datosViaje.length; i++){
                
                this.datosViaje[i] = datosViaje[i];
                
                
                if(datosViaje[i]!=null){
                    System.out.println("i: "+i+" ----- "+this.datosViaje[i].toString());
                }
                
                if(datosViaje[i]!=null){
                    System.out.println("i: "+i+" ----- "+datosViaje[i].toString());
                }
            }

            System.out.println("--------Cargas de combustible------");
            for (Object[] obj : combustible){
            //            for (int i = 0; i < obj.length; i++){
            //                System.out.println(obj[i].toString());
            //            }
            this.combustibles.add(obj);
            }

            System.out.println("--------Peajes------");
            for (Object[] obj : peajes){
            //            for (int i = 0; i < obj.length; i++){
            //                System.out.println(obj[i].toString());
            //            }
            this.peajes.add(obj);
            }

    }
    @FXML
    private void finalizarViaje(ActionEvent event){
        
        System.out.println(Integer.getInteger(kmFinales.getText()));
        
        if(fn.checkINT(kmFinales.getText())){
            if(Integer.valueOf(kmFinales.getText()) >
                    Integer.valueOf(datosViaje[5].toString())){
                Database.insert("INSERT INTO viaje (tipo,duracion,duracionTotal,idSalida,idLlegada,kilometos,fechaLlegada,fechaSalida) VALUES(?,?,?,?,?,?,?,?)", //TODO: cambiar el nombre de kilometos a KILOMETROS EN LA BASE DE DATOS Y ACA
                        new Object[]{
                            (String)datosViaje[0], // tipo
                            555555,                // duracion (en segundos)
                            555555,                // duracion total (en segundos)
                            (Integer)datosViaje[3],//id de salida
                            (Integer)datosViaje[4],//id de llegada
                            (Integer.valueOf(kmFinales.getText())-(Integer.valueOf(datosViaje[5].toString()))),//kilometros recorridos
                            Database.consulta("SELECT NOW()").get(0).get("NOW()"),  //fecha de llegada
                            datosViaje[7]
                        });
                
                //TODO: DECIR QUE FUE SUCCESSFULL Y CERRAR TODO
            }
            else{
                System.out.println("Los kilometros no puden ser menores a los con que se salio");
            }
        }
        else{
            //TODO mensaje de error
            System.out.println("Solo se admiten numeros enteros");
        }
        
    }
    
}

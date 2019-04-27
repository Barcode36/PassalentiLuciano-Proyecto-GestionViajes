/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package test;

import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.InputMethodEvent;
import model.*;

/**
* FXML Controller class
*
* @author Luciano
*/
public class NuevoViajeController implements Initializable{
    
    @FXML
    private ComboBox<String> tipoViaje;
    @FXML
    private ComboBox<String> partida;
    @FXML
    private ComboBox<String> llegada;
    @FXML
    private TextField kmIniciales;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb){
        //Datos del tipo de viaje
        ObservableList<String> data = FXCollections.observableArrayList("Lona/Frigo", "Viaje Corto", "Cisterna");
        tipoViaje.setValue(data.get(0));
        tipoViaje.setItems(data);
        //
        
        //Datos de partida
        HashMap<Integer,HashMap<String,Object>> partidas = Database.consulta("SELECT * FROM lugar");        
       
        ObservableList<String> dataPartidas = FXCollections.observableArrayList();
        partidas.forEach((k,v) -> dataPartidas.add((String)v.get("ciudad")+"/"+(String)v.get("direccion")+" "+(Integer)v.get("nDireccion")));
        partida.setValue(dataPartidas.get(0));
        partida.setItems(dataPartidas);
        //
        
        //Datos de llegada
        HashMap<Integer,HashMap<String,Object>> llegadas = Database.consulta("SELECT * FROM lugar");        
       
        ObservableList<String> dataLlegadas = FXCollections.observableArrayList();
        llegadas.forEach((k,v) -> dataLlegadas.add((String)v.get("ciudad")+"/"+(String)v.get("direccion")+" "+(Integer)v.get("nDireccion")));
        llegada.setValue(dataLlegadas.get(0));
        llegada.setItems(dataLlegadas);
        //
        
    }
    @FXML
    private void embarcar(ActionEvent event){
        
        if(checkValueKM(kmIniciales)){

            //Tendria que mostrar la ventana de viajando y pasarle todos los datos
            // y una ventana de si estas seguro de los datos ingresados
            
        }
        else{
            //mostrar mensaje de error
        }
    }
    @FXML
    private void crearDestino(ActionEvent event){
        //mostrar la ventana de creacion de destinos
        
        
        
        //HACER ESTO
        
        
    }
    private boolean checkValueKM(TextField tfKM){
        //checkear si es un numero
        //retorna true si lo es
        boolean valido;
        try{
            Integer.parseInt(tfKM.getText());
            valido=true;
        }
        catch (Exception e){
            valido=false;
            System.out.println(e.getMessage());
        }
        return valido;
    }
    @FXML
    private void reformatear(){
        
       String texto = deformatear(kmIniciales.getText());
       String nuevoFormato = "";

       int i = texto.length();
        for (;i > 0; i--){
            if((i%3==0) && i!=texto.length()){
                nuevoFormato+=".";
            }
            nuevoFormato+=texto.charAt(i-1);
            
        }
        kmIniciales.setText(nuevoFormato);
    }
    private String deformatear(String input){
    String output="";
        for (int i = 0; i < input.length(); i++){
            if(input.charAt(i)!='.'){
                output+=input.charAt(i);
            }
        }

    return output;
    }
    
}

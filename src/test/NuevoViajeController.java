/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package test;

import funciones.fn;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.InputMethodEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
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
        
        updateSalidaEntrada();
       
        
    }
    public void updateSalidaEntrada(){
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
        
        if(fn.checkINT(deformatear(kmIniciales.getText()))){
            //mostrar una ventana de si estas seguro de los datos ingresados
            //Tendria que mostrar la ventana de viajando y pasarle todos los datos
             
        }
        else{
            //mostrar mensaje de error
        }
    }
    @FXML
    private void crearDestino(ActionEvent event){
       try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("crearDestino.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initOwner(((Node)event.getTarget()).getScene().getWindow());
            stage.setTitle("Crear Destino");
            stage.setScene(new Scene(root1));
            stage.setOnCloseRequest((WindowEvent we) -> {
                updateSalidaEntrada();
            });
            stage.show();
        }
        catch (Exception ex){
            ex.getMessage();
        }
        
    }
   
    

    @FXML
    private void formatear(){
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

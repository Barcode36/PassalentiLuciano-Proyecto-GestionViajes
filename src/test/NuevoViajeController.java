/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package test;

import funciones.fn;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
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
    private ComboBox<Lugar> partida;
    @FXML
    private ComboBox<Lugar> llegada;
    @FXML
    private TextField kmIniciales;
    private HashMap<Integer,HashMap<String,Object>> partidas;
    private HashMap<Integer,HashMap<String,Object>> llegadas;
    
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
        partidas = Database.consulta("SELECT * FROM lugar");
        
        ObservableList<Lugar> dataPartidas = FXCollections.observableArrayList();
        
        partidas.forEach((k,v) -> dataPartidas.add(new Lugar(
                (int)v.get("idLugar"),
                (String)v.get("ciudad"),
                (String)v.get("direccion"),
                (int)v.get("nDireccion"))
        ));
        
        partida.setItems(dataPartidas);
        partida.setValue(dataPartidas.get(0));
        
        llegada.setItems(dataPartidas);
        llegada.setValue(dataPartidas.get(0));
    }
    @FXML
    private void embarcar(ActionEvent event){
        
        if(fn.checkINT(deformatear(kmIniciales.getText()))){
            
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("viajando.fxml"));
                
                Parent scene = (Parent) loader.load();
                Stage st = new Stage();
                
                ViajandoController controller = loader.<ViajandoController>getController();
                controller.loadData(new Object[]{
                    tipoViaje.getValue(),
                    partida.getValue().getiDlugar(),
                    llegada.getValue().getiDlugar(),
                    flip(deformatear(kmIniciales.getText()))
                });
                st.initModality(Modality.APPLICATION_MODAL);
                st.setOnCloseRequest((WindowEvent we) -> {
                    controller.cancelTimer();
                });
                st.setTitle("Viajando");
                st.setScene(new Scene(scene));
                st.show();
                
                Stage stage = (Stage) kmIniciales.getScene().getWindow();
                stage.close();
                
            }
            catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
            
        }
        else{
            Alert alert = new Alert(AlertType.ERROR, "El campo Kilometros no puede contener letras");
            alert.showAndWait();
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
        catch (IOException ex){
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
        kmIniciales.positionCaret(kmIniciales.getText().length());
    }
    
    private String deformatear(String input){
        String output="";
        for (int i = input.length(); i > 0; i--){
            if(input.charAt(i-1)!='.'){
                output+=input.charAt(i-1);
            }
        }
        return output;
    }
    private String flip(String text){
        String fliped="";
        for (int i = text.length(); i > 0 ; i--){
            fliped+=text.charAt(i-1);
        }
        return fliped;
    }
    
}

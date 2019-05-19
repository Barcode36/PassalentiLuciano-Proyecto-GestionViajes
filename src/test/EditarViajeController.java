/*
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
*/
package test;

import funciones.fn;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import model.Database;

/**
FXML Controller class

@author Luciano
*/
public class EditarViajeController implements Initializable{
    @FXML
    private Button btnAceptar;
    @FXML
    private Button btnCancelar;
    @FXML
    private ComboBox<String> tipo;
    @FXML
    private ComboBox<Lugar> salida;
    @FXML
    private ComboBox<Lugar> llegada;
    @FXML
    private TextField kilometros;
    
    private static Viaje viaje;
    private HashMap<Integer,HashMap<String,Object>> lugares;
    /**
    Initializes the controller class.
    */
    @Override
    public void initialize(URL url, ResourceBundle rb){
        //Datos del tipo de viaje
        ObservableList<String> data = FXCollections.observableArrayList("Lona/Frigo", "Viaje Corto", "Cisterna");
        tipo.setValue(data.get(0));
        tipo.setItems(data);
        //
        cargarLugares();
        
        kilometros.setText(""+viaje.getKilometros());
    }
    private void cargarLugares(){
        lugares = Database.consulta("SELECT * FROM lugar");
        ObservableList<Lugar> dataPartidas = FXCollections.observableArrayList();
        
        lugares.forEach((k,v) -> dataPartidas.add(new Lugar(
                (int)v.get("idLugar"),
                (String)v.get("ciudad"),
                (String)v.get("direccion"),
                (int)v.get("nDireccion"))
        ));
        
        salida.setItems(dataPartidas);
        salida.setValue(dataPartidas.get(0));
        
        llegada.setItems(dataPartidas);
        llegada.setValue(dataPartidas.get(0));
    }
    @FXML
    private void aceptar(ActionEvent event){
        // checkear que los km sean validos y enviar la consulta
        if(fn.checkINT(kilometros.getText())){

            Database.insert("UPDATE viaje SET tipo = ?, idSalida = ?, idLlegada = ?, kilometos = ? WHERE idViaje = ?", 
                    new Object[]{
                        tipo.getValue(),
                        salida.getValue().getiDlugar(),
                        llegada.getValue().getiDlugar(),
                        kilometros.getText(),
                        viaje.getIdViaje()
                    }
            );
            Stage stage = (Stage) btnAceptar.getScene().getWindow();
            stage.fireEvent(
                new WindowEvent(
                    stage,
                    WindowEvent.WINDOW_CLOSE_REQUEST
                )   
            );
        }
        else{
            Alert alert = new Alert(Alert.AlertType.ERROR, "El campo Kilometros no puede contener letras");
            alert.showAndWait();
        }
        
    }
    @FXML
    private void cerrar(ActionEvent event){
        Stage stage = (Stage) btnCancelar.getScene().getWindow();
        stage.fireEvent(
            new WindowEvent(
                stage,
                WindowEvent.WINDOW_CLOSE_REQUEST
            )   
        );
        stage.close();
    }
    public static void cargarViaje(Viaje v){
        viaje = v;
    }
    
}

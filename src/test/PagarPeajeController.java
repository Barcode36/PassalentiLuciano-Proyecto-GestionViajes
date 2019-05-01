/*
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
*/
package test;

import funciones.fn;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Database;

/**
FXML Controller class

@author Luciano
*/
public class PagarPeajeController implements Initializable{
    @FXML
    private Button btnAceptar;
    @FXML
    private TextField peaje;
    
    /**
    Initializes the controller class.
    */
    @Override
    public void initialize(URL url, ResourceBundle rb){
        // TODO
    }
    @FXML
    private void pagarPeaje(ActionEvent event){
        if(fn.checkINT(peaje.getText())){
            try{
                Stage stage = (Stage) btnAceptar.getScene().getWindow();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("viajando.fxml"));
                Parent root = loader.load();
                ViajandoController controller = loader.<ViajandoController>getController();
                controller.cargarDataPeaje(new Object[]{peaje.getText(), Database.consulta("SELECT NOW() FROM lugar").get(0).get("NOW()")});
                stage.close();
            }
            catch(Exception e){
                System.out.println(e.getMessage());
            }
        }
        else{
            System.out.println("El peaje no admite letras");
        }
        
        // TODO
        //MOSTRAR  ventanas de ERRORES error
        
    }
    
}

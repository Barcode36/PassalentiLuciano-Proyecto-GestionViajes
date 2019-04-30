/*
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
*/
package test;

import funciones.fn;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import model.Database;

/**
FXML Controller class

@author Luciano
*/
public class CargarCombustibleController implements Initializable{
    @FXML
    private TextField litros;
    @FXML
    private TextField km;
    @FXML
    private TextField precio;
    @FXML
    private Button btnAceptar;
    
    /**
    Initializes the controller class.
    */
    @Override
    public void initialize(URL url, ResourceBundle rb){
        // TODO
    }
    @FXML
    private void cargarCombustible(ActionEvent event){
        
        if(fn.checkINT(litros.getText())){
            if(fn.checkINT(km.getText())){
                if(fn.checkINT(precio.getText())){
                    try{
                    Stage stage = (Stage) btnAceptar.getScene().getWindow();
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("viajando.fxml"));
                    Parent root = loader.load();
                    ViajandoController controller = loader.<ViajandoController>getController();
                    controller.cargarDataCombustible(new Object[]{litros.getText(),km.getText(), precio.getText(), Database.consulta("SELECT NOW() FROM lugar").get(0).get("NOW()")});
                    stage.close();
                    }
                    catch(Exception e){
                        System.out.println(e.getMessage());
                    }
                }
                else{
                    System.out.println("El precio no admite letras");
                }
            }
            else{
                System.out.println("Los kilometros no admiten letras");
            }
        }
        else{
            System.out.println("Los litros no admiten letras");
            // TODO
            //MOSTRAR  ventanas de ERRORES error
        }
        
    }
    
    
    
}

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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Database;

/**
 * FXML Controller class
 * 
 * @author Luciano
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
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb){
        // TODO
    }
    @FXML
    private void cargarCombustible(ActionEvent event){
        
        if(fn.checkDouble(litros.getText())){
            if(fn.checkINT(km.getText())){
                if(fn.checkDouble(precio.getText())){
                    try{
                        Stage stage = (Stage) btnAceptar.getScene().getWindow();
                        ViajandoController.cargarDataCombustible(new Object[]{litros.getText(),km.getText(), precio.getText(), Database.consulta("SELECT NOW() FROM lugar").get(0).get("NOW()")});
                        stage.close();
                    }
                    catch(Exception e){
                        System.out.println(e.getMessage());
                    }
                }
                else{
                    Alert alert = new Alert(Alert.AlertType.ERROR, "El precio no admite letras");
                    alert.showAndWait();
                }
            }
            else{
                Alert alert = new Alert(Alert.AlertType.ERROR, "Los kilometros no admiten letras");
                alert.showAndWait();
            }
        }
        else{
            Alert alert = new Alert(Alert.AlertType.ERROR, "Los litros no admiten letras");
            alert.showAndWait();
        }
        
    }
    
    
    
}

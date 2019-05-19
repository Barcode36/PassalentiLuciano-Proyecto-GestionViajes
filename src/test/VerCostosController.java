/*
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
*/
package test;

import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Database;

/**
FXML Controller class

@author Luciano
*/
public class VerCostosController implements Initializable{
    @FXML
    private TableView<Peaje> tvPeajes;
    @FXML
    private TableColumn<Peaje, Double> costoPeaje;
    @FXML
    private TableColumn<Peaje, Object> fechaPeaje;
    @FXML
    private TableView<Combustible> tvCombus;
    @FXML
    private TableColumn<Combustible, Double> litrosCombus;
    @FXML
    private TableColumn<Combustible, Integer> kmCombus;
    @FXML
    private TableColumn<Combustible, Double> costoCombus;
    @FXML
    private TableColumn<Combustible, Object> fechaCombus;
    @FXML
    private Label costoTotalPeajes;
    @FXML
    private Label costoTotalCombustibles;
    
    private ObservableList<Peaje> listaPeajes;
    private ObservableList<Combustible> listaCombustibles;
    private static Viaje viaje;
    private HashMap<Integer,HashMap<String,Object>> peajes = new HashMap<Integer,HashMap<String,Object>>();
    private HashMap<Integer,HashMap<String,Object>> cargasCombus = new HashMap<Integer,HashMap<String,Object>>();
    /**
    Initializes the controller class.
    */
    @Override
    public void initialize(URL url, ResourceBundle rb){
        cargarDatos();
        costoTotalPeajes.setText(""+viaje.getCostoPeajes());
        costoTotalCombustibles.setText(""+viaje.getCostoCombustible());
    }
    public static void cargarViaje(Viaje v){
       viaje = v;
    }
    private void cargarDatos(){
        peajes = Database.consulta("SELECT * FROM peaje WHERE idViaje=?", new Object[]{viaje.getIdViaje()});
        cargarTablaPeajes(peajes);
        tvPeajes.setItems(listaPeajes);
        
        cargasCombus = Database.consulta("SELECT * FROM combustible WHERE idViaje=?", new Object[]{viaje.getIdViaje()});
        cargarTablaCombustibles(cargasCombus);
        tvCombus.setItems(listaCombustibles);
        
    }
    private void cargarTablaPeajes(HashMap<Integer,HashMap<String,Object>> items){
        // crear la observable list
        listaPeajes = FXCollections.observableArrayList();
        // añadir los objetos a la lista
        items.forEach((k,v) -> listaPeajes.add(new
                        Peaje(
                                (int)v.get("idPeaje"),
                                (int)v.get("idViaje"),
                                (double)v.get("costo"),
                                (Object)v.get("fecha")
                        ))
        );
        costoPeaje.setCellValueFactory(new PropertyValueFactory<Peaje,Double>("costo"));
        fechaPeaje.setCellValueFactory(new PropertyValueFactory<Peaje,Object>("fecha"));
    }
    
    private void cargarTablaCombustibles(HashMap<Integer,HashMap<String,Object>> items){
        listaCombustibles = FXCollections.observableArrayList();

        items.forEach((k,v) -> listaCombustibles.add(new
                        Combustible(
                                (int)v.get("idCombustible"),
                                (int)v.get("idViaje"),
                                (double)v.get("litros"),
                                (int)v.get("kilometros"),
                                (double)v.get("precio"),
                                (Object)v.get("fecha")
                        ))
        );
        litrosCombus.setCellValueFactory(new PropertyValueFactory<Combustible,Double>("litros"));
        kmCombus.setCellValueFactory(new PropertyValueFactory<Combustible,Integer>("kilometros"));
        costoCombus.setCellValueFactory(new PropertyValueFactory<Combustible,Double>("precio"));
        fechaCombus.setCellValueFactory(new PropertyValueFactory<Combustible,Object>("fecha"));
 
    }
    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.net.URL;
import java.sql.Date;
import java.util.HashMap;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Database;

/**
 * FXML Controller class
 *
 * @author DAW
 */
public class VerViajesController implements Initializable {

    @FXML
    private TableView<Viaje> tvViajes;
    private ObservableList<Viaje> listaViajes;
    @FXML
    private TextField tfBusqueda;
    @FXML
    private Button btnCrearArchivo;
    @FXML
    private Button btnCerrarVentana;
    @FXML
    private ComboBox<?> cbColumna;
    @FXML
    private Button btnEliminarSeleccionado;
    @FXML
    private Button btnEditarSeleccionado;
    @FXML
    private Button btnAceptarEdicion;
    @FXML
    private Button btnCancelarEdicion;
    private HashMap<Integer,HashMap<String,Object>> viajes;
    @FXML
    private TableColumn<Viaje, String> tipo;
    @FXML
    private TableColumn<Viaje, Integer> duracion;
    @FXML
    private TableColumn<Viaje, Integer> duracionTotal;
    @FXML
    private TableColumn<Viaje, Integer> salida; //placeholder pero en el futuro tendria que ser string
    @FXML
    private TableColumn<Viaje, Integer> llegada;
    @FXML
    private TableColumn<Viaje, Integer> kmRecorridos;
    @FXML
    private TableColumn<Viaje, Object> fechaLlegada;
    @FXML
    private TableColumn<Viaje, Object> fechaSalida;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        // consulta a la bd
        viajes = Database.consulta("SELECT * FROM viaje");
     
        // crear los objetos de viajes
        listaViajes = FXCollections.observableArrayList();
       
        viajes.forEach((k,v) -> listaViajes.add(new
            Viaje(
                (int)v.get("idViaje"),
                (String)v.get("tipo"), 
                (int)v.get("duracion"), 
                (int)v.get("duracionTotal"), 
                (int)v.get("idSalida"), 
                (int)v.get("idLlegada"), 
                (int)v.get("kilometos"), 
                (Object)v.get("fechaLlegada"), 
                (Object)v.get("fechaSalida")
                
            )
        ));

//        imprimir viajes     
//        for (Viaje Viaje : listaViajes){
//            System.out.println(Viaje.getIdViaje());
//            System.out.println(Viaje.getTipo());
//            System.out.println(Viaje.getDuracion());
//            System.out.println(Viaje.getDuracionTotal());
//            System.out.println(Viaje.getIdSalida());
//            System.out.println(Viaje.getIdLlegada());
//            System.out.println(Viaje.getKilometros());
//            System.out.println(Viaje.getFechaLlegada());
//            System.out.println(Viaje.getFechaSalida());
//        }
        
        
        tipo.setCellValueFactory(new PropertyValueFactory<Viaje,String>("tipo"));
        duracion.setCellValueFactory(new PropertyValueFactory<Viaje,Integer>("duracion"));
        duracionTotal.setCellValueFactory(new PropertyValueFactory<Viaje,Integer>("duracionTotal"));
        salida.setCellValueFactory(new PropertyValueFactory<Viaje,Integer>("idSalida"));
        llegada.setCellValueFactory(new PropertyValueFactory<Viaje,Integer>("idLlegada"));
        kmRecorridos.setCellValueFactory(new PropertyValueFactory<Viaje,Integer>("kilometros"));
        fechaLlegada.setCellValueFactory(new PropertyValueFactory<Viaje,Object>("fechaLlegada"));
        fechaSalida.setCellValueFactory(new PropertyValueFactory<Viaje,Object>("fechaSalida"));
        
        tvViajes.setItems(listaViajes);
        
    }    
    
}

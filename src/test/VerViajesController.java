/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package test;

import funciones.FuncionesFile;
import java.io.File;
import java.net.URL;
import java.sql.Date;
import java.util.HashMap;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
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
    private TableColumn<Viaje, String> duracion;
    @FXML
    private TableColumn<Viaje, String> duracionTotal;
    @FXML
    private TableColumn<Viaje, String> salida;
    @FXML
    private TableColumn<Viaje, String> llegada;
    @FXML
    private TableColumn<Viaje, Integer> kmRecorridos;
    @FXML
    private TableColumn<Viaje, Object> fechaLlegada;
    @FXML
    private TableColumn<Viaje, Object> fechaSalida;
    /**
     * Initializes the controller class.
     */
    
    // LISTA DE TODO:
    //  -Fixear el cronometro (por quinta vez)
    //  -Hacer el boton de modificar (Con todos los campos y lanzar la consulta con los datos y recargar la tabla)
    //  -Hacer el boton de Cerrar
    //  -Hacer el campo de filtro (con delay de 1 seg) y llenar el comboBox(salida, llegada y tipo)
    //  -Hacer un boton para ver los gastos de un Viaje
    //  -Hacer Css para algunas cosas
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cargarViajesTabla();
        
        tvViajes.setItems(listaViajes);
    }
    
    private void cargarViajesTabla(){
        // consulta a la bd
        viajes = Database.consulta("SELECT * FROM viaje");
        // crear la observable list
        listaViajes = FXCollections.observableArrayList();
        // añadir los objetos a la lista
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
        tipo.setCellValueFactory(new PropertyValueFactory<Viaje,String>("tipo"));
        duracion.setCellValueFactory(new PropertyValueFactory<Viaje,String>("duracionFormato"));
        duracionTotal.setCellValueFactory(new PropertyValueFactory<Viaje,String>("duracionTotalFormato"));
        salida.setCellValueFactory(new PropertyValueFactory<Viaje,String>("nombreSalida"));
        llegada.setCellValueFactory(new PropertyValueFactory<Viaje,String>("nombreLlegada"));
        kmRecorridos.setCellValueFactory(new PropertyValueFactory<Viaje,Integer>("kilometros"));
        fechaLlegada.setCellValueFactory(new PropertyValueFactory<Viaje,Object>("fechaLlegada"));
        fechaSalida.setCellValueFactory(new PropertyValueFactory<Viaje,Object>("fechaSalida"));
    }
    
    
    @FXML
    private void eliminarSelected(ActionEvent event) {
        if(tvViajes.getSelectionModel().getSelectedItem()!=null){
                    Database.insert("DELETE FROM viaje WHERE idViaje=?", new Object[]{tvViajes.getSelectionModel().getSelectedItem().getIdViaje()});
                    
                    //recargar la tabla
                   listaViajes.clear();
                   cargarViajesTabla();
                   tvViajes.setItems(listaViajes);
        }
        else{
            Alert alert = new Alert(Alert.AlertType.ERROR, "No hay ningun viaje seleccionado");
            alert.showAndWait();
        }
    }
    
    @FXML
    private void crearArchivo(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Html files (*.html)", "*.html");
        fileChooser.getExtensionFilters().add(extFilter);
        
        
        Stage stage = (Stage) btnCrearArchivo.getScene().getWindow();
        File file = fileChooser.showSaveDialog(stage);
        
        if (file != null) {
            String cabezera =
                    "<!DOCTYPE html>"
                    + "<html lang='es' dir='ltr'>"
                    + "<head>"
                    + "<meta charset='utf-8'>"
                    + "<title>Viajes</title>"
                    + "</head>"
                    + "<body>";
            String footer= 
                        "</body>"
                    + "</html>";
            String contenido="";
            
            contenido+=cabezera;
            contenido+="<table style='border: solid 1px black;'>";
            contenido+="<tr>"
                    +"<td style='border: solid 1px black;'>Tipo</td>"
                    +"<td style='border: solid 1px black;'>Duracion</td>"
                    +"<td style='border: solid 1px black;'>Salida</td>"
                    +"<td style='border: solid 1px black;'>LLegada</td>"
                    +"<td style='border: solid 1px black;'>Kilometros</td>"
                    +"<td style='border: solid 1px black;'>Fecha Salida</td>"
                    +"<td style='border: solid 1px black;'>Fecha llegada</td>"
                    + "</tr>";
            for (Viaje viaje : listaViajes) {
                contenido+="<tr style='border: solid 1px black;'>";
                contenido+="<td style='border: solid 1px black;'>";
                contenido+=viaje.getTipo();
                contenido+="</td>";
                contenido+="<td style='border: solid 1px black;'>";
                contenido+=viaje.getDuracionFormato();
                contenido+="</td>";
                contenido+="<td style='border: solid 1px black;'>";
                contenido+=viaje.getNombreSalida();
                contenido+="</td>";
                contenido+="<td style='border: solid 1px black;'>";
                contenido+=viaje.getNombreLlegada();
                contenido+="</td>";
                contenido+="<td style='border: solid 1px black;'>";
                contenido+=viaje.getKilometros();
                contenido+="</td>";
                contenido+="<td style='border: solid 1px black;'>";
                contenido+=viaje.getFechaSalida();
                contenido+="</td>";
                contenido+="<td style='border: solid 1px black;'>";
                contenido+=viaje.getNombreLlegada();
                contenido+="</td>";
                contenido+="</tr>";
            }
            contenido+="</table>";
            contenido+=footer;
            
            FuncionesFile.crearArchivoArrStr(new String[]{contenido},file.getAbsolutePath());
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "El Archivo se creo Correctamente");
            alert.setHeaderText("Tarea completada");
            alert.getButtonTypes().setAll(new ButtonType("Confirmar"));
            alert.showAndWait();
        }
        
    }
    
    
}

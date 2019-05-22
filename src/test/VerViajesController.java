/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package test;

import funciones.FuncionesFile;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
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
    private ComboBox<Filtro> cbColumna;
    @FXML
    private Button btnEliminarSeleccionado;
    @FXML
    private Button btnEditarSeleccionado;
    private HashMap<Integer,HashMap<String,Object>> viajes;
    @FXML
    private TableColumn<Viaje, String> tipo;
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
    @FXML
    private Button btnVerGastos;
    @FXML
    private TableColumn<Viaje, Integer> coste;
    /**
    * Initializes the controller class.
    */ 
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        viajes = Database.consulta("SELECT * FROM viaje");
        cargarViajesTabla(viajes);
        tvViajes.setItems(listaViajes);
        
        ObservableList<Filtro> data = FXCollections.observableArrayList();
        Filtro tipo = new Filtro("Tipo", "tipo");
        data.add(tipo);
        Filtro salida = new Filtro("Salida", "idSalida");
        data.add(salida);
        Filtro llegada = new Filtro("Llegada", "idLlegada");
        data.add(llegada);
        Filtro filtrofechaLlegada = new Filtro("Fecha Llegada", "fechaLlegada");
        data.add(filtrofechaLlegada);
        Filtro filtrofechaSalida = new Filtro("Fecha Salida", "fechaSalida");
        data.add(filtrofechaSalida);
        cbColumna.setItems(data);
        cbColumna.setValue(data.get(0));
    }
    
    private void cargarViajesTabla(HashMap<Integer,HashMap<String,Object>> items){
        // crear la observable list
        listaViajes = FXCollections.observableArrayList();
        // añadir los objetos a la lista
        items.forEach((k,v) -> listaViajes.add(new
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
        coste.setCellValueFactory(new PropertyValueFactory<Viaje,Integer>("gastoTotal"));
        duracionTotal.setCellValueFactory(new PropertyValueFactory<Viaje,String>("duracionTotalFormato"));
        salida.setCellValueFactory(new PropertyValueFactory<Viaje,String>("nombreSalida"));
        llegada.setCellValueFactory(new PropertyValueFactory<Viaje,String>("nombreLlegada"));
        kmRecorridos.setCellValueFactory(new PropertyValueFactory<Viaje,Integer>("kilometros"));
        fechaLlegada.setCellValueFactory(new PropertyValueFactory<Viaje,Object>("fechaLlegada"));
        fechaSalida.setCellValueFactory(new PropertyValueFactory<Viaje,Object>("fechaSalida"));
    }
    private void recargarTabla(HashMap<Integer,HashMap<String,Object>> items){
        listaViajes.clear();
        cargarViajesTabla(items);
        tvViajes.setItems(listaViajes);
    }
    @FXML
    private void busqueda(KeyEvent event) {
        if(!cbColumna.getValue().getValue().equals("fechaLlegada") && !cbColumna.getValue().getValue().equals("fechaSalida")){
            if(!cbColumna.getValue().getValue().equals("tipo")){
                recargarTabla(Database.consulta("SELECT v.idViaje,v.tipo,v.duracion,v.duracionTotal,v.idSalida,v.idLlegada,v.kilometos,v.fechaLlegada,v.fechaSalida FROM viaje v,lugar l WHERE v."+cbColumna.getValue().getValue()+" = l.idLugar AND LOWER(l.ciudad) LIKE \""+'%'+tfBusqueda.getText()+'%'+"\""));   
            }
            else{
                recargarTabla(Database.consulta("SELECT * FROM viaje WHERE LOWER("+cbColumna.getValue().getValue()+") LIKE \""+'%'+tfBusqueda.getText()+'%'+"\""));   
            }
        }
        else{
            recargarTabla(Database.consulta("SELECT * FROM viaje WHERE "+cbColumna.getValue().getValue()+" LIKE \""+'%'+tfBusqueda.getText()+'%'+"\""));
        }
    }
    @FXML
    private void verGastos(ActionEvent event){        
        if(tvViajes.getSelectionModel().getSelectedItem()!=null){
            try{
                FXMLLoader loader = new FXMLLoader(getClass().getResource("verCostos.fxml"));
                VerCostosController.cargarViaje(tvViajes.getSelectionModel().getSelectedItem());
                Parent scene = (Parent) loader.load();
                Stage st = new Stage();

                st.initModality(Modality.APPLICATION_MODAL);
                st.setTitle("Costos");
                st.setScene(new Scene(scene));
                st.setResizable(false);
                st.show();

            }
            catch (IOException ex){
                Logger.getLogger(VerViajesController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else{
            Alert alert = new Alert(Alert.AlertType.ERROR, "No hay ningun viaje seleccionado");
            alert.showAndWait();
        }
    }
    
    @FXML
    private void eliminarSelected(ActionEvent event) {
        if(tvViajes.getSelectionModel().getSelectedItem()!=null){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"Esta seguro de Eliminar ese viaje?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK){
                Database.insert("DELETE FROM peaje WHERE idViaje=?", new Object[]{tvViajes.getSelectionModel().getSelectedItem().getIdViaje()});
                Database.insert("DELETE FROM combustible WHERE idViaje=?", new Object[]{tvViajes.getSelectionModel().getSelectedItem().getIdViaje()});
                Database.insert("DELETE FROM viaje WHERE idViaje=?", new Object[]{tvViajes.getSelectionModel().getSelectedItem().getIdViaje()});
                Alert alert2 = new Alert(Alert.AlertType.INFORMATION, "Viaje eliminado correctamente");
                alert2.showAndWait();
                recargarTabla(Database.consulta("SELECT * FROM viaje"));
                tfBusqueda.setText("");
            }
        }
        else{
            Alert alert = new Alert(Alert.AlertType.ERROR, "No hay ningun viaje seleccionado");
            alert.showAndWait();
        }
    }
    
    @FXML
    private void crearArchivo(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("html files (*.html)", "*.html");
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
            double gastoTotal = 0;
            
            contenido+=cabezera;
            contenido+="<table style='border: solid 1px black;'>";
            contenido+="<tr>"
                    +"<td style='border: solid 1px black;'>Tipo</td>"
                    +"<td style='border: solid 1px black;'>Costo</td>"
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
                contenido+=viaje.getGastoTotal();
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
                contenido+=viaje.getFechaLlegada();
                contenido+="</td>";
                contenido+="</tr>";
                gastoTotal+=viaje.getGastoTotal();
            }
            contenido+="<tr>";
            contenido+="<td colspan='8' style='border: solid 1px black;'>";
            contenido+="Costo total: ";
            contenido+=gastoTotal;
            contenido+=" €";
            contenido+="</td>";
            contenido+="</tr>";
            contenido+="</table>";
            contenido+=footer;
            
            FuncionesFile.crearArchivoArrStr(new String[]{contenido},file.getAbsolutePath());
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "El Archivo se creo Correctamente");
            alert.setHeaderText("Tarea completada");
            alert.getButtonTypes().setAll(new ButtonType("Confirmar"));
            alert.showAndWait();
        }
        
    }
    
    @FXML
    private void cerrarVentana(ActionEvent event) {
        Stage stage = (Stage) btnCerrarVentana.getScene().getWindow();
        stage.close();
    }
    @FXML
    private void editarViaje(ActionEvent event){
                
        if(tvViajes.getSelectionModel().getSelectedItem()!=null){
            try{
                FXMLLoader loader = new FXMLLoader(getClass().getResource("editarViaje.fxml"));
                EditarViajeController.cargarViaje(tvViajes.getSelectionModel().getSelectedItem());
                Parent scene = (Parent) loader.load();
                Stage st = new Stage();
                st.setOnCloseRequest((WindowEvent we) -> {
                    recargarTabla(Database.consulta("SELECT * FROM viaje"));
                });
                st.initModality(Modality.APPLICATION_MODAL);
                st.setTitle("Editar");
                st.setScene(new Scene(scene));
                st.setResizable(false);
                st.show();
            }
            catch (IOException ex){
                Logger.getLogger(VerViajesController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else{
            Alert alert = new Alert(Alert.AlertType.ERROR, "No hay ningun viaje seleccionado");
            alert.showAndWait();
        }
        
    }
    
    
    
}

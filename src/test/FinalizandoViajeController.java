/*
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
*/
package test;

import funciones.fn;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import model.*;

/**
 * FXML Controller class
 * @author Luciano
 */
public class FinalizandoViajeController implements Initializable{
    
    private Object[] datosViaje = new Object[8];
    private ArrayList<Object[]> combustibles = new ArrayList<Object[]>();
    private ArrayList<Object[]> peajes = new ArrayList<Object[]>();
    @FXML
    private TextField kmFinales;
    private static boolean finalizado = false;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb){
        
    }

    /**
     * Cargar Todos los peajes/cargas de combustible y los datos del viaje.
     * @param datosViaje Object[] 
     * @param combustible ArrayList Combustible 
     * @param peajes ArrayList Peaje
     */
    public void loadData(Object[] datosViaje, ArrayList<Object[]> combustible, ArrayList<Object[]> peajes){
        //            System.out.println("--------Datos del viaje:------");
        for (int i = 0; i < datosViaje.length; i++){
            this.datosViaje[i] = datosViaje[i];
        }
        //            System.out.println("--------Cargas de combustible------");
        for (Object[] obj : combustible){
            this.combustibles.add(obj);
        }
        //            System.out.println("--------Peajes------");
        for (Object[] obj : peajes){
            this.peajes.add(obj);
        }
    }
    
    /**
    * Chequea que los kilometros finales no sean menores que con los que se inicio, que sea un int valido y Crea el vaije, a continuacion crea todos los peajes y todas las cargas de combustible conrrespondientes al viaje.
    */
    @FXML
    private void finalizarViaje(ActionEvent event){
       
        
        if(fn.checkINT(kmFinales.getText())){
            if((Integer.valueOf(kmFinales.getText()) > Integer.valueOf(datosViaje[5].toString())) && (Integer.valueOf(kmFinales.getText()) < 2147483647 )){
                
                System.out.println("Segundos del timer: "+ViajandoController.getSegundos());
                System.out.println("Segundos viajados "+(Long)Database.consulta("Select TIMESTAMPDIFF(SECOND, ?, ?) as duracionTotal FROM lugar", new Object[]{datosViaje[7],Database.consulta("SELECT NOW()").get(0).get("NOW()")}).get(0).get("duracionTotal"));
                
                //CREAR EL VIAJE
                Database.insert("INSERT INTO viaje (tipo,duracion,duracionTotal,idSalida,idLlegada,kilometos,fechaLlegada,fechaSalida) VALUES(?,?,?,?,?,?,?,?)", //TODO: cambiar el nombre de kilometos a KILOMETROS EN LA BASE DE DATOS Y ACA
                        new Object[]{
                            (String)datosViaje[0], // tipo de viaje
                            (Integer)ViajandoController.getSegundos(), // duracion (en segundos)
                            (Long)Database.consulta("Select TIMESTAMPDIFF(SECOND, ?, ?) as duracionTotal FROM lugar", new Object[]{datosViaje[7],Database.consulta("SELECT NOW()").get(0).get("NOW()")}).get(0).get("duracionTotal"),                       // duracion total (en segundos)
                            (Integer)datosViaje[3],//id de salida
                            (Integer)datosViaje[4],//id de llegada
                            (Integer.valueOf(kmFinales.getText())-(Integer.valueOf(datosViaje[5].toString()))),//kilometros recorridos
                            Database.consulta("SELECT NOW()").get(0).get("NOW()"),  //fecha de llegada
                            datosViaje[7] //fecha de salida
                        });
                
                //CREAR LOS PEAJES
                for (Object[] peaje : peajes) {
                    Database.insert("INSERT INTO peaje(idViaje,costo,fecha) VALUES(?,?,?)",
                            new Object[]{
                                (Integer)Database.consulta("SELECT MAX(idViaje) as ultimoID FROM viaje").get(0).get("ultimoID"), // id del ultimo viaje insertado
                                peaje[0],   // costo
                                peaje[1]    // DATETIME
                            });
                }
                
                //CREAR LAS CARGAS DE COMBUSTIBLE
                for (Object[] carga : combustibles) {
                    Database.insert("INSERT INTO combustible(idViaje,litros,kilometros,precio,fecha) VALUES(?,?,?,?,?)",
                            new Object[]{
                                (Integer)Database.consulta("SELECT MAX(idViaje) as ultimoID FROM viaje").get(0).get("ultimoID"), // id del ultimo viaje insertado
                                carga[0],   // litros
                                carga[1],   // km
                                carga[2],   // precio
                                carga[3]    // DATETIME
                            });
                }
                //Ventana de creacion exitosa
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("viajeSuccsess.fxml"));
                    
                    Parent scene = (Parent) loader.load();
                    Stage st = new Stage();
                    Stage stage = (Stage) kmFinales.getScene().getWindow();
                    
                    ViajeSuccsessController controller = loader.<ViajeSuccsessController>getController();
                    st.initModality(Modality.APPLICATION_MODAL);
                    st.setOnCloseRequest((WindowEvent we) -> {
                        stage.fireEvent(
                                new WindowEvent(
                                        stage,
                                        WindowEvent.WINDOW_CLOSE_REQUEST
                                )
                        );
                    });
                    st.setTitle("Finalizado Correctamente");
                    st.setScene(new Scene(scene));
                    st.setResizable(false);
                    st.show();
                    
                    finalizado = true;
                    
                }
                catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
            else{
                Alert alert = new Alert(Alert.AlertType.WARNING, "Los kilometros no pueden ser menores a con los que se salieron");
                alert.showAndWait();
            }
        }
        else{
            Alert alert = new Alert(Alert.AlertType.ERROR, "Solo se admiten numeros enteros y menores a 2,147,483,647");
            alert.showAndWait();
        }
    }
    
    /**
     * Retorna la variable que determina si el viaje finalizo exitosamente o no.
     * @return boolean
     */
    public static boolean getFinalizado() {
        return FinalizandoViajeController.finalizado;
    }
    
}

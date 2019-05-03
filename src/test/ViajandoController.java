/*
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
*/
package test;

import funciones.fn;
import java.io.IOException;
import model.*;
import java.net.URL;
import java.util.*;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
FXML Controller class

@author Luciano
*/
public class ViajandoController implements Initializable{
    @FXML
    private Label status;
    private Object[] data;
    private int segundos=0,horas=0,minutos=0;
    private static Integer segundosTotales=0;
    @FXML
    private Label cronometro;
    private boolean pausado = false;
    private Timer timer;
    private TimerTask timerTask;
    @FXML
    private Button btnPausa;
    private static ArrayList<Object[]> gastosCombustible = new ArrayList<Object[]>();
    private static ArrayList<Object[]> peajes = new ArrayList<Object[]>();
    
    /**
    Initializes the controller class.
    * @param url
    * @param rb
    */
    
    @Override
    public void initialize(URL url, ResourceBundle rb){
        //start the timer
        timer = new Timer();
        
        timerTask = new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        segundos++;
                        ViajandoController.segundosTotales++;
                        actualizar();
                        artualizarCronometro();
                    }
                });
            }
        };
        
        timer.schedule(timerTask, 1000, 1000);
        status.setText("ACTIVO");
        
    }
    private void actualizar(){
        if(minutos>=60){
            horas++;
            minutos=0;
        }
        if(segundos>=60){
            minutos++;
            segundos=0;
        }
    }
    private void artualizarCronometro(){
        String seg="",min="",hr="";
        if(segundos<=9) {seg = "0";}
        if(minutos<=9) {min = "0";}
        if(horas<=9) {hr = "0";}
        
        cronometro.setText(hr+horas+":"+min+minutos+":"+seg+segundos);
    }
    @FXML
    private void pausar(){
        if(pausado==false){
            pausado=true;
            btnPausa.setText("Reanudar");
            status.setText("PAUSADO");
            timer.cancel();
        }
        else{
            pausado=false;
            btnPausa.setText("Pausar");
            status.setText("ACTIVO");
            timer = new Timer();
            timer.schedule( new TimerTask() {
                @Override
                public void run() {
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            segundos++;
                            ViajandoController.segundosTotales++;
                            actualizar();
                            artualizarCronometro();
                        }
                    });
                }
            }, 0, 1000);
        }
    }
    
    public void loadData(Object[] data){
        this.data = new Object[data.length+4];
        
        this.data[0] = data[0];            //tipo de viaje
        this.data[1] = null;               //duracion
        this.data[2] = null;               //duracion Total
        this.data[3] = (Integer) Database.consulta("SELECT idLugar FROM lugar WHERE ciudad=? AND direccion=? AND nDireccion=?",new Object[]{decripCiudad((String)data[1]),decripDireccion((String)data[1]),decripNDireccion((String)data[1])}).get(0).get("idLugar"); // ID partida
        this.data[4] = (Integer) Database.consulta("SELECT idLugar FROM lugar WHERE ciudad=? AND direccion=? AND nDireccion=?",new Object[]{decripCiudad((String)data[2]),decripDireccion((String)data[2]),decripNDireccion((String)data[2])}).get(0).get("idLugar"); // ID llegada
        this.data[5] = data[3];            //kilometros iniciales
        this.data[6] = null;               //fecha de llegada
        this.data[7] = Database.consulta("SELECT NOW() as fechasalida").get(0).get("fechasalida"); //fecha-hora de salida
        
        
        /////////
        for (Object obj : this.data){
            if(obj!=null)System.out.println(obj.toString());
        }
        //////
    }
    private int decripNDireccion(String destino){
        String nDireccion="";
        int i = 0;
        boolean numeroEncontro = false;
        
        while(i< destino.length() && !numeroEncontro){
            if((destino.charAt(i)=='0' || destino.charAt(i)=='1' || destino.charAt(i)=='2' || destino.charAt(i)=='3' || destino.charAt(i)=='4' || destino.charAt(i)=='5' || destino.charAt(i)=='6' || destino.charAt(i)=='7' || destino.charAt(i)=='8' || destino.charAt(i)=='9')){
                numeroEncontro=true;
            }
            else{
                i++;
            }
        }
        for (; i< destino.length(); i++){
            nDireccion+=destino.charAt(i);
        }
        return Integer.parseInt(nDireccion);
    }
    private String decripDireccion(String destino){
        String direccion="";
        int i = 0;
        boolean encontroSlash = false;
        boolean encontroNumero = false;
        while(!encontroSlash){
            if(destino.charAt(i)=='/'){
                encontroSlash=true;
            }
            i++;
        }
        while(!encontroNumero){
            if(destino.charAt(i)=='0' || destino.charAt(i)=='1' || destino.charAt(i)=='2' || destino.charAt(i)=='3' || destino.charAt(i)=='4' || destino.charAt(i)=='5' || destino.charAt(i)=='6' || destino.charAt(i)=='7' || destino.charAt(i)=='8' || destino.charAt(i)=='9'){
                encontroNumero=true;
            }
            else{
                direccion+=destino.charAt(i);
                i++;
            }
        }
        return direccion;
    }
    private String decripCiudad(String destino){
        String ciudad="";
        boolean finalizo=false;
        int i = 0;
        while((i < destino.length()) && finalizo == false){
            if(destino.charAt(i)=='/'){
                finalizo=true;
            }
            else{
                ciudad+=destino.charAt(i);
                i++;
            }
        }
        return ciudad;
    }
    @FXML
    private void mostrarCargaCombustible(ActionEvent event){
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("cargarCombustible.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initOwner(((Node)event.getTarget()).getScene().getWindow());
            stage.setTitle("Cargar Combustible");
            stage.setScene(new Scene(root1));
            
            stage.show();
        }
        catch (Exception ex){
            ex.getMessage();
        }
    }
    @FXML
    private void mostrarPagarPeaje(ActionEvent event){
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("pagarPeaje.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initOwner(((Node)event.getTarget()).getScene().getWindow());
            stage.setTitle("Pagar Peaje");
            stage.setScene(new Scene(root1));
            
            stage.show();
        }
        catch (Exception ex){
            ex.getMessage();
        }
    }
    public void cargarDataCombustible(Object[] array){
        gastosCombustible.add(array);
        //{LITROS, KM, PRECIO, DATETIME};
        
        ////// IMPRIMIR POR CONSOLA ////////
//        for (Object obj : array){
//            if(obj!= null){
//                System.out.println(obj.toString());
//            }    
//        }
        ////////////////
    }
    public void cargarDataPeaje(Object[] array){
        peajes.add(array);
        // {PRECIO, DATETIME}

        ///////////////IMPRIMIR/////////////
//        System.out.println("peajes guardados: "+ peajes.size());
//        
//        for (int i = 0; i < this.peajes.size(); i++){
//            for (int j = 0; j < this.peajes.get(i).length; j++){
//                System.out.println(this.peajes.get(i)[j].toString());
//            }
//        }
//        
        ////////////////
    }
    public void cancelTimer(){
        timer.cancel();
    }
    
    public static int getSegundos(){
        return segundosTotales;
    }
    @FXML
    private void finalizar(ActionEvent event){
        //en mi mente: cuando se abra la de finalizando viaje se tendria que parar el cronometro y reanudar en el evento de close
        
        // pasarle la data a la ventana de finalizar viaje
        // y abrirla, pero no cerrar esta.
        
        
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("finalizandoViaje.fxml"));
                
                Parent scene = (Parent) loader.load();
                Stage st = new Stage();
                pausar();
               
                FinalizandoViajeController controller = loader.<FinalizandoViajeController>getController();
                controller.loadData(
                    data,               //{tipo de viaje, Duracion(ESTA NULL), Duracion total(ESTA NULL), idSalida, idLlegada, Km Iniciales, fecha de LLegada(ESTA NULL, LO SETEA CUANDO SE FINALIZA, DATETIME de la salida)}
                    gastosCombustible,  //{IdViaje, Litros, Km, Precio, DATETIME}
                    peajes              //{IdViaje, Costo, DATETIME}
                );
                st.initModality(Modality.APPLICATION_MODAL);
                st.setOnCloseRequest((WindowEvent we) -> {
                    pausar();
                });
                st.setTitle("Finalizando Viaje");
                st.setScene(new Scene(scene));
                st.show();
            }
            catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
            
        
        
    }
}

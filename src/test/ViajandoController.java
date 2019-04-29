/*
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
*/
package test;

import model.*;
import java.net.URL;
import java.util.*;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
FXML Controller class

@author Luciano
*/
public class ViajandoController implements Initializable{
    @FXML
    private Label status;
    private Object[] data;
    private int segundos=50,horas=0,minutos=0;
    @FXML
    private Label cronometro;
    private boolean pausado = false;
    private Timer timer;
    private TimerTask timerTask;
    @FXML
    private Button btnPausa;
    
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
    private void pausar(ActionEvent event){
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
        
        this.data[0] = data[0]; //tipo de viaje
        this.data[1] = null;    //duracion
        this.data[2] = null;    //duracion Total
        this.data[3] = (Integer) Database.consulta("SELECT idLugar FROM lugar WHERE ciudad=? AND direccion=? AND nDireccion=?",new Object[]{decripCiudad((String)data[1]),decripDireccion((String)data[1]),decripNDireccion((String)data[1])}).get(0).get("idLugar"); //partida
        this.data[4] = (Integer) Database.consulta("SELECT idLugar FROM lugar WHERE ciudad=? AND direccion=? AND nDireccion=?",new Object[]{decripCiudad((String)data[2]),decripDireccion((String)data[2]),decripNDireccion((String)data[2])}).get(0).get("idLugar"); //llegada
        this.data[5] = data[3]; //kilometros iniciales
        this.data[6] = null;    //fecha de llegada
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
    
}

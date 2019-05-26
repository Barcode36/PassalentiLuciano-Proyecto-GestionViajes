/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package funciones;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FuncionesFile {
    
    /**
    * Añade una linea de texto a un archivo.
    * @param String path
    * @param String texto
    */
    public static void aniadirStringArchivo(String path, String texto) {
        FileWriter fichero = null;
        try {
            fichero = new FileWriter(path,true);
            char[] textoChar = texto.toCharArray();
            for (int i = 0; i < textoChar.length; i++) {
                fichero.append(textoChar[i]);
            }
            fichero.append("\r\n");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("El archivo ya existe");
        }finally {
            if(fichero!=null){
                try {
                    fichero.close();
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }

    /**
    * Retorna la lina N de un archivo.
    * @param int numero de linea
    * @param String path del archivo
    * @return String linea de texto.
    */
    public static String leerLineaNumeroArchivo(int i, String path) {
        String linea="";
        File archivo = null;
        FileReader fr = null;
        BufferedReader br = null;
        int count = 0;
        try {
            archivo = new File (path);
            fr = new FileReader (archivo);
            br = new BufferedReader(fr);
            try {
                while(count<=i) {
                    count++;
                    linea=br.readLine();
                }
            } 
            catch (Exception e) {
                System.out.println("Error "+e.getMessage());
            }
        }
        catch(Exception e){
                System.out.println("Mensaje: " + e.getMessage());
        }finally{
                try{                    
                        if( null != fr ){   
                                br.close();
                                fr.close();     
                        }                  
                }catch (Exception e2){ 
                        e2.printStackTrace();
                }
        }
        return linea;
    }
    /**
    * Crea un archivo con las lineas que esten en el array de Strings.
    * @param String[] lineas a escribir
    * @param String path del archivo
    */
    public static void crearArchivoArrStr(String[] arrString, String path) {
        FileWriter fichero = null;
        try {
            fichero = new FileWriter(path);

            for (int i = 0; i < arrString.length; i++) {
                fichero.write(arrString[i] +"\r\n");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("El archivo ya existe");
        }finally {
            if(fichero!=null)
                try {
                    fichero.close();
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
        }
    }
    
    /**
    * Retorna la cantidad de lineas de un archivo.
    * @param String path del archivo
    * @return int cantidad de lineas
    */
    public static int getTamanio(String path) {
        File archivo = null;
        FileReader fr = null;
        BufferedReader br = null;
        int count = 0;
        try {
            archivo = new File (path);
            fr = new FileReader (archivo);
            br = new BufferedReader(fr);
            try {
                @SuppressWarnings("unused")
                String linea;
                while((linea=br.readLine())!=null) {
                    count++;
                }
            } catch (Exception e) {
                System.out.println("Error "+e.getMessage());
            }
        }
        catch(Exception e){
                System.out.println("Mensaje: " + e.getMessage());
        }finally{
                try{                    
                        if( null != fr ){   
                                br.close();
                                fr.close();     
                        }                  
                }catch (Exception e2){ 
                        e2.printStackTrace();
                }
        }
        return count;
    }
    /**
    * Crea un directorio en el path especificado.
    * @param String path del archivo
    * @return boolean exito=> true
    */
    public static boolean crearDirectorio(String path) {
        boolean funciono= false;
        try {
            File directorio=new File(path);
            directorio.mkdir(); 
            funciono= true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return funciono;

    }
    public static File abrirArchivo(String path) {
        File archivo = null;
        if((archivo = new File (path)).exists()) {
            return archivo;
        }else return null;
    }
    
    /**
    * Borra de forma recursiva un directorio o un archivo
    * @param String path del archivo
    */
    public static void  borrarDirectorio(String path) { // Borra tanto directorios como archivos en 
        File[] ficheros = abrirArchivo(path).listFiles();
        System.out.println(ficheros.length);

        for (int x = 0; x < ficheros.length; x++){
            if (ficheros[x].isDirectory()) {
                if(ficheros[x].list().length==0) {
                    ficheros[x].delete();
                }
                else borrarDirectorio(ficheros[x].getAbsolutePath());
            }
            ficheros[x].delete();
        }
    }

}





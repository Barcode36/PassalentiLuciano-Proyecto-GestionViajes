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

	public static void aniadirStringArchivo(String path, String texto) {
		FileWriter fichero = null;
		try {
			fichero = new FileWriter(path,true);
			char[] textoChar = texto.toCharArray();
			//			if(leerLineaNumeroArchivo(getTamanio(path),path)!=null) {
			//				
			//			}
			for (int i = 0; i < textoChar.length; i++) {
				fichero.append(textoChar[i]);
			}
			fichero.append("\r\n");
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
		return linea;
	}

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

	public static int getTamanio(String path) { //Retorna la cantidad de lineas que tienen un archivo
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


	public static void  borrarDirectorio(String path) { // Borra tanto directorios como archivos
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





package model;
import funciones.FuncionesFile;
import java.sql.*;


public class Conexion {
    Connection con=null;
    public Connection conectar(){
        
        
        try{
            Class.forName("com.mysql.jdbc.Driver");
            String url="jdbc:mysql://localhost:3306/camion";
            String user="root";
            String pass="";
            try {
                con = DriverManager.getConnection(url, user, pass);
            }
            catch (Exception e) {
                url="jdbc:mysql://localhost:3306/";
                user="root";
                pass="";
                con = DriverManager.getConnection(url, user, pass);
                PreparedStatement p = con.prepareStatement("CREATE DATABASE IF NOT EXISTS camion");
                p.execute();
                p = con.prepareStatement("USE camion");
                p.execute();
                loadSQLSchema();
            }
            
        }
        catch(Exception ex){
            System.out.println("Ha sido imposible crear la conexion"+ex.getMessage());
        }
        
        return con;
    }
    private void loadSQLSchema(){
        int size = FuncionesFile.getTamanio("src/camion.sql");
        for (int i=0; i < size ; i++) {
            String linea = FuncionesFile.leerLineaNumeroArchivo(i,"src/camion.sql");
            try {
                PreparedStatement s = this.con.prepareStatement(linea);
                s.execute();
            }
            catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
                
        }
    }
    
    
    public static void desconectar(Connection con){
        try{
            if (con!=null) con.close();
        } catch(Exception ex){
            System.out.println("Ha sido imposible cerrar la conexion");
        }
    }
    
}

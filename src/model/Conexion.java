package model;
import funciones.FuncionesFile;
import java.sql.*;


public class Conexion {
    
    /**
    * Datos de para establecer una connecion con el servidor mysql.
    */
    private String url = "jdbc:mysql://localhost:3306/";
    private String user= "root";
    private String pass= "";
    
    private String db="camion";
    Connection con=null;
    
    /**
    * Intententa realizar una connecion con la base de datos, de ser imposible crea la bd camion y la reallena de datos pre-existentes en el archivo src/camion.sql.
    * @return Connection
    */
    public Connection conectar(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            try {
                con = DriverManager.getConnection(url+db, user, pass);
            }
            catch (Exception e) {
                con = DriverManager.getConnection(url, user, pass);
                PreparedStatement p = con.prepareStatement("CREATE DATABASE IF NOT EXISTS camion");
                p.execute();
                p = con.prepareStatement("USE camion");
                p.execute();
                loadSQLSchema();
            }
        }
        catch(Exception ex){
            System.out.println("Ha sido imposible crear la conexion "+ex.getMessage());
        }
        
        return con;
    }
    
    /**
    * Hace una consulta con cada linea que contenga el archivo src/camion.sql.
    */
    private void loadSQLSchema(){
        int size = FuncionesFile.getTamanio("src/camion.sql");
        for (int i=0; i < size ; i++) {
            String linea = FuncionesFile.leerLineaNumeroArchivo(i,"src/camion.sql");
            System.out.println(linea);
            try {
                PreparedStatement s = this.con.prepareStatement(linea);
                s.execute();
            }
            catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
    
    /**
    * Cierra la connecion con la bd.
    * @param con Connection
    */
    public static void desconectar(Connection con){
        try{
            if (con!=null) con.close();
        } catch(Exception ex){
            System.out.println("Ha sido imposible cerrar la conexion");
        }
    }
    
}

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
        
        for (int i=0; i < FuncionesFile.getTamanio("G:\\WorkspaceProyecto\\Test\\camion.sql"); i++) {
            int j=0;
            String linea = FuncionesFile.leerLineaNumeroArchivo(i,"G:\\WorkspaceProyecto\\Test\\camion.sql");
            
            while(j<linea.length()-1){
                String statement="";
                while(linea.charAt(j)!=';' && j<linea.length()-1){
                    statement+=linea.charAt(j);
                    j++;
                }
                statement+=';';
                statement= statement.replace("'","\"");
                System.out.println(statement);
                PreparedStatement s;
                try {
                    s = this.con.prepareStatement(statement);
                    s.execute();
                }
                catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
                
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

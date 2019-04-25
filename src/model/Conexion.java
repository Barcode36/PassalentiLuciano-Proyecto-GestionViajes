package model;
import java.sql.*;


public class Conexion {

	public Connection conectar(){
		Connection con=null;

		try{            
			Class.forName("com.mysql.jdbc.Driver");
			String url="jdbc:mysql://localhost:3306/camion";
			String user="root";
			String pass="";
			con = DriverManager.getConnection(url, user, pass);

		}
		catch(Exception ex){            
			System.out.println("Ha sido imposible crear la conexion"+ex.getMessage());
		}

		return con; 
	}

	public static void desconectar(Connection con){
		try{
			if (con!=null) con.close();
		} catch(Exception ex){
			System.out.println("Ha sido imposible cerrar la conexion");
		}
	}

}

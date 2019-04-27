package model;
import java.sql.*;
import java.util.*;

public class Database {

	public static HashMap<Integer,HashMap<String,Object>> consulta(String statement) {
		PreparedStatement stmt=null;
		ResultSet rs = null;
		HashMap<Integer,HashMap<String,Object>> output = new HashMap<Integer,HashMap<String,Object>>();
		Connection con = startConnection();
		try {            
			stmt = con.prepareStatement(statement);
			rs = stmt.executeQuery();
		}
		catch(Exception ex){
			System.out.println(ex.getMessage());
		}
		finally {
			try {
				output = convertResultSetToHashMap(rs);
				rs.close();
				closeConnection(con);
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		}
		return output;
	}
	public static HashMap<Integer,HashMap<String,Object>> consulta(String statement, Object[] vars) {
		PreparedStatement stmt=null;
		ResultSet rs = null;
		HashMap<Integer,HashMap<String,Object>> output = new HashMap<Integer,HashMap<String,Object>>();
		Connection con = startConnection();
		try {            
			stmt = con.prepareStatement(statement);
			for (int i = 0; i < vars.length; i++) {
				stmt.setObject(i+1,vars[i]);
			}
			rs = stmt.executeQuery();
		}
		catch(Exception ex){
			System.out.println(ex.getMessage());
		}
		finally {
			try {
				if(output!=null) {
					output = convertResultSetToHashMap(rs);
				}
				rs.close();
				closeConnection(con);
			}
			catch (SQLException e) {
				System.out.println(e.getMessage());
			}
			catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		return output;
	}
        public static void insert(String statement, Object[] vars) {
		PreparedStatement stmt=null;
                Connection con = startConnection();
		try {            
			stmt = con.prepareStatement(statement);
			for (int i = 0; i < vars.length; i++) {
				stmt.setObject(i+1,vars[i]);
			}
			stmt.execute();
		}
		catch(Exception ex){
			System.out.println(ex.getMessage());
		}
		finally {
			try {
                            closeConnection(con);
			}
			catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}

	}
	private static HashMap<Integer,HashMap<String,Object>> convertResultSetToHashMap(ResultSet rs) throws SQLException {
		ResultSetMetaData md = (ResultSetMetaData) rs.getMetaData();
		HashMap<Integer,HashMap<String,Object>> map = new HashMap<Integer,HashMap<String,Object>>();
		int columns = md.getColumnCount();
		int j =0;

		while (rs.next()) {
			HashMap<String,Object> row = new HashMap<String, Object>(columns);
			for(int i=1; i<=columns; ++i) {
				row.put(md.getColumnName(i),rs.getObject(i));
			}
			map.put(j, row);
			j++;
		}
		return map;
	}
	private static void closeConnection(Connection con) {
		try {
			if(!con.isClosed()) {
				Conexion.desconectar(con);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	private static Connection startConnection() {
		Conexion conexion = new Conexion();
		Connection con = null;
		try {
			con = conexion.conectar();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return con;
	}
}



package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashMap;


public class Database {

    /**
    * Realiza una consulta a la bd.
    * @param statement String
    * @return HashMap Integer,HashMap String,Object
    */
    public static HashMap<Integer,HashMap<String,Object>> consulta(String statement) {
        PreparedStatement stmt=null;
        ResultSet rs = null;
        HashMap<Integer,HashMap<String,Object>> output = new HashMap<Integer,HashMap<String,Object>>();
        Connection con = startConnection();
        try {            
            stmt = con.prepareStatement(statement+";");
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

    /**
    * Realiza una consulta a la bd con un array de parametros de tipo objeto.
    * 
    * @param statement String consulta
    * @param vars Object[] variables de la consulta
    * @return HashMap Integer,HashMap String,Object
    */
    public static HashMap<Integer,HashMap<String,Object>> consulta(String statement, Object[] vars) {
        PreparedStatement stmt=null;
        ResultSet rs = null;
        HashMap<Integer,HashMap<String,Object>> output = new HashMap<Integer,HashMap<String,Object>>();
        Connection con = startConnection();
        try {            
            stmt = con.prepareStatement(statement+";");
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

    /**
    * Funciona tanto para inserciones/deletes y updates
    * @param vars Object[] Variables de la consulta
    * @param statement String consulta
    */
    public static void insert(String statement, Object[] vars) {
        PreparedStatement stmt=null;
        Connection con = startConnection();
        try {            
            stmt = con.prepareStatement(statement+";");
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

    /**
    * Convierte de resultSet a HashMap<Integer,HashMap<String,Object>> 
    * @param rs ResultSet
    * @return HashMap<Integer,HashMap<String,Object>>
    */
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
        } 
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
    * Crea la conneccion con la bd.
    * @return Connection
    */
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



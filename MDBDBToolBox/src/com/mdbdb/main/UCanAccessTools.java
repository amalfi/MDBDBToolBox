package com.mdbdb.main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UCanAccessTools 
{
	/**
	 *  Function which returns query result as a list of maps with Connection in parameter
	 * @param conn
	 * @param query
	 * @return List<Map<String, String>> list of maps with takes all selected vales from query
	 * @throws SQLException
	 */
	@SuppressWarnings("unchecked")
	public static List<Map<String, String>> getQueryResultWithConnectionAsParameter(Connection conn, String query) throws SQLException
	{
		List<Map<String, String>> list = null;
		try 
		{
			Statement st =conn.createStatement();
			ResultSet rs=st.executeQuery(query);

			list = new ArrayList<Map<String, String>>(); 
			ResultSetMetaData meta = rs.getMetaData();
		        while (rs.next()) 
		        {
		            @SuppressWarnings("rawtypes")
					Map map = new HashMap();
		            for (int i = 1; i <= meta.getColumnCount(); i++) {
		                String key = meta.getColumnName(i);
		                String value = rs.getString(key);
		              
		                value=value.trim();
		                
		                map.put(key, value);
		            }
		            list.add(map);
		        }
			//conn.close();
		} 
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		
		return list;
	}
	@SuppressWarnings("unchecked")
	/**
	 * Function which returns query result as a list of maps with creating and closing own Connection object
	 * @param conn
	 * @param query
	 * @return List<Map<String, String>> list of maps with takes all selected vales from query
	 * @throws SQLException
	 */
	public static List<Map<String, String>> getQueryResultWithOwnConnection(String query) throws SQLException, ClassNotFoundException
	{
		List<Map<String, String>> list = null;
		try 
		{
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			Connection conn=DriverManager.getConnection("jdbc:ucanaccess://C:/Users/Marcin/Desktop/MDB/Promocje/Promocje.mdb","", ""); 
			Statement st =conn.createStatement();
			ResultSet rs=st.executeQuery(query);

			list = new ArrayList<Map<String, String>>(); 
			ResultSetMetaData meta = rs.getMetaData();
		        while (rs.next()) 
		        {
		            @SuppressWarnings("rawtypes")
					Map map = new HashMap();
		            for (int i = 1; i <= meta.getColumnCount(); i++) {
		                String key = meta.getColumnName(i);
		                String value = rs.getString(key);
		              
		                value=value.trim();
		                
		                map.put(key, value);
		            }
		            list.add(map);
		        }
			conn.close();
		} 
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		
		return list;
	}
	
	public Connection getConnection() throws SQLException
	{
		Connection conn=null;
		try 
		{
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			conn=DriverManager.getConnection("jdbc:ucanaccess://C:/Users/Marcin/Desktop/MDB/Promocje/Promocje.mdb","", ""); 
		} 
		catch (ClassNotFoundException e) 
		{
			e.printStackTrace();
		}
		return conn;
	}
	
	public void closeConnection(Connection conn)
	{
		try
		{
			conn.close();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}
}

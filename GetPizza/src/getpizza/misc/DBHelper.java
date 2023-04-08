package getpizza.misc;

import java.util.List;
import java.util.Map;
import java.sql.*;
import getpizza.model.*;

public class DBHelper {
	static DBHelper instance = null;
	
	static String bd = "cliente";
	static String login = "XYZ";
	static String password = "ABC";
	static String url = "jdbc:mysql://localhost/" + bd;
	Connection connection = null;

	private DBHelper() {
		try {
			// String url = " jdbc : mysql :// hostname / database - name ";
			connection = DriverManager.getConnection(url, login, password);
		} catch (SQLException ex) {
			connection = null;
			ex.printStackTrace();
			System.out.println(" SQLException : " + ex.getMessage());
			System.out.println(" SQLState : " + ex.getSQLState());
			System.out.println(" VendorError : " + ex.getErrorCode());
		}
	}
	
	public static DBHelper getInstance() {
		if(instance == null)
			instance = new DBHelper();
		
		return instance;
	}

	public Connection getConnection() {
		return connection;
	}

	public void desconectar() {
		connection = null;
	}

	public void createClient(Map<String, String> datos) {

	}

	public Cliente getClient(String username, String password) {

		return null;
	}

	public void sendOrder(Cliente cliente) {

	}

	public <T> List<T> getCodigoDescuento() {

		return null;
	}

}

package getpizza.misc;

import java.util.List;
import java.sql.*;
import getpizza.model.*;

public class DBHelper {
	static String bd = "cliente";
	static String login = "XYZ";
	static String password = "ABC";
	static String url = "jdbc:mysql://localhost/" + bd;
	Connection connection = null;

	public DBHelper() {
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

	public Connection getConnection() {
		return connection;
	}

	public void desconectar() {
		connection = null;
	}

	public static void createClient(Cliente cliente) {

	}

	public static Cliente getClient(String username, String password) {

		return null;
	}

	public static void sendOrder(Cliente cliente) {

	}

	public static <T> List<T> getCodigoDescuento() {

		return null;
	}

}

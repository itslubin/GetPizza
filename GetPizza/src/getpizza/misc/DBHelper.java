package getpizza.misc;

import java.util.List;

import com.google.gson.Gson;

import java.sql.*;
import getpizza.model.*;

public class DBHelper {
	static DBHelper instance = null;
	
	static String bd = "cliente";
	static String login = "root";
	static String password = "lfearivsbdn";
	static String url = "jdbc:mysql://sql.zcoding.cc/";
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

	public void createClient(Cliente cliente) {
		String data = new Gson().toJson(cliente);
		// TODO
	}

	public Cliente getClient(String username, String password) {
		String data = "";
		// TODO
		try {
			Cliente cliente = new Gson().fromJson(data, Cliente.class);
			if(cliente.password.equals(password))
				return cliente;
		}catch(Exception e) {
			Utils.showErrorMsg("DB Error");
		}
		return null;
	}

	public void sendOrder(Cliente cliente) {

	}

	public <T> List<T> getCodigoDescuento() {

		return null;
	}

	public static void main(String[] arg) {
		//DBHelper.getInstance();
		System.out.println(DBHelper.getInstance().toString());
	}
}

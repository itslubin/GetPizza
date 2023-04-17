package getpizza.misc;

import com.google.gson.Gson;
import getpizza.model.*;
import redis.clients.jedis.Jedis;

public class DBHelper {
	static DBHelper instance = null;
	Jedis jedis = null;

	private DBHelper() {
		try {
			jedis = new Jedis("zithuang.top", 6379);
			jedis.auth("lfearivsbdn");
		} catch (Exception ex) {
			jedis = null;
			ex.printStackTrace();
		}
	}

	public static DBHelper getInstance() {
		if (instance == null)
			instance = new DBHelper();

		return instance;
	}

	public Jedis getConnection() {
		return jedis;
	}

	public void desconectar() {
		jedis.close();
	}
	
	public boolean exists(String username) {
		return jedis.exists(username);
	}

	public void setClient(Cliente cliente) {
		String data = new Gson().toJson(cliente);
		jedis.set(cliente.getId(), data);
	}

	public Cliente getClient(String username, String password) {
		if (jedis.exists(username)) {
			String data = jedis.get(username);
			try {
				Cliente cliente = new Gson().fromJson(data, Cliente.class);
				if (cliente.password.equals(password))
					return cliente;
				else
					Utils.showErrorMsg("Contraseña incorrecta");
			} catch (Exception e) {
				Utils.showErrorMsg("DB Error");
			}
		}
		else
			Utils.showErrorMsg("Usuario no existe");
		
		return null;
	}

}

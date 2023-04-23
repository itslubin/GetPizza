package getpizza.misc;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

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

	public String get(String key) {
		return jedis.get(key);
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
		} else
			Utils.showErrorMsg("Usuario no existe");

		return null;
	}
	
	public List<DescuentoPorCodigo> getCodigo(){
		Gson gson = new Gson();
		JsonObject pizzajson = gson.fromJson(DBHelper.getInstance().get("descuentoPorCodigo"), JsonObject.class);
		List<DescuentoPorCodigo> codigo = new ArrayList<>();
		JsonArray codigos = pizzajson.getAsJsonArray("descuentoPorCodigo");
		for (JsonElement p : codigos) {
			codigo.add(gson.fromJson(p, DescuentoPorCodigo.class));
		}
		return codigo;
	}

	public static void main(String[] arg) {

		Jedis jedis = new Jedis("zithuang.top", 6379);
		jedis.auth("lfearivsbdn");

		jedis.set("descuentoPorCodigo", "{\"descuentoPorCodigo\":[{\"porcentaje\":0.05,\"precioDescontado\":0,\"codigo\":\"PIZZA5FF\"},{\"porcentaje\":0.1,\"precioDescontado\":0,\"codigo\":\"PIZZA10FF\"},{\"porcentaje\":0.15,\"precioDescontado\":0,\"codigo\":\"PIZZA15FF\"},{\"porcentaje\":0.1,\"precioDescontado\":0,\"codigo\":\"WELCOME10\"}]}");
		System.out.println(jedis.ping());
		
		jedis.close();
	}
}

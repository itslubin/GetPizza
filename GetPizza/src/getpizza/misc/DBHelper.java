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
	
	public List<Ingrediente> getIngrediente(){
		Gson gson = new Gson();
		JsonObject pizzajson = gson.fromJson(DBHelper.getInstance().get("ingredientes"), JsonObject.class);
		List<Ingrediente> ingrediente = new ArrayList<>();
		JsonArray ingredientes = pizzajson.getAsJsonArray("ingredientes");
		for (JsonElement p : ingredientes) {
			ingrediente.add(gson.fromJson(p, Ingrediente.class));
		}
		return ingrediente;
	}

	public static void main(String[] arg) {

		Jedis jedis = new Jedis("zithuang.top", 6379);
		jedis.auth("lfearivsbdn");
		
//		jedis.set("bebidas", addProducto("{\"nombre\":\"Agua Mineral\",\"precio\":1.5,\"desc\":\"Agua Mineral sin gas\",\"id\":\"agua_mineral\",\"tamanyo\":\"Grande\"}", "bebidas"));
		jedis.set("bebidas", removeProducto("agua_mineral", "bebidas"));
		
		System.out.println(jedis.ping());
		
		jedis.close();
	}
	
	private static String removeProducto(String id, String tipo) {
		Gson gson = new Gson();
		JsonObject productos = gson.fromJson(DBHelper.getInstance().get(tipo), JsonObject.class);
		JsonArray productArray = productos.getAsJsonArray(tipo);
		for(int i = 0; i < productArray.size(); i++) {
			if(productArray.get(i).getAsJsonObject().get("id").getAsString().equals(id)) {
				productArray.remove(i);
				break;
			}
		}

		return gson.toJson(productos);
	}
	
	private static String addProducto(String jsonobject, String tipo) {
		Gson gson = new Gson();
		JsonObject productos = gson.fromJson(DBHelper.getInstance().get(tipo), JsonObject.class);
		JsonArray productArray = productos.getAsJsonArray(tipo);
		productArray.add(gson.fromJson(jsonobject, JsonObject.class));
		
		return gson.toJson(productos);
	}
	
}

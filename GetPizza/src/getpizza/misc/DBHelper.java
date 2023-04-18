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

	public static void main(String[] arg) {

		Jedis jedis = new Jedis("zithuang.top", 6379);
		jedis.auth("lfearivsbdn");

		jedis.set("pizzas",
				"{\"pizzas\":[{\"nombre\":\"Pizza Margarita\",\"precio\":9.99,\"desc\":\"Margarita\",\"id\":\"p1\",\"masa\":\"Napolitana\",\"tamanyo\":\"Mediano\",\"base\":\"Queso\",\"ingredientes\":[\"Salsa de tomate\",\"Mozzarella fresca\",\"Albahaca fresca\"]},{\"nombre\":\"Pizza Pepperoni\",\"precio\":12.99,\"desc\":\"Pepperoni\",\"id\":\"p2\",\"masa\":\"Americana\",\"tamanyo\":\"Grande\",\"base\":\"Queso\",\"ingredientes\":[\"Salsa de tomate\",\"Queso mozzarella\",\"Pepperoni en rodajas\"]},{\"nombre\":\"Pizza Hawaiana\",\"precio\":14.99,\"desc\":\"Hawaiana\",\"id\":\"p3\",\"masa\":\"Romana\",\"tamanyo\":\"Grande\",\"base\":\"Tomate\",\"ingredientes\":[\"Salsa de tomate\",\"Queso mozzarella\",\"Jamón\",\"Piña\"]},{\"nombre\":\"Pizza Napolitana\",\"precio\":11.99,\"desc\":\"Napolitana\",\"id\":\"p4\",\"masa\":\"Napolitana\",\"tamanyo\":\"Mediano\",\"base\":\"Tomate\",\"ingredientes\":[\"Salsa de tomate\",\"Mozzarella fresca\",\"Anchoas\",\"Aceitunas negras\"]},{\"nombre\":\"Pizza Cuatro Quesos\",\"precio\":13.99,\"desc\":\"Cuatro Quesos\",\"id\":\"p5\",\"masa\":\"Romana\",\"tamanyo\":\"Grande\",\"base\":\"Queso_y_tomate\",\"ingredientes\":[\"Salsa de tomate\",\"Queso mozzarella\",\"Queso gorgonzola\",\"Queso parmesano\",\"Queso provolone\"]},{\"nombre\":\"Pizza Vegetariana\",\"precio\":10.99,\"desc\":\"Vegetariana\",\"id\":\"p6\",\"masa\":\"Americana\",\"tamanyo\":\"Pequenyo\",\"base\":\"Sin_base\",\"ingredientes\":[\"Salsa de tomate\",\"Queso mozzarella\",\"Espinacas\",\"Tomate\",\"Cebolla\",\"Pimiento\",\"Aceitunas negras\"]}]}");

		jedis.set("bebidas",
				"{\"bebidas\":[{\"nombre\":\"Agua Mineral\",\"precio\":1.5,\"desc\":\"Agua Mineral sin gas\",\"id\":\"agua_mineral\",\"tamanyo\":\"Grande\"},{\"nombre\":\"Fanta Naranja\",\"precio\":2.0,\"desc\":\"Refresco de naranja\",\"id\":\"fanta_naranja\",\"tamanyo\":\"Pequenyo\"},{\"nombre\":\"Fanta Limón\",\"precio\":2.0,\"desc\":\"Refresco de limón\",\"id\":\"fanta_limon\",\"tamanyo\":\"Mediano\"},{\"nombre\":\"Coca Cola\",\"precio\":2.5,\"desc\":\"Refresco de cola\",\"id\":\"coca_cola\",\"tamanyo\":\"Grande\"},{\"nombre\":\"Nestea\",\"precio\":2.0,\"desc\":\"Té frío\",\"id\":\"nestea\",\"tamanyo\":\"Pequenyo\"},{\"nombre\":\"Zumo de Frutas\",\"precio\":2.5,\"desc\":\"Zumo de frutas variadas\",\"id\":\"zumo_frutas\",\"tamanyo\":\"Mediano\"}]}");

		jedis.set("postres",
				"{\"postres\":[{\"nombre\":\"Tiramisú\",\"precio\":4.5,\"desc\":\"Postre italiano hecho con bizcochos de soletilla, café, queso mascarpone, huevos y cacao en polvo\",\"id\":\"tiramisu\"},{\"nombre\":\"Crème Brûlée\",\"precio\":5.0,\"desc\":\"Postre francés hecho con crema de leche, yema de huevo, azúcar y vainilla, cubierto con una capa de caramelo quemado\",\"id\":\"creme_brulee\"},{\"nombre\":\"Profiteroles\",\"precio\":4.0,\"desc\":\"Postre francés hecho con pasta choux rellena de crema pastelera y cubierta con chocolate derretido\",\"id\":\"profiteroles\"},{\"nombre\":\"Tarta de Chocolate\",\"precio\":4.5,\"desc\":\"Postre hecho con bizcocho de chocolate y una capa de chocolate fundido por encima\",\"id\":\"tarta_chocolate\"},{\"nombre\":\"Flan\",\"precio\":3.5,\"desc\":\"Postre hecho con huevo, leche, azúcar y caramelo líquido\",\"id\":\"flan\"}]}");
		
		System.out.println(jedis.ping());
	}
}

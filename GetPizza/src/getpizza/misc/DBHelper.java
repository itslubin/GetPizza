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

	public static void main (String[] arg) {

		Jedis jedis = new Jedis("zithuang.top", 6379);
		jedis.auth("lfearivsbdn");

		jedis.set("pizzas","{\r\n"
				+ "  \"pizzas\": {\r\n"
				+ "    \"p1\": {\r\n"
				+ "      \"nombre\": \"Pizza Margarita\",\r\n"
				+ "      \"precio\": 9.99,\r\n"
				+ "      \"desc\": \"Margarita\",\r\n"
				+ "      \"id\": \"p1\",\r\n"
				+ "      \"masa\": \"Napolitana\",\r\n"
				+ "      \"tamanyo\": \"Mediano\",\r\n"
				+ "      \"base\": \"Queso\",\r\n"
				+ "      \"ingredientes\": [\r\n"
				+ "        \"Salsa de tomate\",\r\n"
				+ "        \"Mozzarella fresca\",\r\n"
				+ "        \"Albahaca fresca\"\r\n"
				+ "      ]\r\n"
				+ "    },\r\n"
				+ "    \"p2\": {\r\n"
				+ "      \"nombre\": \"Pizza Pepperoni\",\r\n"
				+ "      \"precio\": 12.99,\r\n"
				+ "      \"desc\": \"Pepperoni\",\r\n"
				+ "      \"id\": \"p2\",\r\n"
				+ "      \"masa\": \"Americana\",\r\n"
				+ "      \"tamanyo\": \"Grande\",\r\n"
				+ "      \"base\": \"Queso\",\r\n"
				+ "      \"ingredientes\": [\r\n"
				+ "        \"Salsa de tomate\",\r\n"
				+ "        \"Queso mozzarella\",\r\n"
				+ "        \"Pepperoni en rodajas\"\r\n"
				+ "      ]\r\n"
				+ "    },\r\n"
				+ "    \"p3\": {\r\n"
				+ "      \"nombre\": \"Pizza Hawaiana\",\r\n"
				+ "      \"precio\": 14.99,\r\n"
				+ "      \"desc\": \"Hawaiana\",\r\n"
				+ "      \"id\": \"p3\",\r\n"
				+ "      \"masa\": \"Romana\",\r\n"
				+ "      \"tamanyo\": \"Grande\",\r\n"
				+ "      \"base\": \"Tomate\",\r\n"
				+ "      \"ingredientes\": [\r\n"
				+ "        \"Salsa de tomate\",\r\n"
				+ "        \"Queso mozzarella\",\r\n"
				+ "        \"Jamón\",\r\n"
				+ "        \"Piña\"\r\n"
				+ "      ]\r\n"
				+ "    },\r\n"
				+ "    \"p4\": {\r\n"
				+ "      \"nombre\": \"Pizza Napolitana\",\r\n"
				+ "      \"precio\": 11.99,\r\n"
				+ "      \"desc\": \"Napolitana\",\r\n"
				+ "      \"id\": \"p4\",\r\n"
				+ "      \"masa\": \"Napolitana\",\r\n"
				+ "      \"tamanyo\": \"Mediano\",\r\n"
				+ "      \"base\": \"Tomate\",\r\n"
				+ "      \"ingredientes\": [\r\n"
				+ "        \"Salsa de tomate\",\r\n"
				+ "        \"Mozzarella fresca\",\r\n"
				+ "        \"Anchoas\",\r\n"
				+ "        \"Aceitunas negras\"\r\n"
				+ "      ]\r\n"
				+ "    },\r\n"
				+ "    \"p5\": {\r\n"
				+ "      \"nombre\": \"Pizza Cuatro Quesos\",\r\n"
				+ "      \"precio\": 13.99,\r\n"
				+ "      \"desc\": \"Cuatro Quesos\",\r\n"
				+ "      \"id\": \"p5\",\r\n"
				+ "      \"masa\": \"Romana\",\r\n"
				+ "      \"tamanyo\": \"Grande\",\r\n"
				+ "      \"base\": \"Queso_y_tomate\",\r\n"
				+ "      \"ingredientes\": [\r\n"
				+ "        \"Salsa de tomate\",\r\n"
				+ "        \"Queso mozzarella\",\r\n"
				+ "        \"Queso gorgonzola\",\r\n"
				+ "        \"Queso parmesano\",\r\n"
				+ "        \"Queso provolone\"\r\n"
				+ "      ]\r\n"
				+ "    },\r\n"
				+ "    \"p6\": {\r\n"
				+ "      \"nombre\": \"Pizza Vegetariana\",\r\n"
				+ "      \"precio\": 10.99,\r\n"
				+ "      \"desc\": \"Vegetariana\",\r\n"
				+ "      \"id\": \"p6\",\r\n"
				+ "      \"masa\": \"Americana\",\r\n"
				+ "      \"tamanyo\": \"Pequenyo\",\r\n"
				+ "      \"base\": \"Sin_base\",\r\n"
				+ "      \"ingredientes\": [\r\n"
				+ "        \"Salsa de tomate\",\r\n"
				+ "        \"Queso mozzarella\",\r\n"
				+ "        \"Espinacas\",\r\n"
				+ "        \"Tomate\",\r\n"
				+ "        \"Cebolla\",\r\n"
				+ "        \"Pimiento\",\r\n"
				+ "        \"Aceitunas negras\"\r\n"
				+ "      ]\r\n"
				+ "    }\r\n"
				+ "}");
		
		
		jedis.set("bebidas", "{\r\n"
				+ "  \"bebidas\": {\r\n"
				+ "    \"agua_mineral\": {\r\n"
				+ "      \"nombre\": \"Agua Mineral\",\r\n"
				+ "      \"precio\": 1.50,\r\n"
				+ "      \"desc\": \"Agua Mineral sin gas\",\r\n"
				+ "      \"id\": \"agua_mineral\",\r\n"
				+ "      \"tamanyo\": \"Grande\"\r\n"
				+ "    },\r\n"
				+ "    \"fanta_naranja\": {\r\n"
				+ "      \"nombre\": \"Fanta Naranja\",\r\n"
				+ "      \"precio\": 2.00,\r\n"
				+ "      \"desc\": \"Refresco de naranja\",\r\n"
				+ "      \"id\": \"fanta_naranja\",\r\n"
				+ "      \"tamanyo\": \"Pequenyo\"\r\n"
				+ "    },\r\n"
				+ "    \"fanta_limon\": {\r\n"
				+ "      \"nombre\": \"Fanta Limón\",\r\n"
				+ "      \"precio\": 2.00,\r\n"
				+ "      \"desc\": \"Refresco de limón\",\r\n"
				+ "      \"id\": \"fanta_limon\",\r\n"
				+ "      \"tamanyo\": \"Mediano\"\r\n"
				+ "    },\r\n"
				+ "    \"coca_cola\": {\r\n"
				+ "      \"nombre\": \"Coca Cola\",\r\n"
				+ "      \"precio\": 2.50,\r\n"
				+ "      \"desc\": \"Refresco de cola\",\r\n"
				+ "      \"id\": \"coca_cola\",\r\n"
				+ "      \"tamanyo\": \"Grande\"\r\n"
				+ "    },\r\n"
				+ "    \"nestea\": {\r\n"
				+ "      \"nombre\": \"Nestea\",\r\n"
				+ "      \"precio\": 2.00,\r\n"
				+ "      \"desc\": \"Té frío\",\r\n"
				+ "      \"id\": \"nestea\",\r\n"
				+ "      \"tamanyo\": \"Pequenyo\"\r\n"
				+ "    },\r\n"
				+ "    \"zumo_frutas\": {\r\n"
				+ "      \"nombre\": \"Zumo de Frutas\",\r\n"
				+ "      \"precio\": 2.50,\r\n"
				+ "      \"desc\": \"Zumo de frutas variadas\",\r\n"
				+ "      \"id\": \"zumo_frutas\",\r\n"
				+ "      \"tamanyo\": \"Mediano\"\r\n"
				+ "    }\r\n"
				+ "  }\r\n"
				+ "}");
		
		jedis.set("postres", "{\r\n"
				+ "  \"postres\": {\r\n"
				+ "    \"tiramisu\": {\r\n"
				+ "      \"nombre\": \"Tiramisú\",\r\n"
				+ "      \"precio\": 4.50,\r\n"
				+ "      \"desc\": \"Postre italiano hecho con bizcochos de soletilla, café, queso mascarpone, huevos y cacao en polvo\",\r\n"
				+ "      \"id\": \"tiramisu\"\r\n"
				+ "    },\r\n"
				+ "    \"creme_brulee\": {\r\n"
				+ "      \"nombre\": \"Crème Brûlée\",\r\n"
				+ "      \"precio\": 5.00,\r\n"
				+ "      \"desc\": \"Postre francés hecho con crema de leche, yema de huevo, azúcar y vainilla, cubierto con una capa de caramelo quemado\",\r\n"
				+ "      \"id\": \"creme_brulee\"\r\n"
				+ "    },\r\n"
				+ "    \"profiteroles\": {\r\n"
				+ "      \"nombre\": \"Profiteroles\",\r\n"
				+ "      \"precio\": 4.00,\r\n"
				+ "      \"desc\": \"Postre francés hecho con pasta choux rellena de crema pastelera y cubierta con chocolate derretido\",\r\n"
				+ "      \"id\": \"profiteroles\"\r\n"
				+ "    },\r\n"
				+ "    \"tarta_chocolate\": {\r\n"
				+ "      \"nombre\": \"Tarta de Chocolate\",\r\n"
				+ "      \"precio\": 4.50,\r\n"
				+ "      \"desc\": \"Postre hecho con bizcocho de chocolate y una capa de chocolate fundido por encima\",\r\n"
				+ "      \"id\": \"tarta_chocolate\"\r\n"
				+ "    },\r\n"
				+ "    \"flan\": {\r\n"
				+ "      \"nombre\": \"Flan\",\r\n"
				+ "      \"precio\": 3.50,\r\n"
				+ "      \"desc\": \"Postre hecho con huevo, leche, azúcar y caramelo líquido\",\r\n"
				+ "      \"id\": \"flan\"\r\n"
				+ "    }\r\n"
				+ "  }\r\n"
				+ "}\r\n"
				+ "");
	}
}

package getpizza.misc;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.google.gson.Gson;

import getpizza.control.SACliente;
import getpizza.model.Postre;

class TestProductos {

	@Test
	void test() {
		DBHelper DBHelper = getpizza.misc.DBHelper.getInstance();

		try {

			// Añadimos un nuevo producto Postre "Petit-Suisse"
			String postreJSON = "{\"nombre\":\"Petit-Suisse\",\"precio\":2.0,\"desc\":\"El postre preferido de los peques\",\"id\":\"petit_suisse\"}";

			DBHelper.jedis.set("postres", DBHelper.addProducto(postreJSON, "postres"));

			// Cargamos los productos a la aplicación
			SACliente saCliente = new SACliente(null);
			saCliente.importProductos();

			Gson gson = new Gson();
			Postre postre = gson.fromJson(postreJSON, Postre.class);

			// Comprobamos si se ha metido bien el producto a la aplicación
			List<Postre> lPostres = saCliente.getPostres();
			boolean contains = false;
			for (Postre p : lPostres) {
				if (p.getId().equals(postre.getId())) {
					contains = true;
					break;
				}
			}			

			// Eliminamos el producto de la base de datos
			DBHelper.jedis.set("postres", DBHelper.removeProducto("petit_suisse", "postres"));

			// Comprobamos si se ha metido bien el producto
			assertTrue("Producto mal añadido", contains);

		} catch (Exception e) {
			System.out.println(e.toString());
		}

		DBHelper.jedis.close();
	}

}
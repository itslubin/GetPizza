package getpizza.misc;

import static org.junit.Assert.*;

import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.Test;

import getpizza.control.Controller;
import getpizza.control.SACliente;
import getpizza.model.Cliente;
import getpizza.model.Descuento;
import getpizza.model.Menu;
import getpizza.model.Pedido;
import getpizza.model.Pizza;

class TestClientes {

	@Test
	void test() {
		DBHelper DBHelper = new DBHelper();
		boolean clienteAdded = false;
		boolean pedidoCorrecto = false;
		
		try {

			// Añadimos un nuevo cliente "prueba"
			String clienteJSON = "{\"id\":\"prueba\",\"nombre\":\"Prueba\",\"apellido\":\"JUnit\",\"correo\":\"prueba@gmail.com\",\"telefono\":\"123456789\",\"direccion\":\"a\",\"ciudad\":\"A\",\"provincia\":\"B\",\"codigoPostal\":\"123456\",\"password\":\"a\",\"membresia\":{\"rango\":\"Oro\",\"puntos\":378,\"puntosTotales\":567},\"codigosUsados\":[],\"historia\":[]}";

			DBHelper.jedis.set("prueba", clienteJSON);

			// Comprobamos si el cliente se ha metido correctamente a la base de datos
			
			Cliente cliente = getpizza.misc.DBHelper.getInstance().getClient("prueba", "a"); // usuario, contraseña
			if (cliente != null)
				clienteAdded = true;
			
			// Hacemos un pedido con el cliente
			Controller ctrl = new Controller();
			Pedido pedido = new Pedido();
			Menu carrito = new Menu();
			
			SACliente saCliente = new SACliente(null);
			ctrl.setCliente(cliente);
			saCliente.importProductos();
			List<Pizza> lPizzas = saCliente.getPizzas();
			Pizza pizzaPedido = lPizzas.get(0); // Cogemos la primera pizza que tenemos añadida en la base de datos: Pizza Pepperoni
			
			carrito.addProducto(pizzaPedido);
			pedido.setId(UUID.randomUUID().toString());
			pedido.setDireccion(cliente.getDireccion());
			pedido.setPrecio(pizzaPedido.getPrecio());
			pedido.setPrecioFinal(pizzaPedido.getPrecio());
			pedido.setConDatafono(true);
			ctrl.sendOrder(pedido, new Descuento());
			
			if(cliente.historia.get(0).equals(pedido))
				pedidoCorrecto = true;

			// Eliminamos el cliente de la base de datos
			DBHelper.jedis.del("prueba");

		} catch (Exception e) {
			System.out.println(e.toString());
		}
		
		assertTrue("Cliente mal añadido", clienteAdded);
		assertTrue("Pedido mal añadido al historial", pedidoCorrecto);

		DBHelper.jedis.close();
	}

}
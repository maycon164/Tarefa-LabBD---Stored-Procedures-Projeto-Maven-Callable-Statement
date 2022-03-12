package model;

import java.sql.SQLException;
import java.util.Date;

import persistence.ClienteDao;
import persistence.GenericDao;

public class Teste {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		ClienteDao cd = new ClienteDao(new GenericDao());

		//Cliente c = cd.selectOne("50409978842");
		//System.out.println(c);

		// List<Cliente> clientes = cd.selectAll();
		// clientes.forEach(c -> System.out.println(c.toString()));

		Cliente cliente = new Cliente("95425364021", "Huanca", "mayconfelipe164@gmail.com", 100.90, new Date());
		cd.update(cliente);
		// cd.insert(cliente);
		// cd.delete(cliente.getCpf());
	}
}

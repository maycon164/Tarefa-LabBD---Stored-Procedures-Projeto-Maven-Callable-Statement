package model;

import java.sql.SQLException;
import java.util.Date;

import persistence.ClienteDao;
import persistence.GenericDao;

public class Teste {

	
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		ClienteDao cd = new ClienteDao(new GenericDao());
		Cliente cliente = new Cliente("50409978841", "Maycon", "mayconfelipe164@gmail.com", 100.90, new Date());
		cd.insert(cliente);
		
	}
}

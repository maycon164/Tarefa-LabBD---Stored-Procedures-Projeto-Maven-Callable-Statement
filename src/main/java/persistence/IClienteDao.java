package persistence;

import java.sql.SQLException;
import java.util.List;

import model.Cliente;

public interface IClienteDao {
	
	void insert(Cliente cliente) throws SQLException, ClassNotFoundException;
	void update(Cliente cliente, int id) throws SQLException, ClassNotFoundException;
	void delete(int id) throws SQLException, ClassNotFoundException;

	Cliente selectOne(int id) throws SQLException, ClassNotFoundException;
	List<Cliente>selectAll() throws SQLException, ClassNotFoundException;
} 

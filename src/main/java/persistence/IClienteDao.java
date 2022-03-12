package persistence;

import java.sql.SQLException;
import java.util.List;

import model.Cliente;

public interface IClienteDao {
	
	void insert(Cliente cliente) throws SQLException, ClassNotFoundException;
	void update(Cliente cliente) throws SQLException, ClassNotFoundException;
	void delete(String cpf) throws SQLException, ClassNotFoundException;

	Cliente selectOne(String cpf) throws SQLException, ClassNotFoundException;
	List<Cliente>selectAll() throws SQLException, ClassNotFoundException;
} 

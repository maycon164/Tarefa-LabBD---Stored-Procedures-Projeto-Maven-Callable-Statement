package persistence;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import model.Cliente;

public class ClienteDao implements IClienteDao {

	private GenericDao gDao;

	public ClienteDao(GenericDao gDao) {
		this.gDao = gDao;
	}

	@Override
	public void insert(Cliente cliente) throws SQLException, ClassNotFoundException {
		Connection c = gDao.getConnection();
		
		String sql = "{CALL sp_insert_cliente(?, ?, ?, ?, ?)}";
		CallableStatement cs = c.prepareCall(sql);
		
		cs.setString(1, cliente.getCpf());
		cs.setString(2, cliente.getNome()); 
		cs.setString(3, cliente.getEmail());
		cs.setDouble(4, cliente.getLimiteCredito());
		cs.setDate(5, new Date(cliente.getDataNascimento().getTime()));
		cs.execute();
	
		cs.close();
		c.close();
	}

	@Override
	public void update(Cliente cliente, int id) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(int id) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Cliente selectOne(int id) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Cliente> selectAll() throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}
}

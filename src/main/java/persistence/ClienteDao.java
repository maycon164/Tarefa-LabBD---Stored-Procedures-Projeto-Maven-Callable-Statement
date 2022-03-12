package persistence;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
	public void update(Cliente cliente) throws SQLException, ClassNotFoundException {
		Connection c = gDao.getConnection();
		String sql = "{CALL sp_update_cliente(?, ?, ?, ?, ?)}";
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
	public void delete(String cpf) throws SQLException, ClassNotFoundException {

		Connection c = gDao.getConnection();
		String sql = "{CALL sp_delete_cliente(?)}";
		CallableStatement cs = c.prepareCall(sql);

		cs.setString(1, cpf);
		cs.execute();

		cs.close();
		c.close();

	}

	@Override
	public Cliente selectOne(String cpf) throws SQLException, ClassNotFoundException {
		ResultSet rs = null;

		Connection c = gDao.getConnection();
		String sql = "{CALL sp_select_one(?)}";
		CallableStatement cs = c.prepareCall(sql);

		cs.setString(1, cpf);
		rs = cs.executeQuery();
		Cliente cliente = null;
		
		if (rs.next()) {
			cliente = instantiateCliente(rs);
		}

		cs.close();
		c.close();

		return cliente;
	}

	@Override
	public List<Cliente> selectAll() throws SQLException, ClassNotFoundException {
		ResultSet rs = null;
		List<Cliente> clientes = new ArrayList<>();

		Connection c = gDao.getConnection();
		String sql = "{CALL sp_select_all}";
		CallableStatement cs = c.prepareCall(sql);
		rs = cs.executeQuery();

		while (rs.next()) {
			Cliente cliente = instantiateCliente(rs);
			clientes.add(cliente);
		}

		cs.close();
		c.close();

		return clientes;
	}

	private Cliente instantiateCliente(ResultSet rs) throws SQLException {
		Cliente cliente = new Cliente();

		cliente.setCpf(rs.getString("cpf"));
		cliente.setNome(rs.getString("nome"));
		cliente.setEmail(rs.getString("email"));
		cliente.setLimiteCredito(rs.getDouble("limite_credito"));
		cliente.setDataNascimento(rs.getDate("dt_nascimento"));

		return cliente;
	}
}

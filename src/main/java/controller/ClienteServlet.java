package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Cliente;
import persistence.ClienteDao;
import persistence.GenericDao;

@WebServlet("/cliente")
public class ClienteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ClienteDao cd = new ClienteDao(new GenericDao());

	public ClienteServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		System.out.println(action);
		if (action.equalsIgnoreCase("listartodos")) {
			List<Cliente> clientes;

			try {
				clientes = cd.selectAll();

				request.setAttribute("clientes", clientes);

			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
		} else if (action.equalsIgnoreCase("pesquisa")) {
			System.out.println("Olá Mundo!!!!");
			try {
				String cpf = request.getParameter("cpf");
				Cliente cliente = cd.selectOne(cpf);
				request.setAttribute("cliente", cliente);

			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
		} else if (action.equalsIgnoreCase("delete")) {
			String cpf = request.getParameter("cpf");

			try {
				cd.delete(cpf.trim());
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else if (action.equalsIgnoreCase("edit")) {

			String cpf = request.getParameter("cpf");
			Cliente clienteUpdate;
			try {
				clienteUpdate = cd.selectOne(cpf.trim());
				request.setAttribute("clienteUpdate", clienteUpdate);
				RequestDispatcher rd = request.getRequestDispatcher("clienteUpdate.jsp");
				rd.forward(request, response);
				return;
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}

		RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("entrou no servlet");

		String cpf = request.getParameter("cpf");
		String nome = request.getParameter("nome");
		String email = request.getParameter("email");
		String limite = request.getParameter("limite");
		String nascimento = request.getParameter("nascimento");
		System.out.println(cpf);
		try {

			Cliente cliente = new Cliente();

			cliente.setCpf(cpf);
			cliente.setNome(nome);
			cliente.setEmail(email);
			cliente.setLimiteCredito(Double.parseDouble(limite));
			cliente.setDataNascimento(nascimento);

			if (cd.selectOne(cpf) != null) {
				cd.update(cliente);
			} else {
				cd.insert(cliente);
			}
			
			RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
			request.setAttribute("cliente", cliente);
			rd.forward(request, response);

		} catch (ParseException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

	}

}

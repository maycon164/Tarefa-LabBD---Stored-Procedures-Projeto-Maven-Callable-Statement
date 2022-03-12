package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Cliente;
import persistence.ClienteDao;
import persistence.GenericDao;

@WebServlet("/clienteInsert")
public class ClienteInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ClienteDao cd = new ClienteDao(new GenericDao());

	public ClienteInsertServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("Entrou no Servlet DE INSERT");

		String cpf = request.getParameter("cpf");
		String nome = request.getParameter("nome");
		String email = request.getParameter("email");
		String limite = request.getParameter("limite");
		String nascimento = request.getParameter("nascimento");

		try {
			Cliente cliente = new Cliente();

			cliente.setCpf(cpf);
			cliente.setNome(nome);
			cliente.setEmail(email);
			cliente.setLimiteCredito(Double.parseDouble(limite));
			cliente.setDataNascimento(nascimento);

			cd.insert(cliente);

			RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
			request.setAttribute("cliente", cliente);
			rd.forward(request, response);

		} catch (ParseException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

	}

}

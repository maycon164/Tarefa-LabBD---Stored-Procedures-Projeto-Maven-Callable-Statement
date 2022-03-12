package controller;

import java.io.IOException;
import java.sql.SQLException;
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

@WebServlet("/clientes")
public class ClienteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ClienteDao cd = new ClienteDao(new GenericDao());

	public ClienteServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("entrou no servlet");

		List<Cliente> clientes;

		try {
			clientes = cd.selectAll();

			RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
			request.setAttribute("clientes", clientes);
			rd.forward(request, response);

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

}

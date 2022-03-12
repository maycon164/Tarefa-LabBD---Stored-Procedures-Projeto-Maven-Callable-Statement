package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Cliente;
import persistence.ClienteDao;
import persistence.GenericDao;


@WebServlet("/clientePesquisa")
public class ClientePesquisaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ClienteDao cd = new ClienteDao(new GenericDao());


    public ClientePesquisaServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Entrou no Servlet DE PESQUISA");

		String cpf = request.getParameter("pesquisa");
		Cliente cliente;

		try {
			cliente = cd.selectOne(cpf);
			RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
			request.setAttribute("cliente", cliente);
			rd.forward(request, response);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}

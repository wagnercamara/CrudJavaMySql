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

import classe.Negocio;
import model.Produto;

/**
 * Servlet implementation class Page
 */
@WebServlet(description = "Redirecionameto de pagina", urlPatterns = { "/Page" })
public class Page extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static String INSERT_OR_EDIT = "/cadastro.jsp";
	private static String LIST_PRODUTO = "/home.jsp";

	/**
	 * @throws SQLException
	 * @see HttpServlet#HttpServlet()
	 */
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {

		String forward = "";

		Negocio ng = new Negocio();

		try {

			String action = request.getParameter("action");

			if (action.equalsIgnoreCase("insert")) {
				forward = INSERT_OR_EDIT;
			} else if (action.equalsIgnoreCase("delete")) {
								
				int idProduto = Integer.parseInt(request.getParameter("id"));
				
				System.out.println("Deletar Produtos --> " + idProduto);

				ng.DeleteProduto(idProduto);
				
				forward = LIST_PRODUTO;
				
				request.setAttribute("produtos", ng.SelectProdutoAll());
				
			} else if (action.equalsIgnoreCase("update")) {
				int idProduto = Integer.parseInt(request.getParameter("id"));
				ng.DeleteProduto(idProduto);
				forward = LIST_PRODUTO;
				request.setAttribute(forward, ng.SelectProdutoAll());
			} else {
				System.out.println("Listar Produtos");
				forward = LIST_PRODUTO;	
				request.setAttribute("produtos", ng.SelectProdutoAll());
			}

	        RequestDispatcher view = request.getRequestDispatcher(forward);
	        view.forward(request, response);

		} catch (Exception e) {
			System.out.println("Erro: controller.processRequest() --> " + e.getMessage());
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			this.processRequest(request, response);
		} catch (ServletException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		// response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			this.processRequest(request, response);
		} catch (ServletException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		// doGet(request, response);
	}

}

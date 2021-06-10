package br.wagner.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.wagner.classe.Negocio;

/**
 * Servlet implementation class Page
 */
@WebServlet(description = "Redirecionameto de pagina", urlPatterns = { "/Page" })
public class Page extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static String INSERT_OR_EDIT = "/cadastro.jsp";
	private static String LIST_PRODUTO = "/home.jsp";
	private static String ERROR = "/error.jsp";

	/**
	 * @throws SQLException
	 * @see HttpServlet#HttpServlet()
	 */
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {

		String forward = "";
		int idProduto = 0;

		Negocio ng = new Negocio();

		try {

			String action = request.getParameter("action");

			if (action.equalsIgnoreCase("insert")) {

				try {
					System.out.println("insert");

					String nome = request.getParameter("nome").toUpperCase();

					double valor = Double.parseDouble(request.getParameter("valor"));

					int qtd = Integer.parseInt(request.getParameter("qtd"));

					int desconto = Integer.parseInt(request.getParameter("desconto"));


					String sId = request.getParameter("idProduto");

					if (sId == null || sId.isEmpty()) {
						sId = "ins";
					}

					System.out.println("nome ="+nome+" valor ="+valor+" qtd ="+qtd+" desconto =" +desconto);
					
					if (sId.equalsIgnoreCase("ins")) {

						System.out.println("insert new Produtos ");

						boolean check = ng.InsertProduto(nome, valor, qtd, desconto);

						System.out.println("Check Insert --> " + check);
						
						if (check == false) {

							forward = INSERT_OR_EDIT;

							request.setAttribute("check", "repetido");

						} else {

							forward = LIST_PRODUTO;

							request.setAttribute("produtos", ng.SelectProdutoAll());

						}
						
					} else {
						
						idProduto = Integer.parseInt(sId);

						System.out.println("Update Produtos ");

						ng.UpdateProduto(idProduto, nome, valor, qtd, desconto);

						forward = LIST_PRODUTO;

						request.setAttribute("produtos", ng.SelectProdutoAll());

					}

				} catch (Exception e) {

					forward = INSERT_OR_EDIT;

					request.setAttribute("check", "error");
				}

			} else if (action.equalsIgnoreCase("update")) {

				idProduto = Integer.parseInt(request.getParameter("id"));

				System.out.println("update Produtos --> " + idProduto);

				forward = INSERT_OR_EDIT;

				request.setAttribute("Update", ng.Select(idProduto));

			} else if (action.equalsIgnoreCase("edit")) {

				idProduto = Integer.parseInt(request.getParameter("id"));

				System.out.println("Editar Produtos --> " + idProduto);

				forward = INSERT_OR_EDIT;

				request.setAttribute("produto", ng.Select(idProduto));

			} else if (action.equalsIgnoreCase("delete")) {

				idProduto = Integer.parseInt(request.getParameter("id"));

				System.out.println("Deletar Produtos --> " + idProduto);

				ng.DeleteProduto(idProduto);

				forward = LIST_PRODUTO;

				request.setAttribute("produtos", ng.SelectProdutoAll());

			} else if (action.equalsIgnoreCase("home")) {
				System.out.println("Listar Produtos");
				forward = LIST_PRODUTO;
				request.setAttribute("produtos", ng.SelectProdutoAll());

			} else {
				forward = INSERT_OR_EDIT;
			}

			ng.CloseProduto();

			RequestDispatcher view = request.getRequestDispatcher(forward);
			view.forward(request, response);

		} catch (Exception e) {

			ng.CloseProduto();
			System.out.println("Erro: controller.processRequest() --> " + e.getMessage());
			forward = ERROR;
			RequestDispatcher view = request.getRequestDispatcher(forward);
			view.forward(request, response);
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

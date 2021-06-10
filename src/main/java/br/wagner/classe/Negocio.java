package br.wagner.classe;

import java.sql.SQLException;
import java.util.List;

import br.wagner.dao.ProdutoDao;
import br.wagner.model.Produto;

public class Negocio {

	ProdutoDao pro = new ProdutoDao();

	public List<Produto> SelectProdutoAll() throws SQLException {

		List<Produto> produtos = null;

		try {

			produtos = pro.SelectAll();

		} catch (Exception e) {
			System.out.println("Erro: Classe.Negocio.SelectProdutoAll() --> " + e.getMessage());
		}

		return produtos;

	}

	public boolean InsertProduto(String name, double valor, int qtd, int desconto) throws SQLException {

		boolean check = pro.CheckProduct(name);

		System.out.println("InsertProduto --> " + check);
		
		if (check == true) {
			
			Produto produto = new Produto();

			produto.setNome(name);
			produto.setValor(valor);
			produto.setQtd(qtd);
			produto.setDesconto(desconto);

			return pro.Insert(produto);
			

		} else {
			System.out.println("Erro: Classe.Negocio.InsertProduto() --> Nome Repetido");
			return false;
		}

	}

	public void UpdateProduto(int id, String name, double valor, int qtd, int desconto) throws SQLException {

		Produto produto = new Produto();

		produto.setId(id);
		produto.setNome(name);
		produto.setValor(valor);
		produto.setQtd(qtd);
		produto.setDesconto(desconto);

		pro.Update(produto);

	}

	public Produto Select(int id) throws SQLException {
		return pro.Select(id);
	}

	public void DeleteProduto(int id) throws SQLException {

		pro.Remove(id);
	}
	
	public void CloseProduto() throws SQLException {
		pro.closeConnection();
	}

}

package classe;

import java.sql.SQLException;
import java.util.List;

import dao.ProdutoDao;
import model.Produto;

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

	public void InsertProduto(String name, double valor, int qtd, int desconto) throws SQLException {

		boolean chack = pro.CheckProduct(name);

		if (chack) {

			Produto produto = new Produto();

			produto.setNome(name);
			produto.setValor(valor);
			produto.setQtd(qtd);
			produto.setDesconto(desconto);

			pro.Insert(produto);

		} else {
			System.out.println("Erro: Classe.Negocio.InsertProduto() --> Nome Repetido");
		}

	}

	public void UpdateProduto(int id, String name, double valor, int qtd, int desconto) throws SQLException {

		boolean chack = pro.CheckProduct(name);

		if (chack) {

			Produto produto = new Produto();

			produto.setId(id);
			produto.setNome(name);
			produto.setValor(valor);
			produto.setQtd(qtd);
			produto.setDesconto(desconto);

			pro.Update(produto);

		} else {
			System.out.println("Erro: Classe.Negocio.UpdateProduto() --> Nome Repetido");
		}

	}

	public void DeleteProduto(int id) throws SQLException {

		pro.Remove(id);
	}

}

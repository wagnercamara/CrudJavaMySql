package classe;

import java.sql.SQLException;
import java.util.List;
import java.sql.Connection;

import dao.ProdutoDao;
import model.Produto;
import util.ConexaoMySQL;

public class teste {

	public static void main(String[] args) throws SQLException {
				
		ProdutoDao pro = new ProdutoDao();
		
		List<Produto> produtos = pro.SelectAll();
		
		for (Produto produto : produtos) {
			System.out.println(produto.getNome());
		}
		
	}

}

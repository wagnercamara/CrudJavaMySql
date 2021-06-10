package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Produto;
import util.ConexaoMySQL;

public class ProdutoDao {

	private Connection connection;
	
	public ProdutoDao() {
			connection = ConexaoMySQL.getConnection();
	}
	
	public boolean CheckProduct(String nome) throws SQLException {

		boolean retorno = true;

		try {
			PreparedStatement ps = connection
					.prepareStatement("SELECT idProduto FROM produto.produtos WHERE nProduto=?;");
			ps.setString(1, nome);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				retorno = false;
			}
			connection.close();

		} catch (Exception ex) {

			connection.close();

			System.out.println("Error: dao.CheckProduct() -->" + ex.getMessage());
		}

		return retorno;

	}

	public Produto Select(int id) throws SQLException {

		Produto produto = new Produto();

		try {
			PreparedStatement ps = connection
					.prepareStatement("SELECT idProduto FROM produto.produtos WHERE idProduto=?;");
			ps.setInt(1, id);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {

				produto.setId(rs.getInt("idProduto"));
				produto.setNome(rs.getString("nProduto"));
				produto.setQtd(rs.getInt("qtd"));
				produto.setDesconto(rs.getInt("desconto"));
				produto.setValor(rs.getDouble("valor"));
			}

		} catch (Exception ex) {

			

			System.out.println("Error: dao.Select() -->" + ex.getMessage());
		}
		
		connection.close();
		return produto;

	}

	
	public List<Produto> SelectAll() throws SQLException {

		System.out.println("log: SelectAll");

		List<Produto> produtos = new ArrayList<Produto>();

		try {
			Statement stmt  = connection.createStatement();

			ResultSet rs = stmt.executeQuery("SELECT idProduto, nProduto, valor, qtd, desconto FROM produtos;");

			while (rs.next()) {
				Produto produto = new Produto();

				produto.setId(rs.getInt("idProduto"));
				produto.setNome(rs.getString("nProduto"));
				produto.setQtd(rs.getInt("qtd"));
				produto.setDesconto(rs.getInt("desconto"));
				produto.setValor(rs.getDouble("valor"));

				produtos.add(produto);
				
			}
			connection.close();

		} catch (Exception ex) {
			connection.close();
			System.out.println("Error: dao.Function.SelectAll()  -->" + ex.getMessage());
		}

		return produtos;

	}

	public void Insert(Produto produto) throws SQLException {

		try {
			PreparedStatement preparedStatement = connection.prepareStatement(
					"INSERT INTO produto.produtos (nProduto, valor, qtd, desconto) VALUES(?, ?, ?, ?);");

			preparedStatement.setString(1, produto.getNome());
			preparedStatement.setDouble(2, produto.getValor());
			preparedStatement.setInt(3, produto.getQtd());
			preparedStatement.setInt(4, produto.getDesconto());

			preparedStatement.executeUpdate();

			connection.close();

		} catch (SQLException e) {

			connection.close();
			e.printStackTrace();
		}

	}

	public void Update(Produto produto) throws SQLException {

		try {
			PreparedStatement preparedStatement = connection.prepareStatement(
					"UPDATE produto.produtos SET valor=?, qtd=?, desconto=? WHERE idProduto=? AND nProduto=?;");

			preparedStatement.setDouble(1, produto.getValor());
			preparedStatement.setInt(2, produto.getQtd());
			preparedStatement.setInt(3, produto.getDesconto());

			preparedStatement.setInt(4, produto.getId());
			preparedStatement.setString(5, produto.getNome());

			preparedStatement.executeUpdate();

			connection.close();

		} catch (SQLException e) {
			connection.close();
			e.printStackTrace();
		}
	}

	public void Remove(int id) throws SQLException {
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement("delete from produto.produtos where idProduto=?");
			preparedStatement.setInt(1, id);
			preparedStatement.executeUpdate();


		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}

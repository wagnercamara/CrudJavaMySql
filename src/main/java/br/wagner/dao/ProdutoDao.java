package br.wagner.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.wagner.model.Produto;
import br.wagner.util.ConexaoMySQL;

public class ProdutoDao {

	private Connection connection;

	public ProdutoDao() {
		connection = ConexaoMySQL.getConnection();
	}

	public boolean CheckProduct(String nome) throws SQLException {

		boolean retorno = true;

		try {
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM produtos WHERE nProduto=?;");
			ps.setString(1, nome);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {

				if (nome.equalsIgnoreCase(rs.getString("nProduto"))) {	
					retorno = false;
				}				
			}

		} catch (Exception ex) {

			System.out.println("Error: dao.CheckProduct() -->" + ex.getMessage());
			
			retorno = true;
		}

		return retorno;

	}

	public Produto Select(int id) throws SQLException {

		Produto produto = new Produto();

		try {
			PreparedStatement ps = connection.prepareStatement(
					"SELECT idProduto, nProduto, valor, qtd, desconto FROM produtos WHERE idProduto=?;");
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

		return produto;

	}

	public List<Produto> SelectAll() throws SQLException {

		System.out.println("log: SelectAll");

		List<Produto> produtos = new ArrayList<Produto>();

		try {
			Statement stmt = connection.createStatement();

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

		} catch (Exception ex) {

			System.out.println("Error: dao.Function.SelectAll()  -->" + ex.getMessage());
		}

		return produtos;

	}

	public boolean Insert(Produto produto) throws SQLException {

		try {

			PreparedStatement preparedStatement = connection
					.prepareStatement("INSERT INTO produtos (nProduto, valor, qtd, desconto) VALUES(?, ?, ?, ?);");

			preparedStatement.setString(1, produto.getNome());
			preparedStatement.setDouble(2, produto.getValor());
			preparedStatement.setInt(3, produto.getQtd());
			preparedStatement.setInt(4, produto.getDesconto());

			preparedStatement.executeUpdate();

			return true;

		} catch (SQLException e) {

			e.printStackTrace();
			return false;
		}

	}

	public void Update(Produto produto) throws SQLException {

		try {
			PreparedStatement preparedStatement = connection.prepareStatement(
					"UPDATE produtos SET valor=?, qtd=?, desconto=? WHERE idProduto=? AND nProduto=?;");

			preparedStatement.setDouble(1, produto.getValor());
			preparedStatement.setInt(2, produto.getQtd());
			preparedStatement.setInt(3, produto.getDesconto());

			preparedStatement.setInt(4, produto.getId());
			preparedStatement.setString(5, produto.getNome());

			preparedStatement.executeUpdate();

		} catch (SQLException e) {

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

	public void closeConnection() throws SQLException {
		connection.close();
	}

}

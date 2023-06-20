package br.com.vita.projeto.produto.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.com.vita.projeto.produto.model.ProdutoModel;

@Repository
public class ProdutoRepository {

	@Autowired
	ProdutoFactory connFactory;

	 public Boolean existeNome(String nome) {
	        Connection connection = connFactory.recuperarConexao();
	        String sql = "SELECT EXISTS(SELECT 1 FROM produtos WHERE nome = ?);";
	        Boolean exists = null;
	        try {
	            PreparedStatement ps = connection.prepareStatement(sql);
	            ps.setString(1, nome);
	            ResultSet result = ps.executeQuery();
	            result.next();
	            exists = result.getBoolean(1);
	            result.close();
	            ps.close();
	            connection.close();
	        } catch (SQLException e) {
	            throw new RuntimeException(e);
	        }
	        return exists;
	    }

	public void cadastrar(ProdutoModel model) {
		String sql = "INSERT INTO produtos (nome, descricao, preco, quantidadeEstoque) VALUES (?,?,?,?);";
		Connection conn = connFactory.recuperarConexao();
		
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, model.getNome());
			ps.setString(2, model.getDescricao());
			ps.setFloat(3, model.getPreco());
			ps.setInt(4, model.getQuantidadeEstoque());
			
			ps.execute();
			ps.close();
			conn.close();
			
		} catch (SQLException e) {
			e.getMessage();
		}
	}

	public ProdutoModel listarID(Integer id) {
		String sql = "SELECT * FROM produtos WHERE id = ?";
		
		ProdutoModel produto = null;
		Connection conn = connFactory.recuperarConexao();
		
		try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            
            ResultSet result = ps.executeQuery();
            result.next();
            
            Integer id1 = result.getInt(1);
            String resultNome = result.getString(2);
            String descricao = result.getString(3);
            Float preco = result.getFloat(4);
            Integer quantidadeEstoque = result.getInt(5);
            
            produto = new ProdutoModel(id1, resultNome, descricao, preco, quantidadeEstoque);
            
            ps.close();
            result.close();
            conn.close();
            
        } catch (SQLException e) {
            e.getMessage();
        }
		return produto;
	}
	
	 public boolean existeId(Integer id) {
	        Connection connection = connFactory.recuperarConexao();
	        String sql = "SELECT EXISTS(SELECT 1 FROM produtos WHERE id = ?);";
	        Boolean exists = null;
	        try {
	            PreparedStatement ps = connection.prepareStatement(sql);
	            ps.setInt(1, id);
	            ResultSet result = ps.executeQuery();
	            result.next();
	            exists = result.getBoolean(1);
	            result.close();
	            ps.close();
	            connection.close();
	        } catch (SQLException e) {
	            throw new RuntimeException(e);
	        }
	        return exists;
	    }
}

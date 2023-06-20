package br.com.vita.projeto.usuario.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mysql.cj.x.protobuf.MysqlxPrepare.Prepare;

import br.com.vita.projeto.usuario.dto.UsuarioDTO;
import br.com.vita.projeto.usuario.model.UsuarioModel;

@Repository
public class UsuarioRepository {

	@Autowired
	ConnectionFactory connFactory;

	public boolean existeCpf(String cpf) {
		String sql = "SELECT EXISTS(SELECT 1 FROM usuario WHERE cpf = ?);";
		boolean existe = false;
		Connection conn = connFactory.recuperarConexao();
		
		try {
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, cpf);
			
			ResultSet result = ps.executeQuery();
			result.next();
			
			existe = result.getBoolean(1);
			
			ps.close();
			conn.close();
			
		} catch (SQLException e) {
			e.getMessage();
		}
		
		return existe;
	}

	public void cadastrar(UsuarioModel model) {
		String sql = "INSERT INTO usuario (cpf, nome, email, telefone, endereco) VALUES (?,?,?,?,?);";
		Connection conn = connFactory.recuperarConexao();
		
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, model.getCpf());
			ps.setString(2, model.getNome());
			ps.setString(3, model.getEmail());
			ps.setString(4, model.getTelefone());
			ps.setString(5, model.getEndereco());
			
			ps.execute();
			ps.close();
			conn.close();
			
		} catch (SQLException e) {
			e.getMessage();
		}
	}

	public UsuarioModel listarCpf(String cpf) {
		String sql = "SELECT * FROM usuario WHERE cpf = ?";
		
		UsuarioModel usuario = null;
		Connection conn = connFactory.recuperarConexao();
		
		try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, cpf);
            
            ResultSet result = ps.executeQuery();
            result.next();
            
            Integer id = result.getInt(1);
            String resultCpf = result.getString(2);
            String nome = result.getString(3);
            String email = result.getString(4);
            String telefone = result.getString(5);
            String endereco = result.getString(6);
            
            usuario = new UsuarioModel(id, resultCpf, nome, email, telefone, endereco);
            
            ps.close();
            result.close();
            conn.close();
        } catch (SQLException e) {
            e.getMessage();
        }
		return usuario;
	}
}

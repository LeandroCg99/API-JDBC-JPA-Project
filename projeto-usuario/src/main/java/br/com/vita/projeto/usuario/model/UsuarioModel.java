package br.com.vita.projeto.usuario.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioModel {
	
	private Integer id;
	
	private String cpf;
	private String nome;
	private String email;
	private String telefone;
	private String endereco;
	
	
}

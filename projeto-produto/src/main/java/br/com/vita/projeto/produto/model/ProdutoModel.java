package br.com.vita.projeto.produto.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoModel {
	
	private Integer id;
	
	private String nome;
	private String descricao;
	private Float preco;
	private Integer quantidadeEstoque;
	
	
}

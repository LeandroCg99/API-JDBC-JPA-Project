package br.com.vita.projeto.produto.dto;

import lombok.Data;

@Data
public class ProdutoDTO {

	private String nome;
	private String descricao;
	private Float preco;
	private Integer quantidadeEstoque;

}

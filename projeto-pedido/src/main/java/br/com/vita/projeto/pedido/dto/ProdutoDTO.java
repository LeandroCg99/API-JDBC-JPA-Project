package br.com.vita.projeto.pedido.dto;

import lombok.Data;

@Data
public class ProdutoDTO {

	
	private Integer id;
	private String nome;
	private String descricao;
	private Float preco;
	private Integer quantidadeEstoque;
	
}

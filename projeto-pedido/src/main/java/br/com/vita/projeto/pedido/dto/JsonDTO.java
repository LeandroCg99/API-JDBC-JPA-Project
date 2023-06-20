package br.com.vita.projeto.pedido.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JsonDTO {

	private String cpfUsuario;
	private Integer idProduto;
	private Integer quantidade;
	private String formaPagamento;
	private String cepEntrega;

}

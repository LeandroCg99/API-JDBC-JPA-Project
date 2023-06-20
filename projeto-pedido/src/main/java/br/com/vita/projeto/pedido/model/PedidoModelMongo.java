	package br.com.vita.projeto.pedido.model;

import java.time.LocalDateTime;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

import org.springframework.data.mongodb.core.mapping.Document;

import br.com.vita.projeto.pedido.enums.PedidoStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "pedidos")
public class PedidoModelMongo {

	@Id
	private String idPedido;
	private Integer idUsuario;
	private Integer idProduto;
	
	private String cepDeEntrega;
	private LocalDateTime dataHoraPedido = LocalDateTime.now();
	private LocalDateTime dataHoraConclusao;
	
	@Enumerated(EnumType.STRING)
	private PedidoStatus status = PedidoStatus.ANDAMENTO;
	
	private Float valorPedido;
	
	private Integer idSQL;

	public PedidoModelMongo(Integer idUsuario, Integer idProduto, String cepDeEntrega, Float valorPedido) {
		
		this.idUsuario = idUsuario;
		this.idProduto = idProduto;
		this.cepDeEntrega = cepDeEntrega;
		this.valorPedido = valorPedido;
	}
	
	
}


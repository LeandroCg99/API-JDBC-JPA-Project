package br.com.vita.projeto.pedido.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.vita.projeto.pedido.enums.PedidoStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Table(name = "pedidos")
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class PedidoModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idPedido;
	private Integer idUsuario;
	private Integer idProduto;
	
	private String cepDeEntrega;
	private LocalDateTime dataHoraPedido = LocalDateTime.now();
	private LocalDateTime dataHoraConclusao;
	
	@Enumerated(EnumType.STRING)
	private PedidoStatus status = PedidoStatus.ANDAMENTO;
	
	private Float valorPedido;

	public PedidoModel(Integer idUsuario, Integer idProduto, String cepDeEntrega, Float valorPedido) {
		
		this.idUsuario = idUsuario;
		this.idProduto = idProduto;
		this.cepDeEntrega = cepDeEntrega;
		this.valorPedido = valorPedido;
	}
	
	
}


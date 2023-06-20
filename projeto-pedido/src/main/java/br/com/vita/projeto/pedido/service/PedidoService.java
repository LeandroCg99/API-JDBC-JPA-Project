package br.com.vita.projeto.pedido.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import br.com.vita.projeto.pedido.dto.ClienteDTO;
import br.com.vita.projeto.pedido.dto.JsonDTO;
import br.com.vita.projeto.pedido.dto.ProdutoDTO;
import br.com.vita.projeto.pedido.enums.PedidoStatus;
import br.com.vita.projeto.pedido.model.PedidoModel;
import br.com.vita.projeto.pedido.model.PedidoModelMongo;
import br.com.vita.projeto.pedido.repository.PedidoRepository;
import br.com.vita.projeto.pedido.repository.PedidoRepositoryMongo;

@Service
public class PedidoService {

	@Autowired
	ClienteRest clienteRest;

	@Autowired
	ProdutoRest produtoRest;

	@Autowired
	PedidoRepository pedidoRepository;
	
	@Autowired
	PedidoRepositoryMongo pedidoRepositoryMongo;
		
	public ResponseEntity<String> realizaPedido(JsonDTO jsonDTO) {

		ClienteDTO clienteDTO = null;
		try {
			clienteDTO = clienteRest.buscaCliente(jsonDTO.getCpfUsuario());
		} catch (HttpClientErrorException e) {
			return ResponseEntity.status(404).body("CPF informado não localizado");
		}

		ProdutoDTO produtoDTO = null;
		try {
			produtoDTO = produtoRest.buscaProduto(jsonDTO.getIdProduto());
		} catch (HttpClientErrorException e) {
			return ResponseEntity.status(404).body("ID de produto informado não localizado");
		}

		if (jsonDTO.getQuantidade() > produtoDTO.getQuantidadeEstoque()) {
			return ResponseEntity.status(409).body("Quantidade solicitada excede a quantidade disponivel em estoque");
		}

		PedidoModel pedidoModel = new PedidoModel(jsonDTO.getIdProduto(), clienteDTO.getId(), jsonDTO.getCepEntrega(),
				jsonDTO.getQuantidade() * produtoDTO.getPreco());
		
		PedidoModelMongo pedidoModelMongo = new PedidoModelMongo(jsonDTO.getIdProduto(), clienteDTO.getId(), jsonDTO.getCepEntrega(),
				jsonDTO.getQuantidade() * produtoDTO.getPreco());

		Integer idSQL = pedidoRepository.save(pedidoModel).getIdPedido();
		pedidoModelMongo.setIdSQL(idSQL);
		pedidoRepositoryMongo.save(pedidoModelMongo);	

		return ResponseEntity.status(200).body("Pedido realizado com sucesso");
	}

	public ResponseEntity<?> buscaPedido(Integer id) {
		
		if (!pedidoRepository.existsById(id)) {
			return ResponseEntity.status(404).body("ID do pedido não encontrado");		
		}
			
		return ResponseEntity.status(200).body(pedidoRepository.findById(id));
	}

	public ResponseEntity<?> atualizarPedido(Integer id) {
		
		if (!pedidoRepository.existsById(id)) {
			return ResponseEntity.status(404).body("ID do pedido não encontrado");		
		} 
		
		PedidoModel pedidoModel = pedidoRepository.findById(id).get();
		pedidoModel.setStatus(PedidoStatus.PROCESSADO);
		pedidoRepository.save(pedidoModel);
	
		return ResponseEntity.status(200).body("Pedido {"+id+"} atualizado com sucesso!");
	}
}

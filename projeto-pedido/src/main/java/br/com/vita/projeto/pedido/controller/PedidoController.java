package br.com.vita.projeto.pedido.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.vita.projeto.pedido.dto.JsonDTO;
import br.com.vita.projeto.pedido.service.PedidoService;

@RestController
public class PedidoController {

	@Autowired
	PedidoService service;
	
	@PostMapping("/realiza-pedido")
	public ResponseEntity<String> realizaPedido(@RequestBody JsonDTO jsonDTO){
		return service.realizaPedido(jsonDTO);
	}
	
	@GetMapping("/busca-pedido/{id}")
	public ResponseEntity<?> buscaPedido(@PathVariable Integer id){
		return service.buscaPedido(id);
	}
	
    @PutMapping("/atualizar/pedido/{id}")
    public ResponseEntity<?> atualizarPedido(@PathVariable Integer id){
        return service.atualizarPedido(id);
    }
}

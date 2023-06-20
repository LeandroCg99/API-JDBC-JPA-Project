package br.com.vita.projeto.produto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.vita.projeto.produto.dto.ProdutoDTO;
import br.com.vita.projeto.produto.service.ProdutoService;

@RestController
public class ProdutoController {

	@Autowired
	ProdutoService service;
	
	@PostMapping("/cadastrar-produto")
	public ResponseEntity<String> cadastrar(@RequestBody ProdutoDTO produto) {
		return service.cadastrar(produto);
	}
	
	@GetMapping("/consultar/{id}")
	public ResponseEntity<?> consultarNome(@PathVariable Integer id) {
		return service.listarID(id);
	}
}

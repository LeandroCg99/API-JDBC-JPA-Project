package br.com.vita.projeto.base.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.vita.projeto.base.dto.UsuarioDTO;
import br.com.vita.projeto.base.service.UsuarioService;

@RestController
public class UsuarioController {

	@Autowired
	UsuarioService service;
	
	@PostMapping("/cadastrar-usuario")
	public ResponseEntity<String> cadastrar(@RequestBody UsuarioDTO usuario) {
		return service.cadastrar(usuario);
	}
	
	@GetMapping("/consultar/{cpf}")
	public ResponseEntity<?> consultarCpf(@PathVariable String cpf) {
		return service.listarCpf(cpf);
	}
}

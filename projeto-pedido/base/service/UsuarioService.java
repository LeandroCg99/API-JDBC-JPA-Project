package br.com.vita.projeto.base.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.vita.projeto.base.dto.UsuarioDTO;
import br.com.vita.projeto.base.model.UsuarioModel;
import br.com.vita.projeto.base.repository.ConnectionFactory;
import br.com.vita.projeto.base.repository.UsuarioRepository;

@Service
public class UsuarioService {

	ConnectionFactory connectionFactory;
	
	@Autowired
	UsuarioRepository repository;

	public ResponseEntity<String> cadastrar(UsuarioDTO usuario) {
		if(repository.existeCpf(usuario.getCpf())){
			return ResponseEntity.status(409).body("CPF já cadastrado na nossa base de dados");
		}
		
		UsuarioModel model = new UsuarioModel();
		BeanUtils.copyProperties(usuario, model);
		repository.cadastrar(model);
		
		return ResponseEntity.status(200).body("Usuario cadastrado com sucesso");
	}

	public ResponseEntity<?> listarCpf(String cpf) {
		if(!repository.existeCpf(cpf)) {
			return ResponseEntity.status(409).body("CPF nao cadastrado na base de dados");
		}
		return ResponseEntity.status(200).body(repository.listarCpf(cpf));
	}

	
}

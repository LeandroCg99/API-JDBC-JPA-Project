package br.com.vita.projeto.produto.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.vita.projeto.produto.dto.ProdutoDTO;
import br.com.vita.projeto.produto.model.ProdutoModel;
import br.com.vita.projeto.produto.repository.ProdutoFactory;
import br.com.vita.projeto.produto.repository.ProdutoRepository;

@Service
public class ProdutoService {

	ProdutoFactory connectionFactory;
	
	@Autowired
	ProdutoRepository repository;

	public ResponseEntity<String> cadastrar(ProdutoDTO produto) {
		if(repository.existeNome(produto.getNome())){
			return ResponseEntity.status(409).body("ID do produto já cadastrado na nossa base de dados");
		}
		
		ProdutoModel model = new ProdutoModel();
		BeanUtils.copyProperties(produto, model);
		repository.cadastrar(model);
		
		return ResponseEntity.status(200).body("ID do produto cadastrado com sucesso");
	}

	public ResponseEntity<?> listarID(Integer id) {
		if(!repository.existeId(id)) {
			return ResponseEntity.status(409).body("ID do produto nao cadastrado na base de dados");
		}
		return ResponseEntity.status(200).body(repository.listarID(id));
	}

	
}

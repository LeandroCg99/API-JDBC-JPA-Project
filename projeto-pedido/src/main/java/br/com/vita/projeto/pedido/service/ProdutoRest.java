package br.com.vita.projeto.pedido.service;

import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import br.com.vita.projeto.pedido.dto.ProdutoDTO;

@Component
public class ProdutoRest {
	
	public ProdutoDTO buscaProduto(Integer id) {
		
		RestTemplate restTemplate = new RestTemplate();
		String url = "http://localhost:8081/consultar/" + id;
		ResponseEntity<ProdutoDTO> response = restTemplate.exchange(url, HttpMethod.GET, null, ProdutoDTO.class);
		
		return response.getBody();

	}
}

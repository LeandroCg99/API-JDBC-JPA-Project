package br.com.vita.projeto.pedido.service;

import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import br.com.vita.projeto.pedido.dto.ClienteDTO;

@Component
public class ClienteRest {

	public ClienteDTO buscaCliente(String cpf) {
		
		RestTemplate restTemplate = new RestTemplate();
		String url = "http://localhost:8080/consultar/" + cpf;
		ResponseEntity<ClienteDTO> response = restTemplate.exchange(url, HttpMethod.GET, null, ClienteDTO.class);
		
		System.out.println(response);
		
		return response.getBody();
	}
}

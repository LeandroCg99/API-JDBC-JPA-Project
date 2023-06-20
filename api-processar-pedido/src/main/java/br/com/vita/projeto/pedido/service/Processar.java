package br.com.vita.projeto.pedido.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.vita.projeto.pedido.enums.PedidoStatus;
import br.com.vita.projeto.pedido.model.Notificacao;
import br.com.vita.projeto.pedido.model.PedidoModelMongo;
import br.com.vita.projeto.pedido.repository.NotificacaoRepositoryMongo;
import br.com.vita.projeto.pedido.repository.PedidoRepositoryMongo;

@Service
public class Processar {

	@Autowired
	MongoTemplate mongoTemplate;

	@Autowired
	PedidoRepositoryMongo pedidoRepositoryMongo;

	@Autowired
	NotificacaoRepositoryMongo notificacaoRepositoryMongo;

	@Scheduled(fixedRate = 5 * 60 * 1000, initialDelay = 10000)
	public void processarPedido() {
		System.out.println("processando pedidos");
		List<PedidoModelMongo> pedidosAndamendo = pedidoRepositoryMongo.findByStatus("ANDAMENTO");

		for (PedidoModelMongo pedido : pedidosAndamendo) {

			Notificacao pedidoNotificacao = new Notificacao();
			BeanUtils.copyProperties(pedido, pedidoNotificacao);
			notificacaoRepositoryMongo.save(pedidoNotificacao);
			pedido.setStatus(PedidoStatus.PROCESSADO);
			pedidoRepositoryMongo.save(pedido);

		}
		System.out.println("pedidos processados");
	}

	@Scheduled(fixedRate = 10 * 60 * 1000, initialDelay = 40000)
	public void notificarPedido() {
		System.out.println("notificando pedido");
		RestTemplate template = new RestTemplate();

		List<Notificacao> pedidosPendente = notificacaoRepositoryMongo.findByStatus("ANDAMENTO");

		for (Notificacao pedidoPendente : pedidosPendente) {
			pedidoPendente.setStatus(PedidoStatus.NOTIFICADO);
			notificacaoRepositoryMongo.save(pedidoPendente);

			String url = "http://localhost:8083/atualizar/pedido/" + pedidoPendente.getIdSQL();
			ResponseEntity<String> response = template.exchange(url, HttpMethod.PUT, null, String.class);
			System.out.println(response.getBody());
		}
		
		System.out.println("pedidos notificados");
	}

	// @Scheduled(fixedRate = 300000, initialDelay = 10000)
	// public void processarPedido() {

	// System.out.println("Processando");
	// List<PedidoModelMongo> pendentes =
	// pedidoRepositoryMongo.findByStatus(PedidoStatus.ANDAMENTO);

	// for (PedidoModelMongo pedido : pendentes) {

	// Query query = new
	// Query(Criteria.where("pedidoStatus").is(PedidoStatus.ANDAMENTO));
	// Update update = new Update().set("pedidoStatus", PedidoStatus.PROCESSADO);

	// mongoTemplate.updateFirst(query, update, PedidoModelMongo.class);

	// Notificacao notificacaoMongoModel = new Notificacao();
	// BeanUtils.copyProperties(pedido, notificacaoMongoModel);
	// notificacaoRepositoryMongo.save(notificacaoMongoModel);
	// }
	// }

	// @Scheduled(fixedRate = 600000, initialDelay = 40000)
	// public void notificarPedido() {

	// System.out.println("notificando pedidos...");

	// RestTemplate restTemplate = new RestTemplate();

	// List<Notificacao> pedidosProcessados =
	// notificacaoRepositoryMongo.findByStatus(PedidoStatus.ANDAMENTO);
	// for (Notificacao pedido : pedidosProcessados) {

	// pedido.setStatus(PedidoStatus.ANDAMENTO);
	// notificacaoRepositoryMongo.save(pedido);

	// // Query query = new
	// Query(Criteria.where("pedidoStatus").is(PedidoStatus.ANDAMENTO));
	// // Update update = new Update().set("pedidoStatus", PedidoStatus.NOTIFICADO);

	// // mongoTemplate.updateFirst(query, update, Notificacao.class);

	// String url = "http://localhost:8083/atualizar/pedido/" + pedido.getIdSQL();
	// ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.PUT,
	// null, String.class);
	// System.out.println(response.getBody());

	// }
	// }

}

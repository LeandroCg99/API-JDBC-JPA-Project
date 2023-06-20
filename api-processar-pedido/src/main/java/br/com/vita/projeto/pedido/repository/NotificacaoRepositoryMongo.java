package br.com.vita.projeto.pedido.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.vita.projeto.pedido.enums.PedidoStatus;
import br.com.vita.projeto.pedido.model.Notificacao;

public interface NotificacaoRepositoryMongo extends MongoRepository<Notificacao, String> {

	List<Notificacao> findByStatus(String pedidoStatus);

}

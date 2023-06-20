package br.com.vita.projeto.pedido.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.vita.projeto.pedido.enums.PedidoStatus;
import br.com.vita.projeto.pedido.model.PedidoModelMongo;

public interface PedidoRepositoryMongo extends MongoRepository<PedidoModelMongo, String> {

	List<PedidoModelMongo> findByStatus(String pedidoStatus);

}

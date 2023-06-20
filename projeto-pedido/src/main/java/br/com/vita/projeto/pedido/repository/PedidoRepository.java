package br.com.vita.projeto.pedido.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import br.com.vita.projeto.pedido.model.PedidoModel;


@Repository
public interface PedidoRepository extends JpaRepository<PedidoModel, Integer>{
	

}

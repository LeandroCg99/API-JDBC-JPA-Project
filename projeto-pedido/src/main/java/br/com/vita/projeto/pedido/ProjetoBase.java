package br.com.vita.projeto.pedido;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EntityScan(basePackages = {"br.com.vita.projeto.pedido.model"} )
@EnableJpaRepositories(basePackages = {"br.com.vita.projeto.pedido.repository"})
@EnableMongoRepositories(basePackages = {"br.com.vita.projeto.pedido.repository"})
public class ProjetoBase {

	public static void main(String[] args) {
		SpringApplication.run(ProjetoBase.class, args);
	}
}

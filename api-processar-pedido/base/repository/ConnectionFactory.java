package br.com.vita.projeto.base.repository;

import java.sql.Connection;
import java.sql.SQLException;

import org.springframework.stereotype.Component;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Component
public class ConnectionFactory {

	public Connection recuperarConexao() {
        try {
            return createDataSource().getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private HikariDataSource createDataSource() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:mysql://localhost:3306/api_jdbc?serverTimezone=UTC");
        config.setUsername("root");
        config.setPassword("leandro@23");
        config.setMaximumPoolSize(10);

        return new HikariDataSource(config);
    }
}
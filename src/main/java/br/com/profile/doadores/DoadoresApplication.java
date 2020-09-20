package br.com.profile.doadores;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
@EnableJdbcRepositories
@ComponentScan(basePackages = {"br.com.profile.*"})
@SpringBootApplication
public class DoadoresApplication {

	public static void main(String[] args) {
		SpringApplication.run(DoadoresApplication.class, args);
	}

}

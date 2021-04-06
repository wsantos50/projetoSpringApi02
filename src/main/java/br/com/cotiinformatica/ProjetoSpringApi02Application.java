package br.com.cotiinformatica;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*
 * Classe de inicialização do framework Spring Boot
 * Precisa acessar todas as demais classes do projeto
 */

@SpringBootApplication(scanBasePackages = { "br.com.cotiinformatica" })
public class ProjetoSpringApi02Application {

	public static void main(String[] args) {
		SpringApplication.run(ProjetoSpringApi02Application.class, args);
	}

}
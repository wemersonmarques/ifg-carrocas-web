package br.edu.ifg.carrocasweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EntityScan(basePackages="br.edu.ifg.carrocasweb.model")
@ComponentScan(basePackages= {"br.edu.ifg.carrocasweb.*"})
public class CarrocaswebApplication {

	public static void main(String[] args) {
		SpringApplication.run(CarrocaswebApplication.class, args);
	}

}

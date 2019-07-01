package br.edu.ifg.carrocasweb;

import java.io.File;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

import br.edu.ifg.carrocasweb.functionality.FileWritter;

@SpringBootApplication
@EntityScan(basePackages="br.edu.ifg.carrocasweb.model")
@ComponentScan(basePackages= {"br.edu.ifg.carrocasweb.*"})
public class CarrocaswebApplication {

	public static void main(String[] args) {
		new File(FileWritter.uploadDirectory).mkdirs();
		SpringApplication.run(CarrocaswebApplication.class, args);
	}

}

package com.aloespirita.aloespirita;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories("com.aloespirita")
@EntityScan("com.aloespirita")
@ComponentScan("com.aloespirita")
@SpringBootApplication
public class AloespiritaApplication {

	public static void main(String[] args) {
		SpringApplication.run(AloespiritaApplication.class, args);
	}

}

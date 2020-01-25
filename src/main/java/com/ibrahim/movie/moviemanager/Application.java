package com.ibrahim.movie.moviemanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * http://zetcode.com/springboot/postgresql/
 * https://www.javaguides.net/2019/08/spring-boot-jpa-hibernate-one-to-many-example-tutorial.html
 */
@SpringBootApplication
@EnableJpaAuditing
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}

package pe.edu.coworkers.publicationservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class PublicationServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PublicationServiceApplication.class, args);
	}

}

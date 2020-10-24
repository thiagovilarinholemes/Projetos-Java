package academy.digitallab.store.registre;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class RegistreServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(RegistreServiceApplication.class, args);
	}

}

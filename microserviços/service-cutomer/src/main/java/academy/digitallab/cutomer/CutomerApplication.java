package academy.digitallab.cutomer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class CutomerApplication {

	public static void main(String[] args) {
		SpringApplication.run(CutomerApplication.class, args);
	}
}

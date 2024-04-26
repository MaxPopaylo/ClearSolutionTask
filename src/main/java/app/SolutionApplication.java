package app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;


@SpringBootApplication
@EnableConfigurationProperties
public class SolutionApplication {

	public static void main(String[] args) {
		SpringApplication.run(SolutionApplication.class, args);
	}

}

package response.entity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
@EnableJpaRepositories
public class EntityApplication {

	public static void main(String[] args) {
		SpringApplication.run(EntityApplication.class, args);
	}

}

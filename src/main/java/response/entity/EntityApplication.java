package response.entity;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.bcrypt.BCrypt;


@SpringBootApplication
@EnableJpaRepositories
@Slf4j
public class EntityApplication {

	public static void main(String[] args) {
		String hashed = BCrypt.hashpw("asdxdlol", BCrypt.gensalt(12));
		System.out.println(hashed);
		SpringApplication.run(EntityApplication.class, args);
	}

}

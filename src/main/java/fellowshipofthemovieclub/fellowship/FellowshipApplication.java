package fellowshipofthemovieclub.fellowship;

import fellowshipofthemovieclub.fellowship.jpaentities.UserInfo;
import fellowshipofthemovieclub.fellowship.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.stream.Stream;

@SpringBootApplication
public class
FellowshipApplication {

	public static void main(String[] args) {
		SpringApplication.run(FellowshipApplication.class, args);
	}


}

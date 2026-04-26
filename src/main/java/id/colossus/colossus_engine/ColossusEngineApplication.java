package id.colossus.colossus_engine;

import id.colossus.budget.account.config.JpaConfig;
import id.colossus.budget.account.config.SecurityConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@ComponentScan(basePackages = {"id.colossus.helloworld", "id.colossus.budget", "id.colossus.colossus_engine"})
@Import({SecurityConfig.class, JpaConfig.class})
public class ColossusEngineApplication {

	public static void main(String[] args) {
		SpringApplication.run(ColossusEngineApplication.class, args);
	}

}

package id.colossus.colossus_engine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"id.colossus.helloworld", "id.colossus.colossus_engine"})
public class ColossusEngineApplication {

	public static void main(String[] args) {
		SpringApplication.run(ColossusEngineApplication.class, args);
	}

}

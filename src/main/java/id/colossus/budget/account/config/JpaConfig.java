package id.colossus.budget.account.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories("id.colossus.budget.account.repository")
@EnableJpaAuditing
public class JpaConfig {
}
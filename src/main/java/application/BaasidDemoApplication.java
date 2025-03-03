package application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@ComponentScan("controller,config,bean,service,component")
@EnableJpaRepositories({"repository"})
@EntityScan("entity")
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class BaasidDemoApplication {

  public static void main(String[] args) {
    SpringApplication.run(BaasidDemoApplication.class, args);
  }

}

package net.bcsoft.bcosft;

import net.bcsoft.bcosft.service.RoleService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class BcosftApplication {

	public static void main(String[] args) {
		SpringApplication.run(BcosftApplication.class, args);
	}

}

package net.bcsoft.bcosft;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class BcosftApplication {

	public static void main(String[] args) {
		SpringApplication.run(BcosftApplication.class, args);
	}

}

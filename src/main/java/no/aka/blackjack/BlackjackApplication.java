package no.aka.blackjack;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = { "no.aka.blackjack" })
public class BlackjackApplication {

	public static void main(String[] args) {
		SpringApplication.run(BlackjackApplication.class, args);
	}

}

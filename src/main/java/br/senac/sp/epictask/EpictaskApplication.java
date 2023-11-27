package br.senac.sp.epictask;

import br.senac.sp.epictask.principal.Principal;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EpictaskApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(EpictaskApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Principal principal = new Principal();
		principal.exibeMenu();
	}
}

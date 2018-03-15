package fi.academy.miniprojekti2;

import fi.academy.miniprojekti2.Entityt.Kayttaja;
import fi.academy.miniprojekti2.Entityt.Viesti;
import fi.academy.miniprojekti2.Repot.Kayttajarepo;
import fi.academy.miniprojekti2.Repot.Viestirepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Miniprojekti2Application {

	public static void main(String[] args) {
		SpringApplication.run(Miniprojekti2Application.class, args);
	}

	@Bean
	CommandLineRunner luoViestit(Viestirepo viestirepo, Kayttajarepo kayttajarepo) {
		return (args) -> {
            Kayttaja a = new Kayttaja("satu", "salasana");
            a.kryptaaSalasana();
            kayttajarepo.save(a);

            Viesti c = new Viesti("Minulla on paljon töitä", "Työt", "liikunta");
            c.setKayttaja(a);

			viestirepo.save(c);

            a.asetaOmiinViesteihin(c);
            kayttajarepo.save(a);
		};
	}


}

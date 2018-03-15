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
			Kayttaja a = new Kayttaja("Satu", "salasana");
			a.kryptaaSalasana();
			kayttajarepo.save(a);
			Viesti c = new Viesti("Foorumilla on kirjautumismahdollisuus, mutta sen pystyy ohittamaan klikkaamalla. Onko tämä tietoturvariski, pitääkö olla huolissaan? ", "Onks se niin tarkkaa", "Tietoturva");
			c.setKayttaja(a);
			viestirepo.save(c);
			a.asetaOmiinViesteihin(c);
			kayttajarepo.save(a);

			Kayttaja a1 = new Kayttaja("Waltteri", "salasana");
			a1.kryptaaSalasana();
			kayttajarepo.save(a1);
			Viesti c1 = new Viesti("En osaa kirjoittaa mainia itse. Pitääkö olla huolissaan?", "PSVM", "Java");
			c1.setKayttaja(a1);
			viestirepo.save(c1);
			a1.asetaOmiinViesteihin(c1);
			kayttajarepo.save(a1);

			Kayttaja a2 = new Kayttaja("Aleksi", "salasana");
			a2.kryptaaSalasana();
			kayttajarepo.save(a2);
			Viesti c2 = new Viesti("Gitti on ihan perseestä ja nopeemmin selvittäis sähköpostilla. Pitääkö olla huolissaan?", "I don't GIT IT!", "GIT");
			c2.setKayttaja(a2);
			viestirepo.save(c2);
			a2.asetaOmiinViesteihin(c2);
			kayttajarepo.save(a2);

			Kayttaja a3 = new Kayttaja("Aino", "salasana");
			a3.kryptaaSalasana();
			kayttajarepo.save(a3);
			Viesti c3 = new Viesti("Samulla ja Tommilla ei ole vuoronumeroita. Pitääkö olla huolissaan siitä, että on huolissaan?", "Jonotus for the win", "Oppiminen");
			c3.setKayttaja(a3);
			viestirepo.save(c3);
			a3.asetaOmiinViesteihin(c3);
			kayttajarepo.save(a3);
		};

	}


}

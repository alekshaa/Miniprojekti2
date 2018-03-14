package fi.academy.miniprojekti2.Repot;

import fi.academy.miniprojekti2.Entityt.Kayttaja;
import org.springframework.data.repository.CrudRepository;

public interface Kayttajarepo extends CrudRepository<Kayttaja, Integer> {

    Kayttaja findByKayttajanimi(String kayttajanimi);
}

package fi.academy.miniprojekti2.Repot;

import fi.academy.miniprojekti2.Entityt.Viesti;
import org.springframework.data.repository.CrudRepository;

public interface Viestirepo extends CrudRepository<Viesti, Integer> {
}

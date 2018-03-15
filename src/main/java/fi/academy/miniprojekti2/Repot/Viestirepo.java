package fi.academy.miniprojekti2.Repot;

import fi.academy.miniprojekti2.Entityt.Viesti;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface Viestirepo extends CrudRepository<Viesti, Integer> {


    @Query("SELECT u FROM Viesti u WHERE u.teksti LIKE CONCAT('%', :teksti, '%') ")
    List<Viesti> haeViesti(@Param("teksti") String teksti);

    @Query("SELECT u FROM Viesti u WHERE u.aihealue = :teksti")
    List<Viesti> haeAihealueenViestit(@Param("teksti") String teksti);

}

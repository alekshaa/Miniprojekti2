package fi.academy.miniprojekti2.Entityt;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Kayttaja {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false, unique = true)
    private String kayttajanimi;
    private String salasana;
    private String kiinnostus;

    @JsonIgnore
    @OneToMany(mappedBy = "kayttaja")
    private List<Viesti>omatviestit = new ArrayList<>();

    public Kayttaja(String kayttajanimi, String salasana) {
        this.kayttajanimi = kayttajanimi;
        this.salasana = salasana;
    }


    public Kayttaja(String kayttajanimi, String salasana, String kiinnostus) {
        this.kayttajanimi = kayttajanimi;
        this.salasana = salasana;
        this.kiinnostus = kiinnostus;
    }

    public Kayttaja() {
    }

    public void asetaOmiinViesteihin(Viesti viesti) {
        omatviestit.add(viesti);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getKayttajanimi() {
        return kayttajanimi;
    }

    public void setKayttajanimi(String kayttajanimi) {
        this.kayttajanimi = kayttajanimi;
    }

    public String getSalasana() {
        return salasana;
    }

    public void setSalasana(String salasana) {
        this.salasana = salasana;
    }

    public List<Viesti> getOmatviestit() {
        return omatviestit;
    }

    public void setOmatviestit(List<Viesti> omatviestit) {
        this.omatviestit = omatviestit;
    }

}

package fi.academy.miniprojekti2.Entityt;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Kayttaja {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false, unique = true)
    private String kayttajanimi;
    private String salasana;
    private List<String> aihealueet;
    @OneToMany(mappedBy = "kayttaja")
    private Set<Viesti>omatviestit=new HashSet<Viesti>();

    public Kayttaja(String kayttajanimi, String salasana) {
        this.kayttajanimi = kayttajanimi;
        this.salasana = salasana;
    }

    public Kayttaja() {
    }

    public Kayttaja(String kayttajanimi, String salasana, List<String> aihealueet) {
        this.kayttajanimi = kayttajanimi;
        this.salasana = salasana;
        this.aihealueet = aihealueet;
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

    public List<String> getAihealueet() {
        return aihealueet;
    }

    public void setAihealueet(List<String> aihealueet) {
        this.aihealueet = aihealueet;
    }

    public Set<Viesti> getOmatviestit() {
        return omatviestit;
    }

    public void setOmatviestit(Set<Viesti> omatviestit) {
        this.omatviestit = omatviestit;
    }
}

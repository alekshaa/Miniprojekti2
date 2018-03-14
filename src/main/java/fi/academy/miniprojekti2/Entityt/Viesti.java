package fi.academy.miniprojekti2.Entityt;

import javax.persistence.*;
import javax.validation.constraints.Null;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Viesti {
    @Id @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private LocalDate luontiaika = LocalDate.now();
    private String teksti;
    private String otsikko;
    private String aihealue;
    private String vastaus;
//    private List<String> vastaukset = new ArrayList<>();

//    @OneToOne(mappedBy = "mihinVastattu", fetch = FetchType.EAGER)

//    @ManyToOne
//    @JoinColumn
//    @Null
//    Viesti mihinVastattu;

    @ManyToOne
    @JoinColumn(name = "kayttaja")
    private Kayttaja kayttaja;

    public Viesti() {
    }

    public Viesti(String teksti, String otsikko, String aihealue) {
        this.teksti = teksti;
        this.otsikko = otsikko;
        this.aihealue = aihealue;
    }

    public Viesti(String teksti, String otsikko, String aihealue, Kayttaja kayttaja) {
        this.teksti = teksti;
        this.otsikko = otsikko;
        this.aihealue = aihealue;
        this.kayttaja = kayttaja;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getLuontiaika() {
        return luontiaika;
    }

    public void setLuontiaika(LocalDate luontiaika) {
        this.luontiaika = luontiaika;
    }

    public String getTeksti() {
        return teksti;
    }

    public void setTeksti(String teksti) {
        this.teksti = teksti;
    }

    public String getOtsikko() {
        return otsikko;
    }

    public void setOtsikko(String otsikko) {
        this.otsikko = otsikko;
    }

    public String getAihealue() {
        return aihealue;
    }

    public void setAihealue(String aihealue) {
        this.aihealue = aihealue;
    }

    public Kayttaja getKayttaja() {
        return kayttaja;
    }

    public void setKayttaja(Kayttaja kayttaja) {
        this.kayttaja = kayttaja;
    }

    public String getVastaus() {
        return vastaus;
    }

    public void setVastaus(String vastaus) {
        this.vastaus = vastaus;
    }

//    public List<String> getVastaukset() {
//        return vastaukset;
//    }
//
//    public void setVastaukset(List<String> vastaukset) {
//        this.vastaukset = vastaukset;
//    }
//
//    public Viesti getMihinVastattu() {
//        return mihinVastattu;
//    }
//
//    public void setMihinVastattu(Viesti mihinVastattu) {
//        this.mihinVastattu = mihinVastattu;
//    }
}

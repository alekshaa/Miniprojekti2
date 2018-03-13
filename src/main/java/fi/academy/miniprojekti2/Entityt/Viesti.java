package fi.academy.miniprojekti2.Entityt;

import javax.persistence.*;
import java.time.LocalDate;


public class Viesti {
    @Id @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false)
    private LocalDate luontiaika = LocalDate.now();
    private String teksti;
    private String otsikko;
    private String aihealue;
    @ManyToOne(optional = false)
    private Kayttaja kayttaja;

    public Viesti() {
    }

    public Viesti(String teksti, String otsikko, String aihealue, Kayttaja kayttaja) {
        this.teksti = teksti;
        this.otsikko = otsikko;
        this.aihealue = aihealue;
        this.kayttaja = kayttaja;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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
}

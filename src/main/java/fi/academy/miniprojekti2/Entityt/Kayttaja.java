package fi.academy.miniprojekti2.Entityt;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.crypto.bcrypt.BCrypt;

import javax.persistence.*;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Kayttaja {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false, unique = true)
    private String kayttajanimi;
    private String salasana;
    private String salt;

    @JsonIgnore
    @OneToMany(mappedBy = "kayttaja")
    private List<Viesti>omatviestit = new ArrayList<>();

    public Kayttaja(String kayttajanimi, String salasana) {
        this.kayttajanimi = kayttajanimi;
        this.salasana = salasana;
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
        this.salt = BCrypt.gensalt();
        this.salasana = BCrypt.hashpw(salasana, this.salt);
    }

    public List<Viesti> getOmatviestit() {
        return omatviestit;
    }

    public void setOmatviestit(List<Viesti> omatviestit) {
        this.omatviestit = omatviestit;
    }

    public static String getSalt() throws NoSuchAlgorithmException, NoSuchProviderException {
        SecureRandom sr = SecureRandom.getInstance("SHAP1PRNG", "SUN");
        byte[] salt = new byte[16];
        sr.nextBytes(salt);
        return salt.toString();
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }
}

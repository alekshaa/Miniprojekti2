package fi.academy.miniprojekti2.Servicet;


import fi.academy.miniprojekti2.Entityt.Kayttaja;
import fi.academy.miniprojekti2.Repot.Kayttajarepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;

//Tätä rajapintaa käytetään käyttäjän hakemiseen tietokannasta käyttäjänimen perusteella.
//Annetut oikeudet alhaalla (User ja Admin)

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private Kayttajarepo kayttajarepo;

    @Override
/*    @Transactional(readOnly = true)*/
    public UserDetails loadUserByUsername(String kayttajanimi) throws UsernameNotFoundException {
        Kayttaja kayttaja = kayttajarepo.findByKayttajanimi(kayttajanimi);
        if( kayttaja == null)  {
            throw new UsernameNotFoundException("Käyttäjää " + kayttaja + " ei ole olemassa.");
        }

        return new org.springframework.security.core.userdetails.User(
                kayttaja.getKayttajanimi(),
                kayttaja.getSalasana(),
                true,
                true,
                true,
                true,
                Arrays.asList(new SimpleGrantedAuthority("USER"), new SimpleGrantedAuthority("ADMIN")));

    }


}

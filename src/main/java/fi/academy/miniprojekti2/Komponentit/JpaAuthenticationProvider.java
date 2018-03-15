package fi.academy.miniprojekti2.Komponentit;

import fi.academy.miniprojekti2.Entityt.Kayttaja;
import fi.academy.miniprojekti2.Repot.Kayttajarepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JpaAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private Kayttajarepo kayttajarepo;

    @Override
    public Authentication authenticate(Authentication a) throws AuthenticationException {
        String kayttajanimi = a.getPrincipal().toString();
        String salasana = a.getCredentials().toString();
        Kayttaja kirjautuja = new Kayttaja(kayttajanimi, salasana);
        kirjautuja.kryptaaSalasana();

        Kayttaja kayttaja = kayttajarepo.findByKayttajanimi(kayttajanimi);

        if(kayttaja == null) {
            throw new AuthenticationException("Ei pystetty löytämään käyttäjää " + kayttajanimi) {
            };
        }


        if(!BCrypt.hashpw(kirjautuja.getSalasana(), kayttaja.getSalt()).equals(kayttaja.getSalasana())) {
                throw new AuthenticationException("Ei pystytty löytämään käyttäjää " + kayttajanimi) {
                };
            }


        List<GrantedAuthority> grantedAuths = new ArrayList<>();
        grantedAuths.add(new SimpleGrantedAuthority("USER"));

        return new UsernamePasswordAuthenticationToken(kayttaja.getKayttajanimi(), salasana, grantedAuths);
    }

    @Override
    public boolean supports(Class<?> type) {
        return true;
    }
}

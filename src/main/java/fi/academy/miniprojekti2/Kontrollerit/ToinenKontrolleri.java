package fi.academy.miniprojekti2.Kontrollerit;


import fi.academy.miniprojekti2.Entityt.Kayttaja;
import fi.academy.miniprojekti2.Entityt.Viesti;
import fi.academy.miniprojekti2.Repot.Kayttajarepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

@Controller
public class ToinenKontrolleri {

    @Autowired
    Kayttajarepo kayttajarepo;

    @RequestMapping("/rekisteroidy")
    public String rekisteroidy() {
        return "rekisteroidy";
    }

    @RequestMapping("/")
    public String kirjauduSisaan(Model model) {
        model.addAttribute("tulokset", kayttajarepo.findAll());
        return "kirjaudu";
    }

    @PostMapping("/rekisterissa")
    public String lisaaKayttaja(Kayttaja käyttäjä, Model model) {
        käyttäjä.kryptaaSalasana();
        kayttajarepo.save(käyttäjä);
        model.addAttribute("tulokset", kayttajarepo.findAllByKayttajanimi(käyttäjä.getKayttajanimi()));
        return "rekonnistui";
    }

    @PostMapping("/etusivu")
    @Transactional
    public String avaaAloitussivu(Kayttaja käyttäjä, Model model, HttpServletResponse httpServletResponse) {
        Kayttaja k = kayttajarepo.findByKayttajanimi(käyttäjä.getKayttajanimi());
        käyttäjä.kryptaaSalasana(k.getSalt());
        Viesti viesti = new Viesti();
        model.addAttribute("viesti", viesti);
        if(k != null && k.getSalasana().equals(käyttäjä.getSalasana())) {
            Cookie newCookie = new Cookie("kayttaja", ""+k.getId());
            newCookie.setMaxAge(24 * 60 * 60);
            httpServletResponse.addCookie(newCookie);
            return "redirect:/etusivu";
        } else {
            return "redirect:/";
        }
    }
    @RequestMapping("/ulos")
    public String kirjaaulos(HttpServletResponse httpServletResponse) {
        Cookie newCookie = new Cookie("kayttaja", "");
        newCookie.setMaxAge(24 * 60 * 60);
        httpServletResponse.addCookie(newCookie);
        return "redirect:/";
    }

    @RequestMapping("/tarkoitus")
    public String siirryTarkoitussivulle() {
        return "tarkoitus";
    }
}

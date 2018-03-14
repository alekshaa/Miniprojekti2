package fi.academy.miniprojekti2.Kontrollerit;


import fi.academy.miniprojekti2.Entityt.Kayttaja;
import fi.academy.miniprojekti2.Repot.Kayttajarepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ToinenKontrolleri {

/*@PostMapping("/rekisteroidy")
    public ResponseEntity<?> rekisteroiKayttaja*/

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

    @PostMapping("/kirjauduttu")
    public String avaaAloitussivu(Kayttaja käyttäjä, Model model) {
        Kayttaja k = kayttajarepo.findByKayttajanimi(käyttäjä.getKayttajanimi());
        käyttäjä.kryptaaSalasana(k.getSalt());
        if(k != null && k.getSalasana().equals(käyttäjä.getSalasana())) {
            model.addAttribute("otsikko", "Hei");
            return "aloitussivu";
        } else {
            return "redirect:/kirjaudu";
        }
    }
    @RequestMapping("/ulos")
    public String kirjaaulos() {
        return "redirect:/";
    }

    @PostMapping("/ulos")
    public String kirjauduUlos(Model model) {
        model.addAttribute("otsikko", "Hei");
        return "redirect:/";
    }


    //HUOM, logout-sivuun tarvitaan postmapping






}

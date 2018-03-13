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

    @RequestMapping("/index")
    public String haeKayttajat(Model model) {
        model.addAttribute("tulokset", kayttajarepo.findAll());
        return "rek";
    }

    @PostMapping("/lisaakayttaja")
    public String lisaaKayttaja(Kayttaja käyttäjä) {
        kayttajarepo.save(käyttäjä);
        return "redirect:index";
    }






}

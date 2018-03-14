package fi.academy.miniprojekti2.Kontrollerit;

import fi.academy.miniprojekti2.Entityt.Viesti;
import fi.academy.miniprojekti2.Repot.Kayttajarepo;
import fi.academy.miniprojekti2.Repot.Viestirepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class Kontrolleri {
    Kayttajarepo kayttajarepo;
    Viestirepo viestirepo;


    public Kontrolleri(@Autowired Kayttajarepo kayttajarepo, Viestirepo viestirepo) {
        this.kayttajarepo = kayttajarepo;
        this.viestirepo = viestirepo;
    }

    @RequestMapping("/")
    public String avaaAloitussivu(Model model) {
        model.addAttribute("otsikko","Hei" );
        return "aloitussivu";
    }

    @GetMapping("/liikunta")
    public String liikuntaKeskustelu(Model model) {
        model.addAttribute("kaikkiViestit", viestirepo.findAll());
        model.addAttribute("uusiViesti", new Viesti());
        return "liikunta";
    }

    @GetMapping("/muokkaa")
    public String muokkaaViestia(@RequestParam(name = "id") int id, Model model) {
        Optional<Viesti> etsitty = viestirepo.findById(id);
        if (etsitty == null)
            return "redirect:liikunta";
        model.addAttribute("viesti", etsitty);
        return "viestimuokkaus";
    }

    @GetMapping("/ruoka")
    public String ruokaKeskustelu(Model model) {
        return "ruoka";
    }
    @GetMapping("/autot")
    public String autotKeskustelu(Model model) {
        return "autot";
    }
    @GetMapping("/koti")
    public String kotiKeskustelu(Model model) {
        return "koti";
    }

    @PostMapping("lisattyLiikunta")
    public String liikunnanLisays(Viesti viesti, Model model){

        viestirepo.save(viesti);

        model.addAttribute("kaikkiViestit", viestirepo.findAll());
        model.addAttribute("uusiViesti", new Viesti());
        return "liikunta";
    }

    @GetMapping("/rekonnistui")
    public String viesti() {
        return "rekonnistui";
    }
}

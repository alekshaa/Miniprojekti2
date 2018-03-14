package fi.academy.miniprojekti2.Kontrollerit;

import fi.academy.miniprojekti2.Entityt.Viesti;
import fi.academy.miniprojekti2.Repot.Kayttajarepo;
import fi.academy.miniprojekti2.Repot.Viestirepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Controller
public class Kontrolleri {
    Kayttajarepo kayttajarepo;
    Viestirepo viestirepo;


    public Kontrolleri(@Autowired Kayttajarepo kayttajarepo, Viestirepo viestirepo) {
        this.kayttajarepo = kayttajarepo;
        this.viestirepo = viestirepo;
    }

    @GetMapping("/etusivu")
    @Transactional
    public String hakulomake(Model model) {
        Viesti viesti = new Viesti();
        model.addAttribute("viesti", viesti);
        return "aloitussivu";
    }

    @PostMapping("/haeviesti")
    public String haeViesti(Viesti viesti, Model model) {
        List<Viesti> viestit = viestirepo.haeViesti(viesti.getTeksti());
        model.addAttribute("viesti", viestit);
        return "haetutViestit";
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

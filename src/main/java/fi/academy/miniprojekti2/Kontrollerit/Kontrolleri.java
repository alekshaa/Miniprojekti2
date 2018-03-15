package fi.academy.miniprojekti2.Kontrollerit;

import fi.academy.miniprojekti2.Entityt.Kayttaja;
import fi.academy.miniprojekti2.Entityt.Viesti;
import fi.academy.miniprojekti2.Repot.Kayttajarepo;
import fi.academy.miniprojekti2.Repot.Viestirepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
        model.addAttribute("kaikkiViestit", viestirepo.findAll());
        model.addAttribute("uusiViesti",new Viesti());
        model.addAttribute("kayttaja",new Kayttaja());
        return "aloitussivu";
    }

    @RequestMapping("/aihealue")
    public String liikuntaKeskustelu(@RequestParam(name = "id") String id, Model model) {
        model.addAttribute("kaikkiViestit", viestirepo.haeAihealueenViestit(id));
        Viesti uusiViest = new Viesti();
        uusiViest.setAihealue(id);
        model.addAttribute("uusiViesti", uusiViest);
        model.addAttribute("kayttaja",new Kayttaja());
        return "aihealue";
    }
//
//    @RequestMapping("/hakutulokset")
//    public String hakuTulokset(@RequestParam(name = "id") String id, Model model) {
//        model.addAttribute("kaikkiViestit", viestirepo.haeAihealueenViestit(id));
//        Viesti uusiViest = new Viesti();
//        uusiViest.setAihealue(id);
//        model.addAttribute("uusiViesti", uusiViest);
//        model.addAttribute("kayttaja",new Kayttaja());
//        return "hakutulokset";
//    }

    @GetMapping("/muokkaa")
    public String muokkaaViestia(@RequestParam(name = "id") int id, Model model) {
        Optional<Viesti> etsitty = viestirepo.findById(id);
        if (!etsitty.isPresent())
            return "redirect:aihealue";
        model.addAttribute("viesti", etsitty.get());
        return "viestimuokkaus";
    }

    @PostMapping("/muokkaus")
    public String muokataanViesti(Viesti viesti, Model model) {
        viestirepo.findById(viesti.getId()).get().setVastaus(viesti.getTeksti().toString());
        model.addAttribute("kaikkiViestit", viestirepo.findAll());
        model.addAttribute("uusiViesti", new Viesti());
        return "redirect:aihealue?id=" + viestirepo.findById(viesti.getId()).get().getAihealue();
    }

    @PostMapping("lisattyViesti")
    public String liikunnanLisays(Kayttaja kayttaja, Viesti viesti, Model model){
        kayttajarepo.save(kayttaja);
        viesti.setKayttaja(kayttaja);
        viestirepo.save(viesti);
        model.addAttribute("kaikkiViestit", viestirepo.haeAihealueenViestit(viesti.getAihealue()));
        model.addAttribute("uusiViesti", new Viesti());
        return "redirect:aihealue?id=" + viesti.getAihealue();
    }

    @PostMapping("lisattyViesti2")
    public String liikunnanLisays2(Kayttaja kayttaja, Viesti viesti, Model model){
        kayttajarepo.save(kayttaja);
        viesti.setKayttaja(kayttaja);
        viestirepo.save(viesti);
        model.addAttribute("kaikkiViestit", viestirepo.findAll());
        model.addAttribute("uusiViesti", new Viesti());
        return "aloitussivu";
    }

    @GetMapping("/rekonnistui")
    public String viesti() {
        return "rekonnistui";
    }

    @PostMapping("/haeviesti")
    public String haeViesti(Viesti viesti, Model model) {
        List<Viesti> viestit = viestirepo.haeViesti(viesti.getTeksti());
        model.addAttribute("viesti",viestit);
        return "hakutulokset";
    }

}

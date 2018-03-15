package fi.academy.miniprojekti2.Kontrollerit;

import fi.academy.miniprojekti2.Entityt.Kayttaja;
import fi.academy.miniprojekti2.Entityt.Viesti;
import fi.academy.miniprojekti2.Repot.Kayttajarepo;
import fi.academy.miniprojekti2.Repot.Viestirepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.Arrays;
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
        return "aloitussivu";
    }

    @PostMapping("/haeviesti")
    public String haeViesti(Viesti viesti, Model model) {
        List<Viesti> viestit = viestirepo.haeViesti(viesti.getTeksti());
        model.addAttribute("viesti", viestit);
        return "haetutViestit";
    }

    @GetMapping("/liikunta")
    public String liikuntaKeskustelu(@RequestParam(name = "id") String id, Model model, HttpServletRequest request) {

        Kayttaja k = haeKayttajaCookielistasta(request);

        model.addAttribute("kaikkiViestit", viestirepo.haeAihealueenViestit(id));
        Viesti uusiViesti = new Viesti();
        uusiViesti.setKayttaja(k);
        model.addAttribute("uusiViesti", uusiViesti);
        return "liikunta";
    }

    private Kayttaja haeKayttajaCookielistasta(HttpServletRequest request) {
        Kayttaja k = null;
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for(Cookie c : cookies) {
                if(c.getName().equals("kayttaja")) {
                    String idstr = c.getValue();
                    if (idstr != null && !idstr.trim().isEmpty()) {
                        try {
                            int kid = Integer.parseInt(idstr);
                            Optional<Kayttaja> optk = kayttajarepo.findById(kid);
                            if (optk.isPresent()) {
                                k = optk.get();
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Cookie-virhe: käyttäjäid ei numero");
                        }
                    }
                    break;
                }
            }
        }
        return k;
    }

//    @GetMapping("/liikunta")
//    public String liikuntaKeskustelu(Model model) {
//        model.addAttribute("kaikkiViestit", viestirepo.findAll());
//        model.addAttribute("uusiViesti", new Viesti());
//        return "liikunta";
//    }

    @GetMapping("/muokkaa")
    public String muokkaaViestia(@RequestParam(name = "id") int id, Model model) {
        Optional<Viesti> etsitty = viestirepo.findById(id);
        if (!etsitty.isPresent())
            return "redirect:liikunta";
        model.addAttribute("viesti", etsitty.get());
        return "viestimuokkaus";
    }

    @PostMapping("/muokkaus")
    public String muokataanViesti(Viesti viesti, Model model) {
//        viestirepo.findById(viesti.getId()).get().getVastaukset().add(viesti.getTeksti().toString());
        viestirepo.findById(viesti.getId()).get().setVastaus(viesti.getTeksti().toString());
//        viestirepo.findById(viesti.getId()).get().setTeksti(viesti.getTeksti().toString());
        model.addAttribute("kaikkiViestit", viestirepo.findAll());
//        model.addAttribute("kaikkiViestit", viestirepo.findAllMihinVastattuIsNull());
        model.addAttribute("uusiViesti", new Viesti());
        return "liikunta";
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

package fr.perso.morpion.controller;

import fr.perso.morpion.model.Tour;
import fr.perso.morpion.service.implementation.PartieServiceImpl;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Logger;

@RestController
@RequestMapping("/api/partie")
public class PartieController {

    @Autowired
    private PartieServiceImpl partieService;

    private static final Logger logger = Logger.getLogger(LoggerFactory.class.getName());

    @GetMapping("/{id}")
    public Character[] recupererGrille(@PathVariable(value = "id")Integer id) {
        return partieService.recupererGrille(partieService.getPartieById(id));
    }

    @PostMapping("/{id}/tour")
    public void jouerTour(@RequestParam int emplacement, @PathVariable(value = "id") Integer id) {
        Tour tour = partieService.genererTour(partieService.getPartieById(id), emplacement);
        partieService.ajouterTourAPartie(id, tour);
    }

    @GetMapping("/new")
    public int nouvellePartie() {
        return partieService.commencerPartie("Joueur 1", "Joueur 2");
    }

}

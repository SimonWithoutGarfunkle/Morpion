package fr.perso.morpion.controller;

import fr.perso.morpion.model.Partie;
import fr.perso.morpion.model.Tour;
import fr.perso.morpion.service.implementation.PartieServiceImpl;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Logger;

/**
 * Controller principal de l'application
 * Gere les parties et les tours
 */
@RestController
@RequestMapping("/api/partie")
public class PartieController {

    @Autowired
    private PartieServiceImpl partieService;

    private static final Logger logger = Logger.getLogger(LoggerFactory.class.getName());

    /**
     * Demande la grille de la partie en cours
     *
     * @param id de la partie
     * @return un tableau de characteres qui represente la grille de jeu à jour
     */
    @GetMapping("/{id}")
    public Character[] recupererGrille(@PathVariable(value = "id")Integer id) {
        return partieService.recupererGrille(partieService.getPartieById(id));
    }

    /**
     * Nouveau tour pour la partie en cours
     *
     * @param emplacement à jouer
     * @param id de la partie
     * @return true si le tour est valide et a été joué, false sinon
     */
    @PostMapping("/{id}/tour")
    public boolean jouerTour(@RequestParam int emplacement, @PathVariable(value = "id") Integer id) {
        return partieService.ajouterTourAPartie(id, emplacement);
    }

    /**
     * Demande l'etat de la partie (en cours, égalité ou qui a gagné)
     *
     * @param id de la partie
     * @return l'etat de la partie
     */
    @GetMapping("/{id}/status")
    public String statusPartie(@PathVariable(value = "id") Integer id) {
        return partieService.getPartieById(id).getStatusPartie().toString();
    }

    /**
     * Demarre une nouvelle partie
     *
     * @return l'id de la nouvelle partie
     */
    @GetMapping("/new")
    public int nouvellePartie() {
        return partieService.commencerPartie("Joueur 1", "Joueur 2");
    }

}

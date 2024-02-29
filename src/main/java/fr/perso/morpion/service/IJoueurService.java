package fr.perso.morpion.service;

import fr.perso.morpion.model.Joueur;

import java.util.List;

public interface IJoueurService {
    Joueur ajouterJoueur(Joueur joueur);

    Joueur getJoueurById(Integer id);

    List<Joueur> getAllJoueurs();

    Joueur modifierJoueur(Joueur joueur);

    void supprimerJoueur(Integer id);

}

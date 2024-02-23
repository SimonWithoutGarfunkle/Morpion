package fr.perso.morpion.service;

import fr.perso.morpion.model.Joueur;

import java.util.List;

public interface IJoueurService {
    void ajouterJoueur(Joueur joueur);

    Joueur getJoueurById(Integer id);

    List<Joueur> getAllJoueurs();

    void modifierJoueur(Joueur joueur);

    void supprimerJoueur(Integer id);

}

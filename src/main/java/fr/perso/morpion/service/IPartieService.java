package fr.perso.morpion.service;

import fr.perso.morpion.model.Joueur;
import fr.perso.morpion.model.Partie;
import fr.perso.morpion.model.Tour;

public interface IPartieService {

    void ajouterTourAPartie(Integer idPartie, Tour tour);

    Tour genererTour(Partie partie, int emplacement);

    Joueur prochainJoueurAJouer(Partie partie);

    Partie ajouterPartie(Partie partie);

    Partie getPartieById(Integer id);

    Partie modifierPartie(Partie partie);

    void supprimerPartie(Integer id);

    Character[] recupererGrille(Partie partie);

    boolean validerTour(Partie partie, Tour tour);

    boolean validerPartie(Partie partie);

    int commencerPartie(String joueur1, String joueur2);
}

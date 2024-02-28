package fr.perso.morpion.service;

import fr.perso.morpion.model.Joueur;
import fr.perso.morpion.model.Partie;
import fr.perso.morpion.model.StatusPartie;
import fr.perso.morpion.model.Tour;

public interface IPartieService {

    boolean ajouterTourAPartie(Integer idPartie, int emplacement);

    Joueur prochainJoueurAJouer(Partie partie);

    Partie ajouterPartie(Partie partie);

    Partie getPartieById(Integer id);

    Partie modifierPartie(Partie partie);

    void supprimerPartie(Integer id);

    Character[] recupererGrille(Partie partie);

    boolean validerTour(Partie partie, Tour tour);

    boolean validerPartie(Partie partie);

    int commencerPartie(String joueur1, String joueur2);

    boolean miseAJourStatusPartie(Partie partie);

    StatusPartie mettreAJourStatusPartie(Partie partie);

    StatusPartie quiGagne(Partie partie);

    Character[][] grilleOnlyJoueur(Partie partie, Joueur joueur);
}

package fr.perso.morpion.service;

import fr.perso.morpion.model.Tour;

public interface ITourService {
    Tour getTourById(Integer id);
    Tour ajouterTour(Tour tour);
    Tour modifierTour(Tour tour);
    void supprimerTour(Integer id);

}

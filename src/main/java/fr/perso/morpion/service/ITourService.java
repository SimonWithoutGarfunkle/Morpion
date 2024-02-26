package fr.perso.morpion.service;

import fr.perso.morpion.model.Tour;

public interface ITourService {
    public Tour getTourById(Integer id);
    public Tour ajouterTour(Tour tour);
    public Tour modifierTour(Tour tour);
    public void supprimerTour(Integer id);

}

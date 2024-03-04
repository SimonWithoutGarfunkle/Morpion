package fr.perso.morpion.service;

import fr.perso.morpion.model.Tour;
import fr.perso.morpion.repository.TourRepository;
import org.springframework.stereotype.Service;

@Service
public class TourService {

    private final TourRepository tourRepository;

    public TourService(TourRepository tourRepository) { this.tourRepository = tourRepository; }


    public Tour getTourById(Integer id) { return tourRepository.findById(id).orElse(null); }

    public Tour ajouterTour(Tour tour) { return tourRepository.save(tour); }

    public Tour modifierTour(Tour tour) { return tourRepository.save(tour); }

    public void supprimerTour(Integer id) { tourRepository.deleteById(id); }

}

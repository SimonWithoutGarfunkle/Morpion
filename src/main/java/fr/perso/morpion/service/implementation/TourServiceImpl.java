package fr.perso.morpion.service.implementation;

import fr.perso.morpion.model.Tour;
import fr.perso.morpion.repository.TourRepository;
import fr.perso.morpion.service.ITourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TourServiceImpl implements ITourService {

    @Autowired
    private TourRepository tourRepository;


    @Override
    public Tour getTourById(Integer id) { return tourRepository.findById(id).orElse(null); }

    @Override
    public Tour ajouterTour(Tour tour) { return tourRepository.save(tour); }

    @Override
    public Tour modifierTour(Tour tour) { return tourRepository.save(tour); }

    @Override
    public void supprimerTour(Integer id) { tourRepository.deleteById(id); }

}

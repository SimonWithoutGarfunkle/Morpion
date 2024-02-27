package fr.perso.morpion.service.implementation;

import fr.perso.morpion.model.Joueur;
import fr.perso.morpion.model.Partie;
import fr.perso.morpion.model.Tour;
import fr.perso.morpion.repository.PartieRepository;
import fr.perso.morpion.service.IPartieService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class PartieServiceImpl implements IPartieService {

    @Autowired
    private PartieRepository partieRepository;

    @Autowired
    private JoueurServiceImpl joueurService;

    @Autowired
    private TourServiceImpl tourService;

    private static final Logger logger = Logger.getLogger(LoggerFactory.class.getName());

    @Override
    public void ajouterTourAPartie(Integer idPartie, int emplacement) {
        Partie partie = getPartieById(idPartie);
        if (partie != null) {
            Tour tour = tourService.ajouterTour(new Tour(partie, partie.getTours().size() + 1, prochainJoueurAJouer(partie).getMarqueur(), emplacement));
            logger.info("Tour cree id n°" + tour.getIdTour());
            if (validerTour(partie, tour)) {
                logger.info("Tour valide");
                partie.getTours().add(tour);
                partieRepository.save(partie);
            } else {
                logger.info("Tour invalide");
            }
        }
    }

    @Override
    public Joueur prochainJoueurAJouer(Partie partie) {
        if (partie.getTours().isEmpty()) {
            return partie.getJoueur1();
        }
        if (partie.getTours().get(partie.getTours().size() - 1).getMarqueur() == partie.getJoueur1().getMarqueur()) {
            return partie.getJoueur2();
        } else {
            return partie.getJoueur1();
        }
    }

    @Override
    public Partie ajouterPartie(Partie partie) { return partieRepository.save(partie);  }

    @Override
    public Partie getPartieById(Integer id) { return partieRepository.findById(id).orElse(null); }

    @Override
    public Partie modifierPartie(Partie partie) { return partieRepository.save(partie); }

    @Override
    public void supprimerPartie(Integer id) { partieRepository.deleteById(id); }

    @Override
    public Character[] recupererGrille(Partie partie) {
        return partie.getTours().stream().map(Tour::getMarqueur).toArray(Character[]::new);
    }

    @Override
    public boolean validerTour(Partie partie, Tour tour) {
        if (tour.getEmplacement() < 0 || tour.getEmplacement() > partie.getX() * partie.getY()) { return false; }
        if (partie.getTours().stream().anyMatch(t -> t.getEmplacement() == (tour.getEmplacement()))) { return false; }
        if (tour.getPartie() != partie) { return false; }
        if (tour.getMarqueur() != partie.getJoueur1().getMarqueur()
                && tour.getMarqueur() != partie.getJoueur2().getMarqueur()) { return false; }
        if (tour.getNumeroTour() != partie.getTours().size() + 1) { return false; }

        return true;

    }

    @Override
    public boolean validerPartie(Partie partie) {
        return true;
    }

    @Override
    public int commencerPartie(String joueur1, String joueur2) {
        Joueur j1 = joueurService.ajouterJoueur(new Joueur(joueur1, 'X'));
        Joueur j2 = joueurService.ajouterJoueur(new Joueur(joueur2, 'O'));
        Partie partie = ajouterPartie(new Partie(j1, j2));
        return partie.getIdPartie();
    }


}

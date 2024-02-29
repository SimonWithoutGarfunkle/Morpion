package fr.perso.morpion.service.implementation;

import fr.perso.morpion.model.Joueur;
import fr.perso.morpion.model.Partie;
import fr.perso.morpion.model.StatusPartie;
import fr.perso.morpion.model.Tour;
import fr.perso.morpion.repository.PartieRepository;
import fr.perso.morpion.service.IPartieService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
    public boolean ajouterTourAPartie(Integer idPartie, int emplacement) {
        Partie partie = getPartieById(idPartie);
        Tour tour = new Tour(partie, partie.getTours().size() + 1, prochainJoueurAJouer(partie).getMarqueur(), emplacement);
        if (validerTour(partie, tour)) {
            logger.info("Tour valide");
            tour = tourService.ajouterTour(tour);
            partie.getTours().add(tour);
            partie = partieRepository.save(partie);
            mettreAJourStatusPartie(partie);
            return true;
        } else {
            logger.info("Tour invalide");
            return false;
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
        Character[] grille = new Character[partie.getHauteur() * partie.getLongueur()];
        Arrays.fill(grille, ' ');
        partie.getTours().forEach(tour -> grille[tour.getEmplacement()] = tour.getMarqueur());
        return grille;
    }

    @Override
    public boolean validerTour(Partie partie, Tour tour) {
        if (tour.getEmplacement() < 0 || tour.getEmplacement() >= partie.getLongueur() * partie.getHauteur()) { return false; }
        if (partie.getTours().stream().anyMatch(t -> t.getEmplacement() == (tour.getEmplacement()))) { return false; }
        if (tour.getPartie() != partie) { return false; }
        if (tour.getMarqueur() != partie.getJoueur1().getMarqueur()
                && tour.getMarqueur() != partie.getJoueur2().getMarqueur()) { return false; }
        return tour.getNumeroTour() == partie.getTours().size() + 1;

    }

    @Override
    public boolean validerPartie(Partie partie) {
        return true;
    }

    @Override
    public int commencerPartie(String joueur1, String joueur2) {
        Joueur j1 = joueurService.ajouterJoueur(new Joueur(joueur1, 'X'));
        Joueur j2 = joueurService.ajouterJoueur(new Joueur(joueur2, 'O'));
        Partie partie = ajouterPartie(new Partie(j1, j2, 3, 3));
        return partie.getIdPartie();
    }

    @Override
    public StatusPartie mettreAJourStatusPartie(Partie partie) {
        if (partie.getStatusPartie() != quiGagne(partie)) {
            logger.info("Mise à jour du status de la partie");
            partie.setStatusPartie(quiGagne(partie));
            modifierPartie(partie);
        }
        return partie.getStatusPartie();
    }


    @Override
    public StatusPartie quiGagne(Partie partie) {
        if (partie.getStatusPartie() != StatusPartie.EN_COURS) { return partie.getStatusPartie(); }

        if (troisALaSuite(grilleOnlyJoueur(partie, partie.getJoueur1()))) { return StatusPartie.JOUEUR1_GAGNE; }
        if (troisALaSuite(grilleOnlyJoueur(partie, partie.getJoueur2()))) { return StatusPartie.JOUEUR2_GAGNE; }
        if (partie.getTours().size() == partie.getLongueur() * partie.getHauteur()) { return StatusPartie.EGALITE; }
        logger.info("Qui Gagne ? -> Partie en cours");

        return StatusPartie.EN_COURS;
    }

    @Override
    public Character[][] grilleOnlyJoueur(Partie partie, Joueur joueur) {
        Character[][] grille = new Character[partie.getHauteur()][partie.getLongueur()];
        Arrays.stream(grille).forEach(row -> Arrays.fill(row, '-'));
        partie.getTours().stream().filter(t -> t.getMarqueur() == joueur.getMarqueur()).forEach(t -> {
            int x = t.getEmplacement() % partie.getLongueur();
            int y = t.getEmplacement() / partie.getLongueur();
            grille[y][x] = 'x';
        });
        return grille;
    }

    public boolean troisALaSuite(Character[][] grille) {
        return testLigne(grille) || testColonne(grille) || testDiagonalePrincipale(grille) || testDiagonaleSecondaire(grille);
    }

    public boolean testLigne(Character[][] grille) {
        return Arrays.stream(grille, 0, grille.length)
                .anyMatch(this::matchString);
    }

    public boolean matchString(Character[] ligne) {
        Pattern pattern = Pattern.compile("xxx");
        return pattern.matcher(Arrays.stream(ligne).map(String::valueOf).collect(Collectors.joining())).find();
    }

    public boolean testColonne(Character[][] grille) {
        return testLigne(rotation90DegreDroite(grille));
    }

    private Character[][] rotation90DegreDroite(Character[][] grille) {
        afficherGrilleDansConsole("Avant rotation : ", grille);

        Character[][] grilleRotated = IntStream.range(0, grille.length)
                .mapToObj(i -> IntStream.range(0, grille.length)
                        .mapToObj(j -> grille[grille.length - j - 1][i])
                        .toArray(Character[]::new))
                .toArray(Character[][]::new);

        afficherGrilleDansConsole("Après rotation : ", grilleRotated);
        return grilleRotated;
    }

    public boolean testDiagonalePrincipale(Character[][] grille) {
        Pattern pattern = Pattern.compile("xxx");
        String diagonal = IntStream.range(0, grille.length)
                .mapToObj(i -> grille[i][i].toString())
                .collect(Collectors.joining());
        return pattern.matcher(diagonal).find();
    }

    public boolean testDiagonaleSecondaire(Character[][] grille) {
        return testDiagonalePrincipale(rotation90DegreDroite(grille));
    }

    public void afficherGrilleDansConsole(String string, Character[][] grille) {
        System.out.println(string);
        for (Character[] row : grille) {
            System.out.println(Arrays.stream(row).map(String::valueOf).collect(Collectors.joining()));
        }
    }
}
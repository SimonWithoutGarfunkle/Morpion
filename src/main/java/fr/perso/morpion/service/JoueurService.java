package fr.perso.morpion.service;

import fr.perso.morpion.model.Joueur;
import fr.perso.morpion.repository.JoueurRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JoueurService {

    private final JoueurRepository joueurRepository;

    public JoueurService(JoueurRepository joueurRepository) {
        this.joueurRepository = joueurRepository;
    }

    public Joueur ajouterJoueur(Joueur joueur) { return joueurRepository.save(joueur); }

    public Joueur getJoueurById(Integer id) {
        return joueurRepository.findById(id).orElse(null);
    }

    public List<Joueur> getAllJoueurs() {
        return joueurRepository.findAll();
    }

    public Joueur modifierJoueur(Joueur joueur) { return joueurRepository.save(joueur); }

    public void supprimerJoueur(Integer id) {
        joueurRepository.deleteById(id);
    }


}

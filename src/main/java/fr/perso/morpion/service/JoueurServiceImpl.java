package fr.perso.morpion.service;

import fr.perso.morpion.model.Joueur;
import fr.perso.morpion.repository.JoueurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JoueurServiceImpl implements IJoueurService {

    @Autowired
    private JoueurRepository joueurRepository;

    @Override
    public void ajouterJoueur(Joueur joueur) {
        joueurRepository.save(joueur);
    }

    @Override
    public Joueur getJoueurById(Integer id) {
        return joueurRepository.findById(id).orElse(null);
    }

    @Override
    public List<Joueur> getAllJoueurs() {
        return joueurRepository.findAll();
    }

    @Override
    public void modifierJoueur(Joueur joueur) {
        joueurRepository.save(joueur);

    }

    @Override
    public void supprimerJoueur(Integer id) {
        joueurRepository.deleteById(id);
    }

}

package fr.perso.morpion.service.implementation;

import fr.perso.morpion.model.Joueur;
import fr.perso.morpion.repository.JoueurRepository;
import fr.perso.morpion.service.IJoueurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JoueurServiceImpl implements IJoueurService {

    @Autowired
    private JoueurRepository joueurRepository;

    @Override
    public Joueur ajouterJoueur(Joueur joueur) { return joueurRepository.save(joueur); }

    @Override
    public Joueur getJoueurById(Integer id) {
        return joueurRepository.findById(id).orElse(null);
    }

    @Override
    public List<Joueur> getAllJoueurs() {
        return joueurRepository.findAll();
    }

    @Override
    public Joueur modifierJoueur(Joueur joueur) { return joueurRepository.save(joueur); }

    @Override
    public void supprimerJoueur(Integer id) {
        joueurRepository.deleteById(id);
    }


}

package fr.perso.morpion.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class Jeu {

    private Joueur joueur1;

    private Joueur joueur2;

    private List<Partie> parties;
}

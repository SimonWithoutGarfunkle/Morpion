package fr.perso.morpion.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Tour {
    private int numeroTour;

    private Joueur joueur;

    private int emplacement;

}

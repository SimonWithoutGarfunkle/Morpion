package fr.perso.morpion.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Tour {

    @Id
    private Integer idTour;

    @ManyToOne
    @JoinColumn(name = "partie_id")
    private Partie partie;

    private int numeroTour;

    private char marqueur;

    private int emplacement;

}

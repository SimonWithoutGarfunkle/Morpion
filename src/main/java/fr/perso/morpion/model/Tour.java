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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tour")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "partie_id")
    private Partie partie;

    private int numeroTour;

    private char marqueur;

    private int emplacement;

    public Tour(Partie partie, int i, char marqueur, int emplacement) {
        this.partie = partie;
        this.numeroTour = i;
        this.marqueur = marqueur;
        this.emplacement = emplacement;
    }
}

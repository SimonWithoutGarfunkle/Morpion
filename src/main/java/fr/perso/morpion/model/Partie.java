package fr.perso.morpion.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Partie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPartie;

    @OneToMany(mappedBy = "partie", cascade = CascadeType.ALL)
    private List<Tour> tours;

    @ManyToOne
    @JoinColumn(name = "joueur1_id")
    private Joueur joueur1;

    @ManyToOne
    @JoinColumn(name = "joueur2_id")
    private Joueur joueur2;

    private int x = 3;

    private int y = 3;

}

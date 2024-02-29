package fr.perso.morpion.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
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

    private int longueur;

    private int hauteur;

    @Enumerated(EnumType.STRING)
    private StatusPartie statusPartie = StatusPartie.EN_COURS;

    public Partie (Joueur joueur1, Joueur joueur2, int longueur, int hauteur) {
        this.joueur1 = joueur1;
        this.joueur2 = joueur2;
        tours = new ArrayList<>();
        this.longueur = longueur;
        this.hauteur = hauteur;
    }

}

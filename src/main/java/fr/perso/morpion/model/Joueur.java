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
public class Joueur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_joueur")
    private Integer id;

    private String pseudo;

    private char marqueur;

    public Joueur(String joueur1, char x) {
        this.pseudo = joueur1;
        this.marqueur = x;
    }
}

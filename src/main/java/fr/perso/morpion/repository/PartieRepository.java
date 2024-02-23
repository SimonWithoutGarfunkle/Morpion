package fr.perso.morpion.repository;

import fr.perso.morpion.model.Partie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PartieRepository extends JpaRepository<Partie, Integer> {
}

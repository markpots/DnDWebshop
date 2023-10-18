package nl.miwnn.se12.mark.DnDWebshop.repository;

import nl.miwnn.se12.mark.DnDWebshop.model.Bundle;
import nl.miwnn.se12.mark.DnDWebshop.model.Dice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DiceRepository extends JpaRepository<Dice, Integer> {
    Optional<Dice> findDiceByDiceName(String diceName);
}

package nl.miwnn.se12.mark.DnDWebshop.repository;

import nl.miwnn.se12.mark.DnDWebshop.model.Dice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiceRepository extends JpaRepository<Dice, Integer> {
}

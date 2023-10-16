package nl.miwnn.se12.mark.DnDWebshop.repository;

import nl.miwnn.se12.mark.DnDWebshop.model.Dice;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * This file is made by: Mark Pots
 * <m.pots@st.hanze.nl>
 * Get and save dice sets to the DB
 */

public interface DiceRepository extends JpaRepository<Dice, Integer> {
}

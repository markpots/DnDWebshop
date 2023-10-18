package nl.miwnn.se12.mark.DnDWebshop.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * This file is made by: Mark Pots
 * <m.pots@st.hanze.nl>
 * This code is made for:
 */

@Entity
@Getter @Setter
public class CopyDice {

    @Id
    @GeneratedValue
    private Integer copyDiceId;

    private Boolean available = true;

    @ManyToOne
    private Dice dice;
}

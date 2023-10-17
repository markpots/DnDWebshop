package nl.miwnn.se12.mark.DnDWebshop.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

/**
 * This file is made by: Mark Pots
 * <m.pots@st.hanze.nl>
 * The concept of a die set that is available at my webshop
 */

@Entity
@Getter @Setter
public class Dice {

    @Id @GeneratedValue
    private Integer diceId;
    private String diceColor;
    private String diceName;
    private String diceMaterial;
    private Boolean available = true;

}

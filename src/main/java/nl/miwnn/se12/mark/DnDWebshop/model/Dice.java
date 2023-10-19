package nl.miwnn.se12.mark.DnDWebshop.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

/**
 * This file is made by: Mark Pots
 * <m.pots@st.hanze.nl>
 * The concept of a die set that is available at my webshop
 */

@Entity
@Getter @Setter
public class Dice {

    public Dice(String diceColor, String diceName, String diceMaterial) {
        this.diceColor = diceColor;
        this.diceName = diceName;
        this.diceMaterial = diceMaterial;
    }

    public Dice(){
    }

    @Id @GeneratedValue
    private Integer diceId;
    private String diceColor;
    @Column(unique = true)
    private String diceName;
    private String diceMaterial;

    @OneToMany(mappedBy = "dice")
    private List<CopyDice> diceList;

    public int getNumberOfAvailableDice() {
        int count = 0;

        for (CopyDice dice : diceList) {
            if (dice.getAvailable())
                count++;
        }

        return count;
    }
}

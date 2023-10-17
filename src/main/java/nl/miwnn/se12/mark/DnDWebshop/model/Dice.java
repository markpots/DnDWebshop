package nl.miwnn.se12.mark.DnDWebshop.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * This file is made by: Mark Pots
 * <m.pots@st.hanze.nl>
 * The concept of a die set that is available at my webshop
 */
@Entity
public class Dice {

    @Id @GeneratedValue
    private int diceId;
    private String diceColor;
    private String diceName;
    private String diceMaterial;


    public int getDiceId() {
        return diceId;
    }

    public void setDiceId(int id) {
        this.diceId = id;
    }

    public String getDiceColor() {
        return diceColor;
    }

    public void setDiceColor(String diceColor) {
        this.diceColor = diceColor;
    }

    public String getDiceName() {
        return diceName;
    }

    public void setDiceName(String diceName) {
        this.diceName = diceName;
    }

    public String getDiceMaterial() {
        return diceMaterial;
    }

    public void setDiceMaterial(String diceMaterial) {
        this.diceMaterial = diceMaterial;
    }
}

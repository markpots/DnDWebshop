package nl.miwnn.se12.mark.DnDWebshop.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * This file is made by: Mark Pots
 * <m.pots@st.hanze.nl>
 * The concept of a bundle that is available at my webshop
 */

@Entity
@Getter @Setter
public class Bundle {

    @Id @GeneratedValue
    private Integer bundleId;

    @Column(unique = true)
    private String bundleName;
}

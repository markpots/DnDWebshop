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
 * A physical copy of a bundle that can be available for buying
 */

@Entity
@Getter @Setter
public class CopyBundle {

    @Id @GeneratedValue
    private Integer copyBundleId;

    private Boolean available = true;

    @ManyToOne
    private Bundle bundle;
}

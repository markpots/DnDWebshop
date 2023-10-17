package nl.miwnn.se12.mark.DnDWebshop.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

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

    @OneToMany(mappedBy = "bundle")
    private List<CopyBundle> copies;

    public int getNumberOfAvailableBundleCopies() {
        int count = 0;

        for (CopyBundle copy : copies) {
            if (copy.getAvailable())
                count++;
        }

        return count;
    }
}















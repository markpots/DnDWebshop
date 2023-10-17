package nl.miwnn.se12.mark.DnDWebshop.repository;

import nl.miwnn.se12.mark.DnDWebshop.model.Bundle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BundleRepository extends JpaRepository<Bundle, Integer> {
    Optional<Bundle> findBundleByBundleName(String bundleName);
}

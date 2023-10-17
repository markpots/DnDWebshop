package nl.miwnn.se12.mark.DnDWebshop.controller;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import nl.miwnn.se12.mark.DnDWebshop.model.Bundle;
import nl.miwnn.se12.mark.DnDWebshop.model.CopyBundle;
import nl.miwnn.se12.mark.DnDWebshop.repository.BundleRepository;
import nl.miwnn.se12.mark.DnDWebshop.repository.CopyBundleRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

/**
 * This file is made by: Mark Pots
 * <m.pots@st.hanze.nl>
 * Handle everything regarding bundle copies
 */

@Controller
@RequiredArgsConstructor
public class CopyBundleController {
    private final BundleRepository bundleRepository;
    private final CopyBundleRepository copyBundleRepository;

    @GetMapping("/copyBundle/new/{copyBundleId}")
    private String createNewCopyBundle(@PathVariable("copyBundleId") Integer bundleId) {
        Optional<Bundle> bundleOptional = bundleRepository.findById(bundleId);

        if (bundleOptional.isPresent()) {
            CopyBundle copyBundle = new CopyBundle();
            copyBundle.setBundle(bundleOptional.get());
            copyBundleRepository.save(copyBundle);
        }

        return "redirect:/bundle";
    }
}














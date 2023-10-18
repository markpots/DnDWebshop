package nl.miwnn.se12.mark.DnDWebshop.controller;

import lombok.RequiredArgsConstructor;
import nl.miwnn.se12.mark.DnDWebshop.model.Bundle;
import nl.miwnn.se12.mark.DnDWebshop.model.CopyBundle;
import nl.miwnn.se12.mark.DnDWebshop.repository.BundleRepository;
import nl.miwnn.se12.mark.DnDWebshop.repository.CopyBundleRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

/**
 * This file is made by: Mark Pots
 * <m.pots@st.hanze.nl>
 * Handle everything regarding bundle quantities
 */

@Controller
@RequestMapping("/copyBundle")
@RequiredArgsConstructor
public class CopyBundleController {
    private final BundleRepository bundleRepository;
    private final CopyBundleRepository copyBundleRepository;

    @GetMapping("/new/{bundleId}")
    private String createNewCopyBundle(@PathVariable("bundleId") Integer bundleId) {
        Optional<Bundle> bundleOptional = bundleRepository.findById(bundleId);

        if (bundleOptional.isPresent()) {
            CopyBundle copyBundle = new CopyBundle();
            copyBundle.setBundle(bundleOptional.get());
            copyBundleRepository.save(copyBundle);
        }

        return "redirect:/bundle";
    }

    @GetMapping("/borrow/{copyBundleId}")
    private String makeCopyBundleUnavailable(@PathVariable("copyBundleId") Integer copyBundleId) {
        return setAvailabilityForCopyBundleAndRedirectToBundle(copyBundleId, false);
    }

    @GetMapping("/return/{copyBundleId}")
    private String makeCopyBundleAvailable(@PathVariable("copyBundleId") Integer copyBundleId) {
        return setAvailabilityForCopyBundleAndRedirectToBundle(copyBundleId, true);
    }

    private String setAvailabilityForCopyBundleAndRedirectToBundle(Integer copyBundleId, boolean available) {
        Optional<CopyBundle> optionalCopyBundle = copyBundleRepository.findById(copyBundleId);

        if (optionalCopyBundle.isEmpty()) {
            return "redirect:/bundle";
        }

        CopyBundle copyBundle = optionalCopyBundle.get();
        copyBundle.setAvailable(available);
        copyBundleRepository.save(copyBundle);

        return String.format("redirect:/book/detail/%s", copyBundle.getBundle().getBundleName());
    }
}














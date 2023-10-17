package nl.miwnn.se12.mark.DnDWebshop.controller;

import nl.miwnn.se12.mark.DnDWebshop.model.Bundle;
import nl.miwnn.se12.mark.DnDWebshop.repository.BundleRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

/**
 * This file is made by: Mark Pots
 * <m.pots@st.hanze.nl>
 * Handle all request related to Bundles
 */

@Controller
public class BundleController {
    private final BundleRepository bundleRepository;

    public BundleController(BundleRepository bundleRepository) {
        this.bundleRepository = bundleRepository;
    }

    @GetMapping("/bundle")
    private String showBundleOverview(Model model) {
        model.addAttribute("allBundle", bundleRepository.findAll());

        return "bundleOverview";
    }

    @GetMapping("/bundle/new")
    private String showBundleForm(Model model) {
        model.addAttribute("bundle", new Bundle());

        return "bundleForm";
    }

    @PostMapping("/bundle/new")
    private String saveOrUpdateDice(@ModelAttribute("bundle") Bundle bundleToBeSaved, BindingResult result) {
        if (!result.hasErrors()) {
            bundleRepository.save(bundleToBeSaved);
        }

        return "redirect:/bundle";
    }

    @GetMapping("/bundle/detail/{bundleName}")
    private String showBundleDetail(@PathVariable("bundleName") String bundleName, Model model) {
        Optional<Bundle> optionalBundle = bundleRepository.findBundleByBundleName(bundleName);

        if (optionalBundle.isEmpty()) {
            return "redirect:/bundle";
        }

        model.addAttribute("bundleToBeShown", optionalBundle.get());

        return "bundleDetail";
    }

}

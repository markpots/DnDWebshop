package nl.miwnn.se12.mark.DnDWebshop.controller;

import lombok.RequiredArgsConstructor;
import nl.miwnn.se12.mark.DnDWebshop.model.Bundle;
import nl.miwnn.se12.mark.DnDWebshop.repository.BundleRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * This file is made by: Mark Pots
 * <m.pots@st.hanze.nl>
 * Handle all request related to Bundles
 */

@Controller
@RequestMapping("/bundle")
@RequiredArgsConstructor
public class BundleController {
    private final BundleRepository bundleRepository;

    @GetMapping("")
    private String showBundleOverview(Model model) {
        model.addAttribute("allBundle", bundleRepository.findAll());

        return "bundleOverview";
    }

    @GetMapping("/new")
    private String showBundleForm(Model model) {
        model.addAttribute("bundle");

        return "bundleForm";
    }

    @PostMapping("/new")
    private String saveOrUpdateBundle(@ModelAttribute("bundle") Bundle bundleToBeSaved, BindingResult result) {
        if (!result.hasErrors()) {
            bundleRepository.save(bundleToBeSaved);
        }

        return "redirect:/bundle";
    }

    @GetMapping("/detail/{bundleName}")
    private String showBundleDetail(@PathVariable("bundleName") String bundleName, Model model) {
        Optional<Bundle> optionalBundle = bundleRepository.findBundleByBundleName(bundleName);

        if (optionalBundle.isEmpty()) {
            return "redirect:/bundle";
        }

        model.addAttribute("bundleToBeShown", optionalBundle.get());

        return "bundleDetail";
    }

    @GetMapping("/edit/{bundleName}")
    private String showEditBundleForm(@PathVariable("bundleName") String bundleName, Model model){
        Optional<Bundle> optionalBundle = bundleRepository.findBundleByBundleName(bundleName);

        if (optionalBundle.isEmpty()) {
            return "redirect:/bundle";
        }

        model.addAttribute("bundle", optionalBundle.get());

        return "bundleForm";
    }


    @GetMapping("/delete/{bundleName}")
    private String deleteBundle(@PathVariable("bundleName") String bundleName) {
        Optional<Bundle> optionalBundle = bundleRepository.findBundleByBundleName(bundleName);

        if (optionalBundle.isEmpty()) {
            return "redirect:/bundle/overview";
        }

        bundleRepository.delete(optionalBundle.get());

        return "redirect:/bundle";
    }
}

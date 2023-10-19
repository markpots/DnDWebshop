package nl.miwnn.se12.mark.DnDWebshop.controller;

import lombok.RequiredArgsConstructor;
import net.datafaker.Faker;
import nl.miwnn.se12.mark.DnDWebshop.model.Bundle;
import nl.miwnn.se12.mark.DnDWebshop.model.Dice;
import nl.miwnn.se12.mark.DnDWebshop.repository.BundleRepository;
import nl.miwnn.se12.mark.DnDWebshop.repository.DiceRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.*;

/**
 * This file is made by: Mark Pots
 * <m.pots@st.hanze.nl>
 * Initializes my DB with some testing data
 */

@Controller
@RequiredArgsConstructor
public class InitializeController {
    private static final int AMOUNT_GENERATING_DICE = 30;
    private static final int AMOUNT_GENERATING_BUNDLES = 5;
    private final DiceRepository diceRepository;
    private final BundleRepository bundleRepository;

    @GetMapping("/dice/initialize")
    private String initializeDiceDB() {
        if (!diceRepository.findAll().isEmpty()) {
            return "redirect:/dice";
        }

        Faker fakerDice = new Faker();

        List<String> materialsList = Arrays.asList("Metal", "Plastic", "Stone", "Bone", "Wood");

        ArrayList<Dice> diceList = new ArrayList<>();
        Set<String> usedDiceNames = new HashSet<>();

        while (diceList.size() < AMOUNT_GENERATING_DICE) {
            String diceName = fakerDice.dungeonsAndDragons().races();

            generateUniqueDice(usedDiceNames, diceName, fakerDice, materialsList, diceList);
        }

        diceRepository.saveAll(diceList);

        return "redirect:/dice";
    }

    private static void generateUniqueDice(Set<String> usedDiceNames, String diceName, Faker fakerDice, List<String> materialsList, ArrayList<Dice> diceList) {
        if (usedDiceNames.add(diceName)) {
            String color = fakerDice.color().name();
            String material = materialsList.get(fakerDice.random().nextInt(materialsList.size()));
            diceList.add(new Dice(color, diceName, material));
        }
    }

    @GetMapping("/bundle/initialize")
    private String initializeDB() {
        if (!bundleRepository.findAll().isEmpty()) {
            return "redirect:/bundle";
        }

        Faker fakerBundle = new Faker();

        List<Bundle> bundleList = new ArrayList<>();
        Set<String> usedBundleNames = new HashSet<>();

        while (bundleList.size() < AMOUNT_GENERATING_BUNDLES) {
            String bundleName = generateUniqueBundleName(fakerBundle, usedBundleNames);

            bundleList.add(new Bundle(bundleName));
        }

        bundleRepository.saveAll(bundleList);
        return "redirect:/bundle";
    }

    private String generateUniqueBundleName(Faker faker, Set<String> usedBundleNames) {
        while (true) {
            String name = faker.harryPotter().spell();
            if (usedBundleNames.add(name)) {
                return name;
            }
        }
    }
}












package nl.miwnn.se12.mark.DnDWebshop.controller;

import lombok.RequiredArgsConstructor;
import nl.miwnn.se12.mark.DnDWebshop.model.Bundle;
import nl.miwnn.se12.mark.DnDWebshop.model.Dice;
import nl.miwnn.se12.mark.DnDWebshop.repository.DiceRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * This file is made by: Mark Pots
 * <m.pots@st.hanze.nl>
 * Handle all request related to Dice
 */

@Controller
@RequestMapping("/dice")
@RequiredArgsConstructor
public class DiceController {
    private final DiceRepository diceRepository;

    @GetMapping("")
    private String showDiceOverview(Model model) {
        model.addAttribute("allDice", diceRepository.findAll());

        return "diceOverview";
    }

    @GetMapping("/new")
    private String showDiceForm(Model model) {
        model.addAttribute("dice", new Dice());

        return "diceForm";
    }

    @PostMapping("/new")
    private String saveOrUpdateDice(@ModelAttribute("dice") Dice diceToBeSaved, BindingResult result) {
        if (!result.hasErrors()) {
            diceRepository.save(diceToBeSaved);
        }

        return "redirect:/dice";
    }

    @GetMapping("/detail/{diceName}")
    private String showDiceDetail(@PathVariable("diceName") String diceName, Model model) {
        Optional<Dice> optionalDice = diceRepository.findDiceByDiceName(diceName);

        if (optionalDice.isEmpty()) {
            return "redirect:/dice";
        }

        model.addAttribute("diceToBeShown", optionalDice.get());

        return "diceDetail";
    }

    @GetMapping("/edit/{diceName}")
    private String showEditDiceForm(@PathVariable("diceName") String diceName, Model model){
        Optional<Dice> optionalDice = diceRepository.findDiceByDiceName(diceName);

        if (optionalDice.isEmpty()) {
            return "redirect:/dice";
        }

        model.addAttribute("dice", optionalDice.get());

        return "diceForm";
    }


    @GetMapping("/delete/{diceName}")
    private String deleteDice(@PathVariable("diceName") String diceName) {
        Optional<Dice> optionalDice = diceRepository.findDiceByDiceName(diceName);

        if (optionalDice.isEmpty()) {
            return "redirect:/dice/overview";
        }

        diceRepository.delete(optionalDice.get());

        return "redirect:/dice";
    }
}

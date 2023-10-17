package nl.miwnn.se12.mark.DnDWebshop.controller;

import nl.miwnn.se12.mark.DnDWebshop.model.Dice;
import nl.miwnn.se12.mark.DnDWebshop.repository.DiceRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * This file is made by: Mark Pots
 * <m.pots@st.hanze.nl>
 * Handle all request related to Dice
 */
@Controller
public class DiceController {
    private final DiceRepository diceRepository;

    public DiceController(DiceRepository diceRepository) {
        this.diceRepository = diceRepository;
    }

    @GetMapping("/dice")
    private String showDiceOverview(Model model) {
        model.addAttribute("allDice", diceRepository.findAll());

        return "diceOverview";
    }

    @GetMapping("/dice/new")
    private String showDiceForm(Model model) {
        model.addAttribute("dice", new Dice());

        return "diceForm";
    }

    @PostMapping("/dice/new")
    private String saveOrUpdateDice(@ModelAttribute("dice") Dice diceToBeSaved, BindingResult result) {
        if (!result.hasErrors()) {
            diceRepository.save(diceToBeSaved);
        }

        return "redirect:/dice";
    }
}

package nl.miwnn.se12.mark.DnDWebshop.controller;

import lombok.RequiredArgsConstructor;
import nl.miwnn.se12.mark.DnDWebshop.model.CopyDice;
import nl.miwnn.se12.mark.DnDWebshop.model.Dice;
import nl.miwnn.se12.mark.DnDWebshop.repository.CopyDiceRepository;
import nl.miwnn.se12.mark.DnDWebshop.repository.DiceRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

/**
 * This file is made by: Mark Pots
 * <m.pots@st.hanze.nl>
 * Handle everything regarding dice quantities
 */
@Controller
@RequestMapping("/copyDice")
@RequiredArgsConstructor
public class CopyDiceController {
    private final DiceRepository diceRepository;
    private final CopyDiceRepository copyDiceRepository;

    @GetMapping("/new/{diceId}")
    private String createNewCopyBundle(@PathVariable("diceId") Integer diceId) {
        Optional<Dice> diceOptional = diceRepository.findById(diceId);

        if (diceOptional.isPresent()) {
            CopyDice copyDice = new CopyDice();
            copyDice.setDice(diceOptional.get());
            copyDiceRepository.save(copyDice);
        }

        return "redirect:/dice";
    }

    @GetMapping("/borrow/{copyDiceId}")
    private String makeCopyDiceUnavailable(@PathVariable("copyDiceId") Integer copyDiceId) {
        return setAvailabilityForCopyDiceAndRedirectToBundle(copyDiceId, false);
    }

    @GetMapping("/return/{copyDiceId}")
    private String makeCopyBundleAvailable(@PathVariable("copyDiceId") Integer copyDiceId) {
        return setAvailabilityForCopyDiceAndRedirectToBundle(copyDiceId, true);
    }

    private String setAvailabilityForCopyDiceAndRedirectToBundle(Integer copyDiceId, boolean available) {
        Optional<CopyDice> optionalCopyDice = copyDiceRepository.findById(copyDiceId);

        if (optionalCopyDice.isEmpty()) {
            return "redirect:/bundle";
        }

        CopyDice copyDice = optionalCopyDice.get();
        copyDice.setAvailable(available);
        copyDiceRepository.save(copyDice);

        return String.format("redirect:/dice/detail/%s", copyDice.getDice().getDiceName());
    }
}

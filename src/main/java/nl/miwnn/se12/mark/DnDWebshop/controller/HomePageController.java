package nl.miwnn.se12.mark.DnDWebshop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * This file is made by: Mark Pots
 * <m.pots@st.hanze.nl>
 * This code is made for:
 */

@Controller
public class HomePageController {

    @GetMapping("/home")
    public String showHomePage() {
        return "homePage";
    }


}

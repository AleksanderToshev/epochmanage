package com.alex.epochmanage.controller;

import com.alex.epochmanage.model.User;
import com.alex.epochmanage.repository.UserRepo;
import com.alex.epochmanage.service.AuthenticationService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

//Logic got from the StackOverflow guy
//Added myself accountForm2
//Comments as well hehe :3

@Controller
public class LoginController {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private AuthenticationService authenticationService;

    @GetMapping("/login")
    public String showLoginPage(ModelMap model) {
        model.addAttribute("login", new User());
        return "login-page";
    }

    @PostMapping("/login")
    public String submitLoginIn(@ModelAttribute("login") User user, RedirectAttributes redirectAttributes,
                                @RequestParam String username, @RequestParam String password, HttpSession session) {
        User accountForm = userRepo.findByEmailAndPassword(user.getEmail(), user.getPassword());
        Optional<User> accountForm2 = userRepo.findByUsername(user.getUsername());


        //Checks if the email, username and password written by the user exist
        if (accountForm == null || accountForm2.isEmpty()) {
            redirectAttributes.addFlashAttribute("errorMessage1", "Account does not exist");
            return "redirect:/login";
        }
        else if (!authenticationService.authenticateUser(username, password)) {
            return "redirect:/login";
        }
        else {
            session.setAttribute("username", username);
            redirectAttributes.addFlashAttribute("errorMessage1", "Login Successful!");
            return "redirect:/viewToDoList";
        }
    }
}

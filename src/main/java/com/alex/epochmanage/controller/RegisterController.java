package com.alex.epochmanage.controller;


import com.alex.epochmanage.model.User;
import com.alex.epochmanage.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;


//Logic got from the StackOverflow guy

@RestController
public class RegisterController {

    @Autowired
    private UserRepo userRepo;

    @GetMapping("/registration")
    public String showRegistrationPage(ModelMap model) {
        model.addAttribute("registration", new User());
        return "registration";
    }
    @PostMapping("/registration")
    public String registerAccount(@ModelAttribute("registration") User userForm,
                                  BindingResult bindingResult,
                                  Model model,
                                  RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            return "error";
        }

        //Getting the data for the username, email and password
        model.addAttribute("username", userForm.getUsername());
        model.addAttribute("email", userForm.getEmail());
        model.addAttribute("password", userForm.getPassword());

        User emailChecker = userRepo.findByEmail(userForm.getEmail());
        Optional<User> usernameChecker = userRepo.findByUsername(userForm.getUsername());

//        if (userForm.getUsername() == null || userForm.getUsername().isEmpty() ||
//                userForm.getEmail() == null || userForm.getEmail().isEmpty() ||
//                userForm.getPassword() == null || userForm.getPassword().isEmpty()) {
//            redirectAttributes.addFlashAttribute("errorMessage", "Please fill in all required fields.");
//            return "/registration";
//        }


        //Checks if the email or username is already registered in the db
        if(emailChecker != null || usernameChecker != null){
            redirectAttributes.addFlashAttribute("errorMessage",
                    "The email or username already exists!");
            return "redirect:/registration";
        }
        else {
            userRepo.save(userForm);
            return "redirect:/login";
        }

    }
}

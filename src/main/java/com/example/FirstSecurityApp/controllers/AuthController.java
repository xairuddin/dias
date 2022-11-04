package com.example.FirstSecurityApp.controllers;

import com.example.FirstSecurityApp.models.Person;
import com.example.FirstSecurityApp.services.PersonDetailsService;
import com.example.FirstSecurityApp.services.RegistrationService;
import com.example.FirstSecurityApp.util.PersonValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("/auth")
public class AuthController {

    private final PersonDetailsService personDetailsService;

    private final PersonValidator personValidator;
    private final RegistrationService registrationService;

    @Autowired
    public AuthController(PersonDetailsService personDetailsService, PersonValidator personValidator, RegistrationService registrationService) {
        this.personDetailsService = personDetailsService;
        this.personValidator = personValidator;
        this.registrationService = registrationService;
    }

    @GetMapping("/login")
    public String loginPage(){
        return "auth/login";
    }
    @GetMapping("/registration")
    public String registrationPage(@ModelAttribute("person") Person person){
        System.out.println(person);
        return "auth/registration";
    }

    @PostMapping("/registration")
    public String performRegistration(@ModelAttribute("person") @Valid Person person,
                                      BindingResult bindingResult){
        personValidator.validate(person, bindingResult);
        if (bindingResult.hasErrors())
            return "/auth/registration";

        registrationService.register(person);

        return "redirect:/auth/login";
    }
    @GetMapping("/forgotPass")
    public String forgotPasswordPage(@ModelAttribute("person") Person person){
        return "/auth/forgotPass";
    }

    @PatchMapping("/forgotPass")
    public String update(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult) {
        personDetailsService.update(person);
        return "redirect:/auth/login";
    }
}

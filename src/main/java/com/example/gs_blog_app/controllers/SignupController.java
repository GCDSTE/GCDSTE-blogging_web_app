package com.example.gs_blog_app.controllers;

import com.example.gs_blog_app.entities.Account;
import com.example.gs_blog_app.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SignupController {

    @Autowired
    private AccountService accountService;

    @GetMapping("/signup")
    public String getSignPage(Model model) {
        Account account = new Account();
        model.addAttribute("account", account);
        return "signup";
    }

    @PostMapping("/signup")
    public String signupNewUser(@ModelAttribute Account account) {
        accountService.save(account);
        return "redirect:/";
    }

}

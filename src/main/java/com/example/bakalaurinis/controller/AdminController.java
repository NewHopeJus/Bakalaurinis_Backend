package com.example.bakalaurinis.controller;

import com.example.bakalaurinis.exceptions.CustomValidationException;
import com.example.bakalaurinis.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
public class AdminController {
    UserService userService;
    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/quizapp/admin")
    public String getUsers(Model model) {
        model.addAttribute("listUsers", userService.getAllUsers());
        return "admin";
    }

    @GetMapping("/users/change-password/{id}")
    public String changePasswordForm(@PathVariable Long id, Model model) {
        model.addAttribute("userId", id);
        return "change-password";
    }

    @PostMapping("/users/change-password")
    public String changePassword(@RequestParam Long userId, @RequestParam String newPassword, RedirectAttributes redirectAttributes) {
        try {
            userService.changePassword(userId, newPassword);
            redirectAttributes.addFlashAttribute("message", "Slaptazodis sekmingai pakeistas!");
        } catch (CustomValidationException e) {
            redirectAttributes.addFlashAttribute("message", e.getMessage());
            redirectAttributes.addFlashAttribute("userId", userId);
            return "redirect:/users/change-password/" + userId;
        }
        return "redirect:/quizapp/admin";
    }

    @GetMapping("/users/block/{id}")
    public String blockUser(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            userService.blockUser(id);
            redirectAttributes.addFlashAttribute("message", "Vartotojas sėkmingai užblokuotas");
        } catch (CustomValidationException e) {
            redirectAttributes.addFlashAttribute("message", "Nepavyko surasti vartotojo");
        }
        return "redirect:/quizapp/admin";
    }

    @GetMapping("/users/delete/{id}")
    public String deleteUser(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            userService.deleteUser(id);
            redirectAttributes.addFlashAttribute("message", "Vartotojas sėkmingai pašalintas");
        } catch (CustomValidationException e) {
            redirectAttributes.addFlashAttribute("message", "Nepavyko surasti vartotojo");
        }
        return "redirect:/quizapp/admin";
    }
}
package com.tugas.deploy.controller;

import com.tugas.deploy.model.User;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {
    private final String ADMIN_USER = "admin";
    private final String ADMIN_PASS = "20230140047";

    // Static list to persist data across requests for this session
    private static List<User> listMahasiswa = new ArrayList<>();

    @GetMapping("/")
    public String loginPage(HttpSession session) {
        if (session.getAttribute("loggedIn") != null) {
            return "redirect:/home";
        }
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        HttpSession session,
                        Model model) {
        if (ADMIN_USER.equals(username) && ADMIN_PASS.equals(password)) {
            session.setAttribute("loggedIn", true);
            return "redirect:/home";
        } else {
            model.addAttribute("error", "Username atau Password salah!");
            return "login";
        }
    }

    @GetMapping("/home")
    public String homePage(HttpSession session, Model model) {
        if (session.getAttribute("loggedIn") == null) {
            return "redirect:/";
        }
        model.addAttribute("listMahasiswa", listMahasiswa);
        return "home";
    }

    @GetMapping("/form")
    public String formPage(HttpSession session, Model model) {
        if (session.getAttribute("loggedIn") == null) {
            return "redirect:/";
        }
        model.addAttribute("user", new User());
        return "form";
    }

    @PostMapping("/save")
    public String saveData(@ModelAttribute User user, HttpSession session) {
        if (session.getAttribute("loggedIn") == null) {
            return "redirect:/";
        }
        listMahasiswa.add(user);
        return "redirect:/home";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
}
package com.example.dokotsubu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.dokotsubu.model.User;

import jakarta.servlet.http.HttpSession;

@Controller
public class LoginController {

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @PostMapping("/login")
    public String login(
            @RequestParam("name") String name,
            @RequestParam("pass") String pass,
            HttpSession session) {

        // 既存のLoginLogic相当
        if ("1234".equals(pass)) {
            User user = new User(name, pass);
            session.setAttribute("loginUser", user);
            return "loginResult";
        }

        // ログイン失敗時はトップに戻る（簡易実装）
        return "redirect:/";
    }
}

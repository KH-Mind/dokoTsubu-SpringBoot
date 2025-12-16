package com.example.dokotsubu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.dokotsubu.model.Mutter;
import com.example.dokotsubu.model.User;
import com.example.dokotsubu.service.MutterService;

import jakarta.servlet.http.HttpSession;

@Controller
public class MutterController {

    @Autowired
    private MutterService mutterService;

    @GetMapping("/main")
    public String main(HttpSession session, Model model) {
        User loginUser = (User) session.getAttribute("loginUser");

        if (loginUser == null) {
            return "redirect:/";
        }

        List<Mutter> mutterList = mutterService.findAll();
        model.addAttribute("mutterList", mutterList);

        return "main";
    }

    @PostMapping("/main")
    public String postMutter(
            @RequestParam("text") String text,
            HttpSession session,
            Model model) {

        User loginUser = (User) session.getAttribute("loginUser");
        if (loginUser == null) {
            return "redirect:/";
        }

        if (text != null && !text.isEmpty()) {
            Mutter mutter = new Mutter(loginUser.getName(), text);
            mutterService.create(mutter);
        } else {
            model.addAttribute("errorMsg", "つぶやきが入力されていません");
            // エラー時はそのまま画面再表示（リストも再取得必要）
            List<Mutter> mutterList = mutterService.findAll();
            model.addAttribute("mutterList", mutterList);
            return "main";
        }

        // 成功時はRedirect (PRGパターン)
        return "redirect:/main";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "logout";
    }
}

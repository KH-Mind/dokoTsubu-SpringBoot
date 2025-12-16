package com.example.dokotsubu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.dokotsubu.form.PostMutterForm;
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

        // フォームの初期化
        model.addAttribute("postMutterForm", new PostMutterForm());

        return "main";
    }

    @PostMapping("/main")
    public String postMutter(
            @Validated @ModelAttribute("postMutterForm") PostMutterForm form,
            BindingResult result,
            HttpSession session,
            Model model) {

        User loginUser = (User) session.getAttribute("loginUser");
        if (loginUser == null) {
            return "redirect:/";
        }

        if (result.hasErrors()) {
            List<Mutter> mutterList = mutterService.findAll();
            model.addAttribute("mutterList", mutterList);
            return "main";
        }

        Mutter mutter = new Mutter(loginUser.getName(), form.getText());
        mutterService.create(mutter);

        // 成功時はRedirect (PRGパターン)
        return "redirect:/main";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "logout";
    }

    @PostMapping("/main/delete/{id}")
    public String deleteMutter(@org.springframework.web.bind.annotation.PathVariable Integer id, HttpSession session) {
        User loginUser = (User) session.getAttribute("loginUser");

        if (loginUser == null) {
            return "redirect:/";
        }

        // サービスに削除を依頼（所有者チェックはサービス内で行う）
        mutterService.delete(id, loginUser.getName());

        return "redirect:/main";
    }
}

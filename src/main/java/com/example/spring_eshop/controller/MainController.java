package com.example.spring_eshop.controller;

import com.example.spring_eshop.service.SessionObjectHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.UUID;

@Controller
public class MainController {

    private final SessionObjectHolder sessionObjectHolder;

    public MainController(SessionObjectHolder sessionObjectHolder) {
        this.sessionObjectHolder = sessionObjectHolder;
    }

    @RequestMapping({"", "/"})
    public String index(Model model, HttpSession httpSession){
        model.addAttribute("amountClicks", sessionObjectHolder.getAmountClicks());
        if (httpSession.getAttribute("my 10") == null){
            String uuid = UUID.randomUUID().toString();
            httpSession.setAttribute("my10", uuid);
            System.out.println("Gtntrated UUID -->" + uuid);
        }
        return "index";
    }

    @RequestMapping("/login")
    public String login(){
        return "login";
    }

    @RequestMapping("/login:error")
    public String loginError(Model model){
        model.addAttribute("/login:error", true);
        return "login";
    }

}

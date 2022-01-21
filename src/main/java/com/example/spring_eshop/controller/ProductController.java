package com.example.spring_eshop.controller;

import com.example.spring_eshop.dto.ProductDto;
import com.example.spring_eshop.service.ProductService;
import com.example.spring_eshop.service.SessionObjectHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;
    private final SessionObjectHolder sessionObjectHolder;

    public ProductController(ProductService productService, SessionObjectHolder sessionObjectHolder) {
        this.productService = productService;
        this.sessionObjectHolder = sessionObjectHolder;
    }

    @GetMapping
    public String list(Model model) {
        sessionObjectHolder.addClicks();
        List<ProductDto> list = productService.getAll();
        model.addAttribute("products", list);
        return "products";
    }

    @PostMapping("/{id}/bucket")
    public String addBucket(@PathVariable Long id, Principal principal){
        sessionObjectHolder.addClicks();
        if (principal == null){
            return "rederect:/products";
        }
        productService.addToUserBucket(id, principal.getName());
        return "rederect:/products";
    }
}
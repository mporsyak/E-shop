package com.example.spring_eshop.controller;

import com.example.spring_eshop.dto.ProductDto;
import com.example.spring_eshop.service.ProductService;
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

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public String list(Model model) {
        List<ProductDto> list = productService.getAll();
        model.addAttribute("products", list);
        return "products";
    }

    @PostMapping("/{id}/bucket")
    public String addBucket(@PathVariable Long id, Principal principal){
        if (principal == null){
            return "rederect:/products";
        }
        productService.addToUserBucket(id, principal.getName());
        return "rederect:/products";
    }
}
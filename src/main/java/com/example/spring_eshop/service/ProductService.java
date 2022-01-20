package com.example.spring_eshop.service;

import com.example.spring_eshop.dto.ProductDto;

import java.util.List;

public interface ProductService {
    List<ProductDto> getAll();
    void addToUserBucket(Long productId, String username);
}

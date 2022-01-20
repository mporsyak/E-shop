package com.example.spring_eshop.service;

import com.example.spring_eshop.dao.ProductRepository;
import com.example.spring_eshop.domain.User;
import com.example.spring_eshop.dto.ProductDto;
import com.example.spring_eshop.mapper.ProductMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService{

    private final ProductMapper mapper = ProductMapper.MAPPER;

    private final ProductRepository productRepository;
    private final UserService userService;
    private final BucketService bucketService;

    public ProductServiceImpl(ProductRepository productRepository, UserService userService, BucketService bucketService) {
        this.productRepository = productRepository;
        this.userService = userService;
        this.bucketService = bucketService;
    }

    @Override
    public List<ProductDto> getAll() {
        return mapper.fromProductList(productRepository.findAll());
    }

    @Override
    public void addToUserBucket(Long productId, String username) {
        User user = userService.findByName(username);
        if (user == null){
            new RuntimeException("User not found - " + username);
        }


    }
}

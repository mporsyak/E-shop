package com.example.spring_eshop.service;

import com.example.spring_eshop.dao.BucketRepository;
import com.example.spring_eshop.dao.ProductRepository;
import com.example.spring_eshop.domain.Bucket;
import com.example.spring_eshop.domain.Product;
import com.example.spring_eshop.domain.User;
import com.example.spring_eshop.dto.BucketDetailDto;
import com.example.spring_eshop.dto.BucketDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class BucketServiceImpl implements BucketService{
    private final BucketRepository bucketRepository;
    private final ProductRepository productRepository;
    private final UserService userService;

    public BucketServiceImpl(BucketRepository bucketRepository, ProductRepository productRepository, UserService userService) {
        this.bucketRepository = bucketRepository;
        this.productRepository = productRepository;
        this.userService = userService;
    }

    @Override
    @Transactional
    public Bucket createBucket(User user, List<Long> productIds) {
        Bucket bucket = new Bucket();
        bucket.setUser(user);
        List<Product> productList = getCollectRefProductsByIds(productIds);
        bucket.setProducts(productList);
        return bucketRepository.save(bucket);
    }

    private List<Product> getCollectRefProductsByIds(List<Long> productIds) {
        return productIds.stream()
                .map(productRepository::getOne)
                .collect(Collectors.toList());
    }


    @Override
    public void addProduct(Bucket bucket, List<Long> productIds) {
        List<Product> products = bucket.getProducts();
        List<Product> newProductList = products == null ? new ArrayList<>() : new ArrayList<>(products);
        newProductList.addAll(getCollectRefProductsByIds(productIds));
        bucket.setProducts(newProductList);
        bucketRepository.save(bucket);
    }

    @Override
    public BucketDto getBucketByUser(String name) {
       User user = userService.findByName(name);
       if (user == null || user.getBacked() == null){
           return new BucketDto();
       }

       BucketDto bucketDto = new BucketDto();
       Map<Long, BucketDetailDto> mapByProductId = new HashMap<>();

       List<Product> products = user.getBacked().getProducts();
       for (Product product : products){
           BucketDetailDto detailDto = mapByProductId.get(product.getId());
           if (detailDto == null){
               mapByProductId.put(product.getId(), new BucketDetailDto(product));
           }else {
               detailDto.setAmount(detailDto.getAmount().add(new BigDecimal(1.0)));
               detailDto.setSum(detailDto.getSum() + Double.valueOf(product.getPrice().toString()));
           }
       }

        bucketDto.setBucketDetails(new ArrayList<>(mapByProductId.values()));
        bucketDto.aggregate();
        return bucketDto;
    }
}

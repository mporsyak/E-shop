package com.example.spring_eshop.service;

import com.example.spring_eshop.domain.Bucket;
import com.example.spring_eshop.domain.User;
import com.example.spring_eshop.dto.BucketDto;

import java.util.List;

public interface BucketService {
    Bucket createBucket(User user, List<Long> productIds);

    void addProduct(Bucket bucket, List<Long> productIds);

    BucketDto getBucketByUser(String name);
}

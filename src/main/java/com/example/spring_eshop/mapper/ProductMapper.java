package com.example.spring_eshop.mapper;

import com.example.spring_eshop.domain.Product;
import com.example.spring_eshop.dto.ProductDto;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ProductMapper {
    ProductMapper MAPPER = Mappers.getMapper(ProductMapper.class);
//    @Mappings({
//            @Mapping(source = "price", target = "seatCount"),
//            @Mapping(source = "manufacturingData", target = "manufacturingYear")
//    })

    Product toProduct(ProductDto productDto);

    @InheritInverseConfiguration
    ProductDto formProduct(Product product);

    List<Product> toProductList(List<ProductDto> productDTOS);

    List<ProductDto> fromProductList(List<Product> products);
}

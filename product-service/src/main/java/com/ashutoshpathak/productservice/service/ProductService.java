package com.ashutoshpathak.productservice.service;

import com.ashutoshpathak.productservice.dto.ProductRequest;
import com.ashutoshpathak.productservice.dto.ProductResponse;
import com.ashutoshpathak.productservice.model.Product;
import com.ashutoshpathak.productservice.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    public void createProduct(ProductRequest productRequest) {
        //Product product = new Product();
        Product product = Product.builder()
                .name(productRequest.getName())
                .description(productRequest.getDescription())
                .price(productRequest.getPrice())
                .build();

        productRepository.save(product);
        log.info( "Product {} is saved",product.getId());
    }

    public List<ProductResponse> getAllProducts() {

        List<Product> allProducts = productRepository.findAll();
        return allProducts
                .stream()
                .map(this::mapToDTO)
                .toList() ;
    }

    public ProductResponse mapToDTO(Product product){
        return ProductResponse.builder()
                .id(product.getId())
                .description(product.getDescription())
                .name(product.getName())
                .price(product.getPrice())
                .build();
    }
}

package com.ashutoshpathak.productservice.controller;

import com.ashutoshpathak.productservice.dto.ProductRequest;
import com.ashutoshpathak.productservice.dto.ProductResponse;
import com.ashutoshpathak.productservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    @Autowired
    ProductService productService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createProduct(@RequestBody ProductRequest productRequest){
        productService.createProduct(productRequest);
    }

//    @GetMapping("/{id}")
//    public ResponseEntity<ProductResponse> getProduct(){
//
//    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ProductResponse> getAllProducts(){
        return productService.getAllProducts();
    }

}

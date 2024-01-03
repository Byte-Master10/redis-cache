package com.redis.demo.controller;

import com.redis.demo.model.ProductInfoModel;
import com.redis.demo.repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductRepo productRepo;

    @PostMapping
    public ProductInfoModel save(@RequestBody ProductInfoModel productInfoModel){
        return productRepo.saveProduct(productInfoModel);
    }

    @GetMapping
    public List<ProductInfoModel> getAll(){
        return productRepo.getProduct();
    }

    @GetMapping("/{id}")
    @Cacheable(key = "#id" , value = "ProductInfoModel")
    public ProductInfoModel getById(@PathVariable int id){
        return productRepo.findProductById(id);
    }

    @DeleteMapping("/{id}")
    @CacheEvict(key = "#id" , value = "ProductInfoModel")
    public String deleteById(@PathVariable int id){
        return productRepo.deleteProductById(id);
    }
}

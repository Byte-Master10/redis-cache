package com.redis.demo.repo;

import com.redis.demo.model.ProductInfoModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class ProductRepo {

    public static final String PRODUCT_HASH_KEY = "ProductInfoModel";

    @Autowired
    RedisTemplate redisTemplate;

    public ProductInfoModel saveProduct(ProductInfoModel productInfoModel) {
        redisTemplate.opsForHash().put(PRODUCT_HASH_KEY, productInfoModel.getId(), productInfoModel);
        return productInfoModel;
    }

    public List<ProductInfoModel> getProduct(){
        return redisTemplate.opsForHash().values(PRODUCT_HASH_KEY);
    }

    public ProductInfoModel findProductById(int id){
        return (ProductInfoModel) redisTemplate.opsForHash().get(PRODUCT_HASH_KEY, id);
    }

    public String deleteProductById(int id){
        boolean productExists = redisTemplate.opsForHash().hasKey(PRODUCT_HASH_KEY, id);
        if (productExists) {
            redisTemplate.opsForHash().delete(PRODUCT_HASH_KEY, id);
            return "Product removed";
        } else {
            return "Product not found in Redis";
        }
    }
}

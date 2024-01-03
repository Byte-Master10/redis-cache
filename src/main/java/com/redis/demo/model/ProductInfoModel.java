package com.redis.demo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@RedisHash("ProductInfoModel")
public class ProductInfoModel implements Serializable {
    @Id
    private int id;
    private String name;
    private double quantity;
    private double price;
}

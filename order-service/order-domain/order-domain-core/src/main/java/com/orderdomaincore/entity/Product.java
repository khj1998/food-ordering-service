package com.orderdomaincore.entity;

import com.commondomain.entity.BaseEntity;
import com.commondomain.vo.Money;
import com.commondomain.vo.ProductId;
import lombok.Getter;

@Getter
public class Product extends BaseEntity<ProductId> {
    private String name;
    private Money price;

    public Product(ProductId productId,String name,Money price) {
        super.setId(productId);
        this.name = name;
        this.price = price;
    }

    public void updateWithConfirmedNameAndPrice(String name,Money price) {
        this.name = name;
        this.price = price;
    }
}

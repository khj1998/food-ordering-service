package com.orderdomaincore.entity;

import com.commondomain.entity.AggregateRoot;
import com.commondomain.vo.RestaurantId;

import java.util.List;

public class Restaurant extends AggregateRoot<RestaurantId> {
    private boolean active;
    private final List<Product> productList;

    private Restaurant(Builder builder) {
        super.setId(builder.id);
        active = builder.active;
        productList = builder.productList;
    }


    public static final class Builder {
        private RestaurantId id;
        private boolean active;
        private List<Product> productList;

        private Builder() {
        }

        public static Builder builder() {
            return new Builder();
        }

        public Builder id(RestaurantId val) {
            id = val;
            return this;
        }

        public Builder active(boolean val) {
            active = val;
            return this;
        }

        public Builder productList(List<Product> val) {
            productList = val;
            return this;
        }

        public Restaurant build() {
            return new Restaurant(this);
        }
    }
}

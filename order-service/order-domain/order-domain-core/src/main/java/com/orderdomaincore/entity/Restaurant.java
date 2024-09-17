package com.orderdomaincore.entity;

import com.commondomain.entity.AggregateRoot;
import com.commondomain.vo.RestaurantId;
import lombok.Getter;

import java.util.List;
import java.util.UUID;

@Getter
public class Restaurant extends AggregateRoot<RestaurantId> {
    private boolean active;
    private final List<Product> productList;

    private Restaurant(Builder builder) {
        super.setId(builder.restaurantId);
        active = builder.active;
        productList = builder.productList;
    }

    public boolean isActive() {
        return this.active;
    }

    public static final class Builder {
        private RestaurantId restaurantId;
        private boolean active;
        private List<Product> productList;

        private Builder() {
        }

        public static Builder builder() {
            return new Builder();
        }

        public Builder restaurantId(RestaurantId val) {
            restaurantId = val;
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

package com.orderdomaincore.vo;

import lombok.Builder;
import lombok.Getter;

import java.util.Locale;
import java.util.Objects;
import java.util.UUID;

@Getter
public class StreetAddress {
    private final UUID id;
    private final String street;
    private final String postalCode;
    private final String city;

    @Builder
    public StreetAddress(UUID id,String street,String postalCode,String city) {
        this.id = id;
        this.street = street;
        this.postalCode = postalCode;
        this.city = city;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        StreetAddress that = (StreetAddress) obj;
        return street.equals(that.street) && postalCode.equals(that.postalCode) && city.equals(that.city);
    }

    @Override
    public int hashCode() {
        return Objects.hash(street,postalCode,city);
    }
}

package com.commondomain.entity;

import lombok.Getter;

import java.util.Objects;

@Getter
public abstract class BaseEntity<ID> {
    private ID id;

    public void setId(ID id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        BaseEntity<?> that = (BaseEntity<?>) obj;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

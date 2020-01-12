package com.store.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.math.BigDecimal;
import java.util.StringJoiner;

public final class Product {
    private final Long id;
    private final String name;
    private final Unit unit;
    private final BigDecimal price;

    public Product(Long id, String name, Unit unit, BigDecimal price) {
        this.id = id;
        this.name = name;
        this.unit = unit;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Unit getUnit() {
        return unit;
    }

    public BigDecimal getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ",  "[", "]")
                .add( name + "\t")
                .add("price=" + price)
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        return new EqualsBuilder()
                .append(id, product.id)
                .append(name, product.name)
                .append(unit, product.unit)
                .append(price, product.price)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(id)
                .append(name)
                .append(unit)
                .append(price)
                .toHashCode();
    }
}

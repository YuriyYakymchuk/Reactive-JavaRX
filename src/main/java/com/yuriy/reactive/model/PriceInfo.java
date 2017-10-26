package com.yuriy.reactive.model;

import lombok.Data;

public class PriceInfo {

    private final String name;
    private  final double price;

    public PriceInfo(String name, double price) {
        this.name = name;
        this.price = price;
    }

    @Override
    public String toString() {
        return "PriceInfo{" +
                "name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}

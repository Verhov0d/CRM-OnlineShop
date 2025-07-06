package com.example.bigsevaup.Model;

public class Statistic {
    private long kategory_product;
    private String name;

    public Statistic() {
    }

    public Statistic(long kategory_product, String name) {
        this.kategory_product = kategory_product;
        this.name = name;
    }

    public long getkategory_product() {
        return kategory_product;
    }

    public void setkategory_product(long kategory_product) {
        this.kategory_product = kategory_product;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

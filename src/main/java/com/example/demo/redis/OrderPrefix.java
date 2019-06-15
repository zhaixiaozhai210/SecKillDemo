package com.example.demo.redis;

public class OrderPrefix extends BasePrefix{

    private OrderPrefix(String orderPrefix) {
        super(orderPrefix);
    }

    public static OrderPrefix getById = new OrderPrefix("id");

    public static OrderPrefix getByName = new OrderPrefix("name");
}

package com.jpmc.theater.entity;

import org.apache.commons.lang3.RandomStringUtils;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class CustomerTest {

    public static Customer random() {
        Customer customer = new Customer(RandomStringUtils.randomAlphanumeric(9)
                , UUID.randomUUID());
        return customer;
    }
}
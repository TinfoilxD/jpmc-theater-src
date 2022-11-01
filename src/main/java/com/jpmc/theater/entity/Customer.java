package com.jpmc.theater.entity;

import java.util.Objects;
import java.util.UUID;

public class Customer {

    private String name;

    private UUID id;

    /**
     * @param name customer name
     * @param id customer id
     */
    public Customer(String name, UUID id) {
        this.id = id;
        this.name = name;
        }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "name: " + name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Objects.equals(name, customer.name) && Objects.equals(id, customer.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, id);
    }
}
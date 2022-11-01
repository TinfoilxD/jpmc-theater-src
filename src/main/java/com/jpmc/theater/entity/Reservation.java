package com.jpmc.theater.entity;

import com.jpmc.theater.discount.AppliedDiscount;

import java.util.UUID;

public class Reservation {

    private UUID id;
    private Customer customer;
    private Showing showing;
    private int audienceCount;

    private double ticketPrice;

    private AppliedDiscount discount;


    public Reservation() {
    }

    public Reservation(Customer customer, Showing showing, int audienceCount) {
        this.customer = customer;
        this.showing = showing;
        this.audienceCount = audienceCount;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Showing getShowing() {
        return showing;
    }

    public void setShowing(Showing showing) {
        this.showing = showing;
    }

    public int getAudienceCount() {
        return audienceCount;
    }

    public void setAudienceCount(int audienceCount) {
        this.audienceCount = audienceCount;
    }

    public double getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(double ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public AppliedDiscount getDiscount() {
        return discount;
    }

    public void setDiscount(AppliedDiscount discount) {
        this.discount = discount;
    }
}
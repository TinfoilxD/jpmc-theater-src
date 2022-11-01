package com.jpmc.theater.entity;

import java.time.LocalDateTime;
import java.util.UUID;

public class Showing {

    private UUID id;
    private Movie movie;

    private double baseTicketPrice;
    private int sequenceOfTheDay;
    private LocalDateTime showStartTime;

    public Showing() {
    }
    public Showing(Movie movie, int sequenceOfTheDay, LocalDateTime showStartTime) {
        this.movie = movie;
        this.sequenceOfTheDay = sequenceOfTheDay;
        this.showStartTime = showStartTime;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public LocalDateTime getShowStartTime() {
        return showStartTime;
    }

    public LocalDateTime getStartTime() {
        return showStartTime;
    }

    public void setShowStartTime(LocalDateTime showStartTime) {
        this.showStartTime = showStartTime;
    }

    public boolean isSequence(int sequence) {
        return this.sequenceOfTheDay == sequence;
    }

    public void setSequenceOfTheDay(int sequenceOfTheDay) {
        this.sequenceOfTheDay = sequenceOfTheDay;
    }

    public int getSequenceOfTheDay() {
        return sequenceOfTheDay;
    }

    public double getBaseTicketPrice() {
        return baseTicketPrice;
    }

    public void setBaseTicketPrice(double baseTicketPrice) {
        this.baseTicketPrice = baseTicketPrice;
    }
}

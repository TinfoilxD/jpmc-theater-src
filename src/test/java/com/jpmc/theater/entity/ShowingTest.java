package com.jpmc.theater.entity;

import java.time.LocalDateTime;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class ShowingTest {

    public static Showing random() {
        Showing showing = new Showing();
        showing.setMovie(MovieTest.random());
        showing.setSequenceOfTheDay(new Random().nextInt(10));
        int hour = new Random().nextInt(12) + 7;
        showing.setShowStartTime(LocalDateTime.now().withHour(hour));
        double ticketPrice = new Random().nextDouble() * 10.00 + 5;
        showing.setBaseTicketPrice(ticketPrice);
        return showing;
    }

}
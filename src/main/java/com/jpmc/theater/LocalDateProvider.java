package com.jpmc.theater;

import org.springframework.stereotype.Component;

import java.time.LocalDate;


@Component
public class LocalDateProvider implements DateProvider {
    private static LocalDateProvider instance = null;

    public static LocalDateProvider singleton() {
        if (instance == null) {
            instance = new LocalDateProvider();
        }
            return instance;
        }

    public LocalDate currentDate() {
            return LocalDate.now();
    }
}

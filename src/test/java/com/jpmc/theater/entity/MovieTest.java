package com.jpmc.theater.entity;

import com.jpmc.theater.MovieCode;

import java.time.Duration;
import java.util.Random;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric;
import static org.apache.commons.lang3.RandomStringUtils.randomNumeric;

public class MovieTest {

    public static Movie random() {
        Movie movie = new Movie();
        movie.setTitle(randomAlphanumeric(10));
        movie.setSpecialCode(MovieCode.BASIC);
        movie.setDescription(randomAlphanumeric(10));
        long minutes = new Random().nextInt(180);
        movie.setRunningTime(Duration.ofMinutes(minutes));
        return movie;
    }

}
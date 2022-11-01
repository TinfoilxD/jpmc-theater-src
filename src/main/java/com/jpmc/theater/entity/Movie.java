package com.jpmc.theater.entity;

import com.jpmc.theater.MovieCode;

import java.time.Duration;
import java.util.Objects;
import java.util.UUID;

public class Movie {
    private static int MOVIE_CODE_SPECIAL = 1;


    private UUID id;
    private String title;
    private String description;
    private Duration runningTime;
    private MovieCode specialCode;

    public Movie() {
    }

    public Movie(String title, Duration runningTime, double ticketPrice, MovieCode specialCode) {
        this.title = title;
        this.runningTime = runningTime;
        this.specialCode = specialCode;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public static int getMovieCodeSpecial() {
        return MOVIE_CODE_SPECIAL;
    }

    public static void setMovieCodeSpecial(int movieCodeSpecial) {
        MOVIE_CODE_SPECIAL = movieCodeSpecial;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Duration getRunningTime() {
        return runningTime;
    }

    public void setRunningTime(Duration runningTime) {
        this.runningTime = runningTime;
    }

    public MovieCode getSpecialCode() {
        return specialCode;
    }

    public void setSpecialCode(MovieCode specialCode) {
        this.specialCode = specialCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return Objects.equals(title, movie.title)
                && Objects.equals(description, movie.description)
                && Objects.equals(runningTime, movie.runningTime)
                && specialCode == movie.specialCode;
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, description, runningTime, specialCode);
    }
}
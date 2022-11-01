package com.jpmc.theater.entity;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class MovieRepository {

    List<Movie> movieList;

    public MovieRepository() {
        this.movieList = new ArrayList<>();
    }

    public UUID save (Movie movie){
        if(movie.getId() == null)
            movie.setId(UUID.randomUUID());
        movieList.add(movie);
        return movie.getId();
    }

    public Movie getById(UUID id) {
        return movieList.stream()
                .filter(i -> i.getId().equals(id))
                .findFirst()
                .get();
    }
}

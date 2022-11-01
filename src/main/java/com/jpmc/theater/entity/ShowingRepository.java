package com.jpmc.theater.entity;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class ShowingRepository {

    private List<Showing> showingList;

    public ShowingRepository() {
        this.showingList = new ArrayList<>();
    }

    public UUID save (Showing showing){
        if(showing.getId() == null)
            showing.setId(UUID.randomUUID());
        showingList.add(showing);
        return showing.getId();
    }

    public Showing getById(UUID id) {
        return showingList.stream()
                .filter(i -> i.getId().equals(id))
                .findFirst()
                .get();
    }

    public List<Showing> getAll() {
        return showingList;
    }

    public boolean hasId(UUID id) {
        return showingList.stream()
                .anyMatch(i -> i.getId().equals(id));
    }
}

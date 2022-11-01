package com.jpmc.theater.entity;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class ReservationRepository {

    private List<Reservation> reservationList;

    public ReservationRepository() {
        this.reservationList = new ArrayList<>();
    }

    public UUID save (Reservation reservation){
        if(reservation.getId() == null)
            reservation.setId(UUID.randomUUID());
        reservationList.add(reservation);
        return reservation.getId();
    }

    public Reservation getById(UUID id) {
        return reservationList.stream()
                .filter(i -> i.getId().equals(id))
                .findFirst()
                .get();
    }
}

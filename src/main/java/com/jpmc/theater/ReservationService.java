package com.jpmc.theater;

import com.jpmc.theater.discount.AppliedDiscount;
import com.jpmc.theater.discount.Discount;
import com.jpmc.theater.discount.DiscountService;
import com.jpmc.theater.entity.Customer;
import com.jpmc.theater.entity.Reservation;
import com.jpmc.theater.entity.ReservationRepository;
import com.jpmc.theater.entity.Showing;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ReservationService {

    private DiscountService discountService;
    private ReservationRepository reservationRepository;

    private ShowingService showingService;

    public ReservationService(DiscountService discountService, ReservationRepository reservationRepository, ShowingService showingService) {
        this.discountService = discountService;
        this.reservationRepository = reservationRepository;
        this.showingService = showingService;
    }

    public UUID makeReservation(Customer customer, Showing showing, int audienceCount) throws IllegalArgumentException {

        if(!showingService.isValidShowing(showing))
            throw new IllegalArgumentException("Not a valid showing");
        if(audienceCount < 1)
            throw new IllegalArgumentException("Need at least one audience count to make a reservation");

        Reservation reservation = new Reservation();
        reservation.setCustomer(customer);
        reservation.setShowing(showing);
        reservation.setAudienceCount(audienceCount);
        AppliedDiscount discount = discountService.getDiscount(showing);
        reservation.setDiscount(discount);
        reservation.setTicketPrice(showing.getBaseTicketPrice() - discount.getDiscountAmount());
        return reservationRepository.save(reservation);
    }
}

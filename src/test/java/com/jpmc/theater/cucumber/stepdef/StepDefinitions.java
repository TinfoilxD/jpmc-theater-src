package com.jpmc.theater.cucumber.stepdef;


import com.jpmc.theater.DateProvider;
import com.jpmc.theater.MovieCode;
import com.jpmc.theater.ReservationService;
import com.jpmc.theater.cucumber.TestContext;
import com.jpmc.theater.discount.DiscountCode;
import com.jpmc.theater.entity.*;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;

public class StepDefinitions {

    @Autowired
    TestContext testContext;
    @Autowired
    ReservationService reservationService;
    @Autowired
    ReservationRepository reservationRepository;
    @Autowired
    MovieRepository movieRepository;
    @Autowired
    ShowingRepository showingRepository;

    @Mock
    DateProvider dateProvider;

    private static final String CUSTOMER_KEY = "customer";
    private static final String MOVIE_ID_KEY = "movie_id";

    private static final String RESERVATION_ID_KEY = "reservation_id";

    private static final String SHOWING_ID_KEY = "showing_id";

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        given(dateProvider.currentDate()).willReturn( LocalDate.now());

    }

    @Given("I am a random customer")
    public void randomCustomer() {
        testContext.getContext().put(CUSTOMER_KEY, CustomerTest.random());
    }

    @Given("the current date is {string}")
    public void currentDate(String dateString) {
        LocalDate currentDate = LocalDate.parse(dateString, DateTimeFormatter.ofPattern("MM-dd-yyyy"));
        given(dateProvider.currentDate()).willReturn(currentDate);
    }

    @Given("^the movie is doing its (\\d+)(?:st|nd|rd|th) showing at (\\d+)$")
    public void withAShowing(int sequence, int hour) {
        Movie movie = movieRepository.getById((UUID)testContext.getContext().get(MOVIE_ID_KEY));
        Showing showing = ShowingTest.random();
        showing.setMovie(movie);
        showing.setSequenceOfTheDay(sequence);
        showing.setShowStartTime(dateProvider.currentDate().atTime(hour, 0));
        UUID id = showingRepository.save(showing);
        testContext.getContext().put(SHOWING_ID_KEY, id);
    }

    @When("I make a reservation of {int} person/people")
    public void makeAReservation(int numberOfPeople) {
        Showing showing = showingRepository.getById((UUID)testContext.getContext().get(SHOWING_ID_KEY));
        Customer customer = (Customer)testContext.getContext().get(CUSTOMER_KEY);
        UUID id = reservationService.makeReservation(customer, showing, numberOfPeople);
        testContext.getContext().put(RESERVATION_ID_KEY, id);
    }

    @When("^I make a reservation of (\\d+) (?:person|people) for the (\\d+)(?:st|nd|rd|th) showing at (\\d+)$")
    public void makeAReservation(int numberOfPeople, int sequence, int hour) {
        Movie movie = movieRepository.getById((UUID)testContext.getContext().get(MOVIE_ID_KEY));
        Showing showing = ShowingTest.random();
        showing.setMovie(movie);
        showing.setSequenceOfTheDay(sequence);
        showing.setShowStartTime(dateProvider.currentDate().atTime(hour, 0));
        Customer customer = (Customer)testContext.getContext().get(CUSTOMER_KEY);
        UUID id = reservationService.makeReservation(customer, showing, numberOfPeople);
        testContext.getContext().put(RESERVATION_ID_KEY, id);
    }

    @When("I want to see a special movie")
    public void specialMovie() {
        Movie movie = MovieTest.random();
        movie.setSpecialCode(MovieCode.SPECIAL);
        UUID id = movieRepository.save(movie);
        testContext.getContext().put(MOVIE_ID_KEY, id);
    }

    @When("I want to see a random normal movie")
    public void normalMovie() {
        Movie movie = MovieTest.random();
        movie.setSpecialCode(MovieCode.BASIC);
        UUID id = movieRepository.save(movie);
        testContext.getContext().put(MOVIE_ID_KEY, id);
    }

    @Then("the movie will be discounted at {int} percent off")
    public void discountedAt(int percentOff) {
        UUID id = (UUID)testContext.getContext().get(RESERVATION_ID_KEY);
        Reservation reservation = reservationRepository.getById(id);
        double expectedAmount = reservation.getShowing().getBaseTicketPrice() * ((double)percentOff * .01);
        assertThat(reservation.getDiscount().getDiscountAmount(), is(expectedAmount));
    }

    @Then("the movie will be discounted at flat rate of ${int} off")
    public void flatDiscount(int discount) {
        UUID id = (UUID)testContext.getContext().get(RESERVATION_ID_KEY);
        Reservation reservation = reservationRepository.getById(id);
        assertThat(reservation.getDiscount().getDiscountAmount(), is((double) discount));
    }

    @Then("the applied discount is {string}")
    public void appliedDiscount(String discountCode) {
        DiscountCode expectedCode = DiscountCode.getEnumByLabel(discountCode);
        UUID id = (UUID)testContext.getContext().get(RESERVATION_ID_KEY);
        Reservation reservation = reservationRepository.getById(id);
        assertThat(reservation.getDiscount().getCode(), is(expectedCode));
    }

}


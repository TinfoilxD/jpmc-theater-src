package com.jpmc.theater;

import com.jpmc.theater.entity.Showing;
import com.jpmc.theater.entity.ShowingRepository;
import com.jpmc.theater.entity.ShowingTest;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class ShowingServiceTest {

    @InjectMocks
    ShowingService service;

    @Mock
    ShowingRepository showingRepository;

    @Mock
    DateProvider dateProvider;

    @Test
    void getAllForCurrentDay_ReturnsShowingsForToday() {
        MockitoAnnotations.openMocks(this);
        List<Showing> mockList = new ArrayList<>();
        mockList.addAll(mockListOfPastShowings(4));
        mockList.addAll(mockListOfTodaysShowings(5));
        when(showingRepository.getAll()).thenReturn(mockList);
        when(dateProvider.currentDate()).thenReturn(LocalDate.now());
        List<Showing> actual = service.getAllForCurrentDay();
        assertThat(actual, hasSize(5));
    }

    List<Showing> mockListOfPastShowings(int length) {
        return IntStream.range(0,length)
                .mapToObj(i -> ShowingTest.random())
                .map(i -> {
                    LocalDateTime previous = i.getShowStartTime();
                    LocalDateTime replacement = previous.minusDays(new Random().nextInt(4) + 1);
                    i.setShowStartTime(replacement);
                    return i;
                })
                .collect(Collectors.toList());
    }

    List<Showing> mockListOfTodaysShowings(int length) {
        return IntStream.range(0, length)
                .mapToObj(i -> ShowingTest.random())
                .map(i -> {
                    i.setShowStartTime(LocalDateTime.now());
                    return i;
                })
                .collect(Collectors.toList());
    }
}
package com.jpmc.theater;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.jpmc.SpringConfiguration;
import com.jpmc.theater.entity.Showing;
import com.jpmc.theater.entity.ShowingTest;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class SchedulePrintServiceTest {

    SchedulePrintService service;
    @Mock
    DateProvider dateProvider;
    @Mock
    ShowingService showingService;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        List<Showing> mockShowing = IntStream.range(0, 10)
                .mapToObj(i -> ShowingTest.random())
                .collect(Collectors.toList());
        when(showingService.getAllForCurrentDay()).thenReturn(mockShowing);
        when(dateProvider.currentDate()).thenReturn(LocalDate.now());
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        service = new SchedulePrintService(dateProvider, showingService, objectMapper);
    }

    @Test
    void canPrintOutTextSchedule() {
        service.printSchedule();
    }

    @Test
    void canGetScheduleAsJsonString() throws JSONException {
        String jsonString = service.getCurrentDayShowingsJsonString();
        assertTrue(isValidJsonArray(jsonString));
    }

    public boolean isValidJsonArray(String json) {
        try {
            new JSONArray(json);
        } catch (JSONException e) {
            return false;
        }
        return true;
    }

}
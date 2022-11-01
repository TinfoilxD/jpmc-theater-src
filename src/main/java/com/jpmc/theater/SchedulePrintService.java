package com.jpmc.theater;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jpmc.theater.entity.Showing;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class SchedulePrintService {

    DateProvider dateProvider;

    ShowingService showingService;

    ObjectMapper mapper;

    public SchedulePrintService() {
    }

    public SchedulePrintService(DateProvider dateProvider, ShowingService showingService, ObjectMapper mapper) {
        this.dateProvider = dateProvider;
        this.showingService = showingService;
        this.mapper = mapper;
    }

    public void printSchedule() {
        List<Showing> showingList = showingService.getAllForCurrentDay();
        System.out.println(dateProvider.currentDate());
        System.out.println("===================================================");
        showingList.forEach(s ->
                System.out.println(s.getSequenceOfTheDay() + ": " + s.getStartTime() + " " + s.getMovie().getTitle() + " " + humanReadableFormat(s.getMovie().getRunningTime()) + " $" + s.getBaseTicketPrice())
        );
        System.out.println("===================================================");
    }

    public String humanReadableFormat(Duration duration) {
        long hour = duration.toHours();
        long remainingMin = duration.toMinutes() - TimeUnit.HOURS.toMinutes(duration.toHours());

        return String.format("(%s hour%s %s minute%s)", hour, handlePlural(hour), remainingMin, handlePlural(remainingMin));
    }

    public String getCurrentDayShowingsJsonString() {
        List<Showing> showingList = showingService.getAllForCurrentDay();
        try {
            String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(showingList);
            System.out.println(json);
            return json;
        } catch(Exception e) {
            e.printStackTrace();
        }
        throw new IllegalStateException("No Schedule Json Available");
    }

    private String handlePlural(long value) {
        if (value == 1) {
            return "";
        }
        else {
            return "s";
        }
    }
}

package com.jpmc.theater;

import com.jpmc.theater.entity.Showing;
import com.jpmc.theater.entity.ShowingRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ShowingService {

    ShowingRepository showingRepository;

    DateProvider dateProvider;

    public ShowingService(ShowingRepository showingRepository, DateProvider dateProvider) {
        this.showingRepository = showingRepository;
        this.dateProvider = dateProvider;
    }

    public List<Showing> getAllForCurrentDay() {
        return showingRepository.getAll().stream()
                .filter(i -> {
                    LocalDate currentDate = dateProvider.currentDate();
                    boolean isSameDay = i.getShowStartTime().getDayOfYear() == currentDate.getDayOfYear();
                    boolean isSameYear = i.getShowStartTime().getYear() == currentDate.getYear();
                    return isSameDay && isSameYear;
                })
                .collect(Collectors.toList());
    }

    public boolean isValidShowing(Showing showing) {
        return showingRepository.hasId(showing.getId());
    }
}

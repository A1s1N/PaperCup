package org.service.controllers;

import org.service.models.Day;
import org.service.models.Product;
import org.service.models.Timetable;
import org.service.repo.DayRepository;
import org.service.repo.ProductRepository;
import org.service.repo.TimetableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.Month;
import java.time.YearMonth;
import java.util.stream.StreamSupport;

@Controller
public class TimetableController {
    private final TimetableRepository timetableRepository;
    private final DayRepository dayRepository;

    @Autowired
    public TimetableController(TimetableRepository timetableRepository, DayRepository dayRepository) {
        this.timetableRepository = timetableRepository;
        this.dayRepository = dayRepository;
    }

    @GetMapping("/timetables")
    public String timetable(Model model) {
        YearMonth leapYearMonth = YearMonth.of(2024, Month.SEPTEMBER);
        int daysInLeapFeb = leapYearMonth.lengthOfMonth();

        Iterable<Day> days = dayRepository.findAll();
        if (StreamSupport.stream(days.spliterator(), false).findAny().isEmpty()) {
            for (int i = 0; i <= daysInLeapFeb; i++) {
                Day day = new Day("September", String.format("%02d", i));
                dayRepository.save(day);
            }
//            for (Day d : days) {
//
//            }
        }

        else
            model.addAttribute("days", days);
        return "timetables/timetables";
    }
}

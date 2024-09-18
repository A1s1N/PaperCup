package org.service.controllers;

import org.service.models.*;
import org.service.repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.text.DateFormat;
import java.time.Month;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.stream.StreamSupport;

@Controller
public class TimetableController {
    private final TimetableRepository timetableRepository;
    private final DayRepository dayRepository;
    private final DayOperationRepository dayOperationRepository;
    private final ProductRepository productRepository;
    private final ClientRepository clientRepository;
    private final OperationRepository operationRepository;

    @Autowired
    public TimetableController(TimetableRepository timetableRepository,
                               DayRepository dayRepository,
                               DayOperationRepository dayOperationRepository,
                               ProductRepository productRepository,
                               ClientRepository clientRepository,
                               OperationRepository operationRepository) {
        this.timetableRepository = timetableRepository;
        this.dayRepository = dayRepository;
        this.dayOperationRepository = dayOperationRepository;
        this.productRepository = productRepository;
        this.clientRepository = clientRepository;
        this.operationRepository = operationRepository;

    }

    @GetMapping("/timetables")
    public String timetable(Model model) {
        Calendar calendar = Calendar.getInstance();
        int month = calendar.get(Calendar.MONTH);
//        System.out.println(String.valueOf(month));
//
//        Date now = Calendar.getInstance().getTime();
//        String formattedDate = DateFormat.getDateInstance(DateFormat.FULL).format(now);
//        String[] splitDate = formattedDate.split(",");
//        System.out.println(splitDate[0]); // выводим первый элемент массива


        YearMonth leapYearMonth = YearMonth.of(2024, Month.of(month + 1));
        int daysInLeapFeb = leapYearMonth.lengthOfMonth();
        //System.out.println(daysInLeapFeb);

        Iterable<Day> days = dayRepository.findAll();
        Iterable<DayOperation> dayOperation = dayOperationRepository.findAll();
        Iterable<Product> products = productRepository.findAll();
        Iterable<Client> clients = clientRepository.findAll();
        Iterable<Operation> operations = operationRepository.findAll();

        if (StreamSupport.stream(days.spliterator(), false).findAny().isEmpty()) {
            for (int i = 0; i <= daysInLeapFeb; i++) {
                Day day = new Day("September", String.format("%02d", i));
                dayRepository.save(day);
            }


        }

        else {
            model.addAttribute("days", days);
            model.addAttribute("dayOperations", dayOperation);
            model.addAttribute("products", products);
            model.addAttribute("clients", clients);
            model.addAttribute("operations", operations);

        }
        return "timetables/timetables";
    }
}

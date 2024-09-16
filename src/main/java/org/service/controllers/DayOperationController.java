package org.service.controllers;

import org.service.repo.DayOperationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class DayOperationController {
    private final DayOperationRepository dayOperationRepository;

    @Autowired
    public DayOperationController(DayOperationRepository dayOperationRepository) {
        this.dayOperationRepository = dayOperationRepository;
    }
}

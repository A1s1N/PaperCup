package org.service.controllers;

import org.service.models.Client;
import org.service.models.Operation;
import org.service.models.Product;
import org.service.repo.OperationRepository;
import org.service.repo.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.StreamSupport;

@Controller
public class OperationController {
    private final OperationRepository operationRepository;

    @Autowired
    public OperationController(OperationRepository operationRepository) {
        this.operationRepository = operationRepository;
    }

    @GetMapping("/products/{id_product}/operations/{id_operation}/edit")
    public String operationEdit(@PathVariable(value = "id_product") long id_product,
                                 @PathVariable(value = "id_operation") long id_operation,
                                 Model model) {

        Optional <Operation> operation = operationRepository.findById(id_operation);

        ArrayList<Operation> operationList = new ArrayList<>();
        operation.ifPresent(operationList::add);
        model.addAttribute("operations", operationList);

        return "products/operations/operations-edit";
    }

    @PostMapping("/products/{id_product}/operations/{id}/edit")
    public String operationUpdate(@PathVariable(value = "id_product") long id_product,
                                  @PathVariable(value = "id") long id,
                                  @RequestParam String name,
                                  @RequestParam String orderliness,
                                  @RequestParam String operationTime,
                                  Model model) {

        Operation operation = operationRepository.findById(id).orElseThrow();
        operation.setName(name);
        operation.setOrderliness(orderliness);
        operation.setOperationTime(operationTime);
        operationRepository.save(operation);

        return "redirect:/products/{id_product}";
    }

    @PostMapping("/products/{id_product}/operations/{id_operation}/remove")
    public String operationDelete(@PathVariable(value = "id_product") long id_product,
                                  @PathVariable(value = "id_operation") long id_operation,
                                  Model model) {
        Operation operation = operationRepository.findById(id_operation).orElseThrow();
        operationRepository.delete(operation);
        return "redirect:/products/{id_product}";
    }




}


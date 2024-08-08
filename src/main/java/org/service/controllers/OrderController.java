package org.service.controllers;

import org.service.models.Doctor;
import org.service.models.Order;
import org.service.repo.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;
import java.util.stream.StreamSupport;

@Controller
public class OrderController {

    private final OrderRepository orderRepository;

    @Autowired
    public OrderController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @GetMapping("/orders")
    public String order(Model model) {
        Iterable<Order> orders = orderRepository.findAll();
        if(StreamSupport.stream(orders.spliterator(), false).findAny().isEmpty())
            model.addAttribute("message", "На данный момент заказы отсутствуют");
        else
            model.addAttribute("orders", orders);
        return "orders/orders";
    }

    @GetMapping("/orders/add")
    public String orderAdd(Model model) {
        return "orders/orders-add";
    }

    @PostMapping("/orders/add")
    public String ordersNewAdd(@RequestParam String name,
                               @RequestParam String deadline,
                               @RequestParam String createdDate,
                               @RequestParam String updatedDate,
                               @RequestParam Long productId,
                               @RequestParam Long clientId,
                               @RequestParam String totalDate,
                               @RequestParam Long countProducts,
                               @RequestParam double totalWeight,
                               Model model) {

        Order order = new Order(name, deadline, createdDate, updatedDate, productId, clientId, totalDate, countProducts, totalWeight);
        orderRepository.save(order);
        return "redirect:/orders";
    }

    @GetMapping("/orders/{id}")
    public String orderDetails(@PathVariable(value = "id") Long id, Model model) {
        if(!orderRepository.existsById(id))
            return "redirect:/orders";

        Optional<Order> order = orderRepository.findById(id);
        ArrayList<Order> res = new ArrayList<>();
        order.ifPresent(res::add);
        model.addAttribute("order", res);
        return "orders/orders-details";
    }

    @GetMapping("/orders/{id}/edit")
    public String orderEdit(@PathVariable(value = "id") long id, Model model) {
        if(!orderRepository.existsById(id))
            return "redirect:/orders";

        Optional<Order> order = orderRepository.findById(id);
        ArrayList<Order> res = new ArrayList<>();
        order.ifPresent(res::add);
        model.addAttribute("order", res);
        return "orders/orders-edit";
    }

    @PostMapping("/orders/{id}/edit")
    public String orderUpdate(@PathVariable(value = "id") long id,
                              @RequestParam String name,
                              @RequestParam String deadline,
                              @RequestParam String createdDate,
                              @RequestParam String updatedDate,
                              @RequestParam Long productId,
                              @RequestParam Long clientId,
                              @RequestParam String totalDate,
                              @RequestParam Long countProducts,
                              @RequestParam double totalWeight,
                              Model model) {
        Order order = orderRepository.findById(id).orElseThrow();
        order.setName(name);
        order.setDeadline(deadline);
        order.setCreatedDate(createdDate);
        order.setUpdatedDate(updatedDate);
        order.setProductId(productId);
        order.setClientId(clientId);
        order.setTotalDate(totalDate);
        order.setCountProducts(countProducts);
        order.setTotalWeight(totalWeight);
        orderRepository.save(order);
        return "redirect:/orders";
    }

    @PostMapping("/orders/{id}/remove")
    public String orderDelete(@PathVariable(value = "id") long id, Model model) {
        Order order = orderRepository.findById(id).orElseThrow();
        orderRepository.delete(order);
        return "redirect:/orders";
    }

}

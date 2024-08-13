package org.service.controllers;

import org.service.models.Client;
import org.service.models.Doctor;
import org.service.models.Order;
import org.service.models.Product;
import org.service.repo.ClientRepository;
import org.service.repo.OrderRepository;
import org.service.repo.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

//import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.StreamSupport;

@Controller
public class OrderController {

    private final OrderRepository orderRepository;
    private final ClientRepository clientRepository;
    private final ProductRepository productRepository;

    @Autowired
    public OrderController(OrderRepository orderRepository, ClientRepository clientRepository, ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.clientRepository = clientRepository;
        this.productRepository = productRepository;
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
        Iterable<Product> products = productRepository.findAll();
        Iterable<Client> clients = clientRepository.findAll();
        model.addAttribute("products", products);
        model.addAttribute("clients", clients);
        return "orders/orders-add";
    }

    @PostMapping("/orders/add")
    public String ordersNewAdd(@RequestParam String name,
                               @RequestParam String deadline,
                               //@RequestParam String createdDate,
                               //@RequestParam String updatedDate,
                               @RequestParam Long productId,
                               @RequestParam Long clientId,
                               @RequestParam String totalDate,
                               @RequestParam Long countProducts,
                               @RequestParam double totalWeight,
                               Model model) {

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("uuuu-MM-dd 'в' HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();

        String createdDate = now.format(dtf);
        String updatedDate = createdDate;

        Order order = new Order(name, deadline, createdDate, updatedDate, productId, clientId, totalDate , countProducts, totalWeight);
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
                              //@RequestParam String createdDate,
                              //@RequestParam String updatedDate,
                              @RequestParam Long productId,
                              @RequestParam Long clientId,
                              @RequestParam String totalDate,
                              @RequestParam Long countProducts,
                              @RequestParam double totalWeight,
                              Model model) {

        Date today = new Date();
        today.setHours(0);

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("uuuu-MM-dd 'в' HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String updatedDate = now.format(dtf);


        Order order = orderRepository.findById(id).orElseThrow();
        order.setName(name);
        order.setDeadline(deadline);
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

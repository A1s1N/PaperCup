package org.service.controllers;

import org.service.models.Product;
import org.service.repo.ProductRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.service.models.Client;
import org.service.repo.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.StreamSupport;


@Controller
public class ClientController {

    private final ClientRepository clientRepository;
    private final ProductRepository productRepository;

    @Autowired
    public ClientController(ClientRepository clientRepository, ProductRepository productRepository) {
        this.clientRepository = clientRepository;
        this.productRepository = productRepository;
    }

    @GetMapping("/clients")
    public String clients(Model model) {
        Iterable<Client> clients = clientRepository.findAll();
        if (StreamSupport.stream(clients.spliterator(), false).findAny().isEmpty())
            model.addAttribute("message", "На данный момент клиенты отсутствуют");
        else {
            //Optional<Product> product = productRepository.findById(с);
            //ArrayList<Product> res = new ArrayList<>();
           // product.ifPresent(res::add);
            model.addAttribute("clients", clients);
          //  model.addAttribute("product", product);
        }
        return "clients/clients";
    }

    @GetMapping("/clients/add")
    public String clientsAdd(Model model) {
        Iterable<Product> products = productRepository.findAll();
        model.addAttribute("products", products);
        return "clients/clients-add";
    }

    @PostMapping("/clients/add")
    public String clientsNewAdd(@RequestParam String name,
                                @RequestParam long productId,
                                Model model) {
        Client client = new Client(name, productId);
        clientRepository.save(client);
        return "redirect:/clients";
    }

    @GetMapping("/clients/{id}")
    public String clientDetails(@PathVariable(value = "id") Long id, Model model) {
        if(!clientRepository.existsById(id))
            return "redirect:/clients";

        Optional<Client> client = clientRepository.findById(id);
        ArrayList<Client> res = new ArrayList<>();
        client.ifPresent(res::add);
        model.addAttribute("client", res);
        return "clients/clients-details";
    }

    @GetMapping("/clients/{id}/edit")
    public String clientEdit(@PathVariable(value = "id") long id, Model model) {
        if(!clientRepository.existsById(id))
            return "redirect:/clients";

        Optional<Client> client = clientRepository.findById(id);
        Iterable<Product> products = productRepository.findAll();
        ArrayList<Client> res = new ArrayList<>();
        client.ifPresent(res::add);
        model.addAttribute("client", res);
        model.addAttribute("products", products);
        return "clients/clients-edit";
    }

    @PostMapping("/clients/{id}/edit")
    public String clientUpdate(@PathVariable(value = "id") long id,
                               @RequestParam String name,
                               @RequestParam long productId,
                               Model model) {
        Client client = clientRepository.findById(id).orElseThrow();
        client.setName(name);
        client.setProductId(productId);
        clientRepository.save(client);
        return "redirect:/clients";
    }

    @PostMapping("/clients/{id}/remove")
    public String clientDelete(@PathVariable(value = "id") long id, Model model) {
        Client client = clientRepository.findById(id).orElseThrow();
        clientRepository.delete(client);
        return "redirect:/clients";
    }


}

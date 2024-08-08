package org.service.controllers;

import org.springframework.ui.Model;
import org.service.models.Client;
import org.service.repo.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.stream.StreamSupport;

@Controller
public class ClientController {
    private final ClientRepository clientRepository;

    @Autowired
    public ClientController(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @GetMapping("/clients")
    public String clients(Model model) {
        Iterable<Client> clients = clientRepository.findAll();
        if(StreamSupport.stream(clients.spliterator(), false).findAny().isEmpty())
            model.addAttribute("message", "На данный момент клиенты отсутствуют");
        else
            model.addAttribute("clients", clients);
        return "clients/clients";
    }

}

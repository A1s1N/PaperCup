package org.service.controllers;

import org.service.models.Doctor;
import org.springframework.ui.Model;
import org.service.models.Client;
import org.service.repo.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;
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
        if (StreamSupport.stream(clients.spliterator(), false).findAny().isEmpty())
            model.addAttribute("message", "На данный момент клиенты отсутствуют");
        else
            model.addAttribute("clients", clients);
        return "clients/clients";
    }

    @GetMapping("/clients/add")
    public String clientAdd(Model model) {
        return "clients/clients-add";
    }


    @PostMapping("/clients/add")
    public String clientNewAdd(@RequestParam String name,
                               @RequestParam String createdData,
                               @RequestParam String updatedData,
                               Model model) {
        System.out.println(createdData);
        System.out.println(updatedData.getClass().getName());
        Client client = new Client(name,createdData, updatedData);

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
        ArrayList<Client> res = new ArrayList<>();
        client.ifPresent(res::add);
        model.addAttribute("client", res);
        return "clients/clients-edit";
    }

    @PostMapping("/clients/{id}/edit")
    public String clientUpdate(@PathVariable(value = "id") long id,
                               @RequestParam String name,
                               //@RequestParam String createdData,
                               //@RequestParam String updatedData,
                               Model model) {
        Client client = clientRepository.findById(id).orElseThrow();
        client.setName(name);
       // client.setUpdatedData(updatedData);
       // client.setCreatedData(createdData);
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

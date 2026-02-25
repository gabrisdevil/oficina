package com.santana.controller;

import com.santana.model.CustomerRequest;
import com.santana.model.ServiceItem;
import com.santana.repository.CustomerRequestRepository;
import com.santana.repository.ServiceItemRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final ServiceItemRepository serviceRepo;
    private final CustomerRequestRepository requestRepo;

    public AdminController(ServiceItemRepository serviceRepo, CustomerRequestRepository requestRepo) {
        this.serviceRepo = serviceRepo;
        this.requestRepo = requestRepo;
    }

    @GetMapping
    public String dashboard() {
        return "admin/dashboard";
    }

    @GetMapping("/login")
    public String login() {
        return "admin/login";
    }

    @GetMapping("/services")
    public String listServices(Model model) {
        model.addAttribute("services", serviceRepo.findAll());
        return "admin/services";
    }

    @PostMapping("/services/add")
    public String addService(@RequestParam String name,
                             @RequestParam String description,
                             @RequestParam String category,
                             @RequestParam Double price) {
        ServiceItem s = new ServiceItem(name, description, category, price);
        serviceRepo.save(s);
        return "redirect:/admin/services";
    }

    @GetMapping("/requests")
    public String listRequests(Model model) {
        model.addAttribute("requests", requestRepo.findAll());
        return "admin/requests";
    }

    @PostMapping("/requests/{id}/status")
    public String updateStatus(@PathVariable Long id, @RequestParam String status) {
        CustomerRequest req = requestRepo.findById(id).orElse(null);
        if (req != null) {
            req.setStatus(status);
            requestRepo.save(req);
        }
        return "redirect:/admin/requests";
    }
}

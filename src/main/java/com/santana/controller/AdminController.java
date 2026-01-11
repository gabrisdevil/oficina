package com.santana.controller;

import com.santana.model.ServiceItem;
import com.santana.repository.ServiceItemRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final ServiceItemRepository serviceRepo;

    public AdminController(ServiceItemRepository serviceRepo) {
        this.serviceRepo = serviceRepo;
    }

    @GetMapping
    public String dashboard() {
        return "admin/dashboard";
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
}

package com.santana.controller;

import com.santana.repository.ServiceItemRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    private final ServiceItemRepository serviceRepo;

    public HomeController(ServiceItemRepository serviceRepo) {
        this.serviceRepo = serviceRepo;
    }

    @GetMapping({"/", "/home"})
    public String index(Model model) {
        model.addAttribute("services", serviceRepo.findAll());
        model.addAttribute("businessName", "Santana Auto Mecanica");
        model.addAttribute("history", "Fundada em 1975, a Santana Auto Mecânica atende gerações de clientes com compromisso\n de qualidade e confiança. Especializada em manutenção mecânica tradicional — não trabalhamos com parte elétrica nem serviços para veículos 4x4 ou preparações off-road.");
        return "index";
    }

    @GetMapping("/services")
    public String services(Model model) {
        model.addAttribute("services", serviceRepo.findAll());
        return "services";
    }
}

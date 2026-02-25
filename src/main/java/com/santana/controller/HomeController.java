package com.santana.controller;

import com.santana.model.CustomerRequest;
import com.santana.repository.CustomerRequestRepository;
import com.santana.repository.ServiceItemRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.santana.model.ServiceItem;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class HomeController {

    private final ServiceItemRepository serviceRepo;
    private final CustomerRequestRepository requestRepo;

    public HomeController(ServiceItemRepository serviceRepo, CustomerRequestRepository requestRepo) {
        this.serviceRepo = serviceRepo;
        this.requestRepo = requestRepo;
    }

    @GetMapping({"/", "/home"})
    public String index(Model model) {
        List<ServiceItem> allServices = serviceRepo.findAll();
        model.addAttribute("services", allServices);
        model.addAttribute("mainServices", allServices.stream().limit(4).collect(Collectors.toList()));
        model.addAttribute("businessName", "Santana Auto Mecanica");
        model.addAttribute("history", "Fundada em 1975, a Santana Auto Mecânica atende gerações de clientes com compromisso de qualidade e confiança. Especializada em manutenção mecânica tradicional.");
        return "index";
    }

    @GetMapping("/services")
    public String services(Model model) {
        model.addAttribute("services", serviceRepo.findAll());
        return "services";
    }

    @GetMapping("/cadastro")
    public String cadastro(Model model) {
        model.addAttribute("services", serviceRepo.findAll());
        return "cadastro";
    }

    @PostMapping("/cadastro")
    public String cadastrar(@RequestParam String nome,
                            @RequestParam String placa,
                            @RequestParam String telefone,
                            @RequestParam String modelo,
                            @RequestParam String servico,
                            @RequestParam(required = false) String servicoOutro,
                            Model model) {
        String detalhes = null;
        if (servicoOutro != null && !servicoOutro.isBlank()) {
            detalhes = servicoOutro;
        }
        CustomerRequest req = new CustomerRequest(nome, placa, telefone, modelo, servico, detalhes);
        requestRepo.save(req);
        model.addAttribute("success", true);
        model.addAttribute("protocol", req.getId());
        model.addAttribute("services", serviceRepo.findAll());
        return "cadastro";
    }

    @GetMapping("/historia")
    public String historia() {
        return "historia";
    }
}

package com.example.bigsevaup.Controller;

import com.example.bigsevaup.Model.*;
import com.example.bigsevaup.Repository.CheckRepository;
import com.example.bigsevaup.Repository.ServiceCheckRepository;
import com.example.bigsevaup.Repository.UslugaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Optional;

@Controller
@RequestMapping("/uslugacheck")
@PreAuthorize("hasAuthority('ADMIN') OR hasAuthority('USER')")
public class ServiceCheckController {
    @Autowired
    ServiceCheckRepository serviceCheckRepository;
    @Autowired
    UslugaRepository uslugaRepository;
    @Autowired
    CheckRepository checkRepository;

    @GetMapping("")
    public String zooMain(Model model) {
        Iterable<ServiceCheck> listUslugaCheck = serviceCheckRepository.findAll();
        model.addAttribute("listServiceCheck", listUslugaCheck);
        return "uslugacheck/index";
    }

    @GetMapping("/add")
    public String zooAddView(ServiceCheck serviceCheck, Model model) {
        Iterable<Usluga> uslugas = uslugaRepository.findAll();
        model.addAttribute("uslugas", uslugas);
        Iterable<Check> checks = checkRepository.findAll();
        model.addAttribute("checks", checks);
        return "uslugacheck/add";
    }

    @PostMapping("/add")
    public String zooAdd(Model model, @ModelAttribute("serviceCheck") @Valid ServiceCheck serviceCheck,
                         BindingResult result,
                         @RequestParam(name = "usluga_name") String usl,
                         @RequestParam(name = "check_name") String che){
        if(result.hasErrors()){
            Iterable<Usluga> uslugaIterable = uslugaRepository.findAll();
            model.addAttribute("uslugas", uslugaIterable);
            Iterable<Check> checkIterable = checkRepository.findAll();
            model.addAttribute("checks", checkIterable);
            return "uslugacheck/add";
        }
        Usluga uslugas1 = uslugaRepository.findById(Long.parseLong(usl)).orElseThrow();
        serviceCheck.setUsluga(uslugas1);
        Check checks1 = checkRepository.findById(Long.parseLong(che)).orElseThrow();
        serviceCheck.setCheck2(checks1);
        serviceCheckRepository.save(serviceCheck);
        return "redirect:/uslugacheck";
    }
    @GetMapping("/edit/{id}")
    public String zooEdit(Model model,
                          @PathVariable Long id) {
        Optional<ServiceCheck> serviceCheck = serviceCheckRepository.findById(id);
        ArrayList<ServiceCheck> serviceCheckArrayList = new ArrayList<>();
        serviceCheck.ifPresent(serviceCheckArrayList::add);
        Iterable<Usluga> uslugas = uslugaRepository.findAll();
        Iterable<Check> checks = checkRepository.findAll();
        model.addAttribute("uslugas", uslugas);
        model.addAttribute("checks", checks);
        model.addAttribute("servicecheck", serviceCheckArrayList.get(0));
        return("/uslugacheck/edit");
    }

    @PostMapping("/edit/{id}")
    public String zooEdit(Model model, @ModelAttribute("serviceCheck") @Valid ServiceCheck serviceCheck, BindingResult result,
                          @PathVariable Long id) {
        if(!serviceCheckRepository.existsById(id)) {
            return "redirect:/uslugacheck/";
        }
        if(result.hasErrors()){
            Iterable<Usluga> productIterable = uslugaRepository.findAll();
            model.addAttribute("uslugas", productIterable);
            Iterable<Check> checkIterable = checkRepository.findAll();
            model.addAttribute("checks", checkIterable);
            serviceCheck.setId(id);
            return "/uslugacheck/edit";
        }
        Iterable<Usluga> productIterable = uslugaRepository.findAll();
        model.addAttribute("uslugas", productIterable);
        Iterable<Check> checkIterable = checkRepository.findAll();
        model.addAttribute("checks", checkIterable);
        serviceCheck.setId(id);
        serviceCheckRepository.save(serviceCheck);
        return("redirect:/uslugacheck/details/" + serviceCheck.getId());
    }

    @GetMapping("/details/{id}")
    public String zooDetails(Model model,
                             @PathVariable long id) {
        ServiceCheck uslugacheck = serviceCheckRepository.findById(id).orElseThrow();
        model.addAttribute("uslugacheckUGU", uslugacheck);
        return ("/uslugacheck/details");
    }
    @GetMapping("/delete/{id}")
    public String zooDelete(@PathVariable long id) {
        serviceCheckRepository.deleteById(id);
        return("redirect:/uslugacheck");
    }
}
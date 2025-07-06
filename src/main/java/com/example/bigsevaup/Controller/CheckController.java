package com.example.bigsevaup.Controller;

import com.example.bigsevaup.Model.*;
import com.example.bigsevaup.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/check")
@PreAuthorize("hasAuthority('ADMIN') OR hasAuthority('USER')")
public class CheckController {
    @Autowired
    CheckRepository checkRepository;
    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    SaleRepository saleRepository;
    @Autowired
    KlientRepository klientRepository;

    @GetMapping("")
    public String zooMain(Model model) {
        Iterable<Check> listCheck = checkRepository.findAll();
        model.addAttribute("listCheck", listCheck);
        return "check/index";
    }

    @GetMapping("/add")
    public String zooAddView(Check check, Model model) {
        Iterable<Employee> employees = employeeRepository.findAll();
        model.addAttribute("employees", employees);
        Iterable<Klient> klients = klientRepository.findAll();
        model.addAttribute("klients", klients);
        Iterable<Sale> sales = saleRepository.findAll();
        model.addAttribute("sales", sales);
        return "check/add";
    }

    @PostMapping("/add")
    public String zooAdd(Model model, @Valid Check check, BindingResult result,
                         @RequestParam(name = "empl_name") String emp,
                         @RequestParam(name = "klient_name") String klnt,
                         @RequestParam(name = "sale_name") String sle){
        if(result.hasErrors()){
            Iterable<Employee> employeeIterable = employeeRepository.findAll();
            model.addAttribute("employees", employeeIterable);
            Iterable<Klient> klientIterable = klientRepository.findAll();
            model.addAttribute("klients", klientIterable);
            Iterable<Sale> saleIterable = saleRepository.findAll();
            model.addAttribute("sales", saleIterable);
            return "check/add";
        }
        Employee emp1 = employeeRepository.findById(Long.parseLong(emp)).orElseThrow();
        check.setEmployee(emp1);
        Klient klnt1 = klientRepository.findById(Long.parseLong(klnt)).orElseThrow();
        check.setKlient(klnt1);
        Sale sale1 = saleRepository.findById(Long.parseLong(sle)).orElseThrow();
        check.setSale(sale1);
        checkRepository.save(check);
        return "redirect:/check";
    }
    @GetMapping("/edit/{id}")
    public String zooEdit(Model model,
                          @PathVariable long id) {
        Check check = checkRepository.findById(id).orElseThrow();
        model.addAttribute("check", check);
        Iterable<Employee> employeeIterable = employeeRepository.findAll();
        model.addAttribute("employees", employeeIterable);
        Iterable<Klient> klientIterable = klientRepository.findAll();
        model.addAttribute("klients", klientIterable);
        Iterable<Sale> saleIterable = saleRepository.findAll();
        model.addAttribute("sales", saleIterable);
        return("/check/edit");
    }

    @PostMapping("/edit/{id}")
    public String zooEdit(Model model, @Valid Check check, BindingResult result,
                          Long klient_name, Long empl_name, Long sale_name) {
        if(result.hasErrors()){
            Iterable<Employee> employeeIterable = employeeRepository.findAll();
            model.addAttribute("employees", employeeIterable);
            Iterable<Klient> klientIterable = klientRepository.findAll();
            model.addAttribute("klients", klientIterable);
            Iterable<Sale> saleIterable = saleRepository.findAll();
            model.addAttribute("sales", saleIterable);
            return "/check/edit";
        }
        check.setKlient(klientRepository.findById(klient_name).orElseThrow());
        check.setEmployee(employeeRepository.findById(empl_name).orElseThrow());
        check.setSale(saleRepository.findById(sale_name).orElseThrow());
        checkRepository.save(check);
        return("redirect:/check/details/" + check.getId());
    }

    @GetMapping("/filter")
    public String zooFilter(@RequestParam String searchName,
                            Model model){
        List<Check> check = checkRepository.findByAdressContaining(searchName);
        model.addAttribute("listcheck", check);
        return "check/index";
    }

    @GetMapping("/details/{id}")
    public String zooDetails(Model model,
                             @PathVariable long id) {
        Check check = checkRepository.findById(id).orElseThrow();
        model.addAttribute("checkUGU", check);
        return ("/check/details");
    }
    @GetMapping("/delete/{id}")
    public String zooDelete(@PathVariable long id) {
        checkRepository.deleteById(id);
        return("redirect:/check");
    }
}
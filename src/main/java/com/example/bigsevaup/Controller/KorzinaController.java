package com.example.bigsevaup.Controller;

import com.example.bigsevaup.Model.*;
import com.example.bigsevaup.Repository.*;
import com.example.bigsevaup.Service.EmailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Controller
public class KorzinaController {
    @Autowired
    ProductRepository productRepository;
    @Autowired
    KategoryProductRepository kategoryProductRepository;
    @Autowired
    UslugaRepository uslugaRepository;
    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    KorzinaRepository korzinaRepository;

    @GetMapping("/korzina/glavnaya")
    public String korzinaProdu(Model model) {
        Iterable<Korzina> listKorzina = korzinaRepository.findAll();
        model.addAttribute("listKorzina", listKorzina);
        Iterable<Product> listProduct = productRepository.findAll();
        model.addAttribute("listProduct", listProduct);
        Iterable<KategoryProduct> listKategoryproduct = kategoryProductRepository.findAll();
        model.addAttribute("listKategoryproduct", listKategoryproduct);
        Iterable<Usluga> listUsluga = uslugaRepository.findAll();
        model.addAttribute("listUsluga", listUsluga);
        return "korzina/glavnaya";
    }

    @GetMapping("/korzina/glavnaya/filter")
    public String zooFilter(@RequestParam String searchName,
                            Model model) {
        List<Product> product = productRepository.findByNameproductContaining(searchName);
        model.addAttribute("listProduct", product);
        return "korzina/glavnaya";
    }

    @GetMapping("/korzina/glavnaya/fildv/{id}")
    public String dvaFil(@PathVariable Integer id,
                         Model model) {
        List<Product> product = productRepository.findBykategory(id);
        model.addAttribute("listProduct", product);
        return "korzina/glavnaya";
    }

    @GetMapping("/korzina/glavnaya/fildvU")
    public String dvaUFil(Model model) {
        Iterable<Usluga> listUsluga = uslugaRepository.findAll();
        model.addAttribute("listUsluga", listUsluga);
        return "korzina/glavnaya";
    }

    @PostMapping("/korzina/glavnaya")
    public String korzRab(Model model, @RequestParam long id) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth.isAuthenticated()) {
            Product product = productRepository.findById(id).orElseThrow();
            Korzina korzina = new Korzina();
            Employee user = employeeRepository.findByUsername(auth.getName());
            korzina.setProductInkorxina(product);
            korzina.setEmployeeInkorzina(user);
            korzinaRepository.save(korzina);
        }
        model.addAttribute("products", productRepository.findAll());
        return "redirect:kupit";
    }

    @PostMapping("/korzina/glavnayaU")
    public String korzRabU(Model model, @RequestParam long id) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth.isAuthenticated()) {
            Usluga usluga = uslugaRepository.findById(id).orElseThrow();
            Korzina korzina = new Korzina();
            Employee user = employeeRepository.findByUsername(auth.getName());
            korzina.setUslugaInkorxina(usluga);
            korzina.setEmployeeInkorzina(user);
            korzinaRepository.save(korzina);
        }
        model.addAttribute("uslugas", uslugaRepository.findAll());
        return "redirect:kupit";
    }

    @GetMapping("/korzina/kupit")
    public String tochKupit(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Employee user = employeeRepository.findByUsername(auth.getName());
        List<Korzina> korzinaListProduct = new ArrayList<>();
        List<Korzina> korzinaListUsluga = new ArrayList<>();
        List<Korzina> korzinaList = korzinaRepository.findByEmployeeInkorzina(user);
        for (Korzina korzina : korzinaList) {
            if (korzina.getProductInkorxina() != null) {
                korzinaListProduct.add(korzina);
            } else {
                korzinaListUsluga.add(korzina);
            }
        }
        model.addAttribute("korzinasP", korzinaListProduct);
        model.addAttribute("korzinasU", korzinaListUsluga);
        return "/korzina/kupit";
    }

    @GetMapping("/korzina/delete/{id}")
    public String zooDeleteP(@PathVariable long id) {
        korzinaRepository.deleteById(id);
        return("redirect:/korzina/kupit");
    }

    @PostMapping("/korzina/kupit/mail")
    public String zooSend(@RequestParam String nameMail) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Employee user = employeeRepository.findByUsername(auth.getName());
        List<Korzina> korzinaListProduct = new ArrayList<>();
        List<Korzina> korzinaListUsluga = new ArrayList<>();
        List<Korzina> korzinaList = korzinaRepository.findByEmployeeInkorzina(user);
        Random rand = new Random();
        int randomNumber = rand.nextInt(9000) + 1000;
        String message = "Номер заказа: " + randomNumber;
        for (Korzina korzina : korzinaList) {
            if (korzina.getProductInkorxina() != null) {
                message += "\n* " + korzina.getProductInkorxina().getNameproduct();
            } else {
                message += "\n* " + korzina.getUslugaInkorxina().getNameusluga();
            }
        }

        try {
            EmailSender.sendEmail("pavelverhovod@gmail.com", "Заказ", message);
            EmailSender.sendEmail(nameMail, "Заказ", message);
            System.out.println("Письмо отправленно");
        } catch (MessagingException e) {
            System.out.println("Ошибка в отправке письма: " + e.getMessage());
        }
        return("redirect:/korzina/kupit");
    }
}
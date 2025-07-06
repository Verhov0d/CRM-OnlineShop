package com.example.bigsevaup.Controller;

import com.example.bigsevaup.Model.Klient;
import com.example.bigsevaup.Repository.KlientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/klient")
@PreAuthorize("hasAuthority('ADMIN') OR hasAuthority('USER')")
public class KlientController {
    @Autowired
    KlientRepository klientRepository;

    @GetMapping("")
    public String zooMain(Model model) {
        Iterable<Klient> listKlient = klientRepository.findAll();
        model.addAttribute("listKlient", listKlient);
        return "klient/index";
    }

    @GetMapping("/add")
    public String zooAddView(Klient klient) {
        return "klient/add";
    }

    @PostMapping("/add")
    public String zooAdd(@Valid Klient klient, BindingResult result){
        if(result.hasErrors())
            return "klient/add";
        klientRepository.save(klient);
        return "redirect:/klient";
    }
    @GetMapping("/edit/{id}")
    public String zooEdit(Model model, @PathVariable long id) {
        Klient klient = klientRepository.findById(id).orElseThrow();
        model.addAttribute("klient", klient);
        return("/klient/edit");
    }

    @PostMapping("/edit/{id}")
    public String zooEdit(@Valid Klient klient, BindingResult result) {
        if(result.hasErrors()) return "/klient/edit";
        klientRepository.save(klient);
        return("redirect:/klient/details/" + klient.getId());
    }

    @GetMapping("/filter")
    public String zooFilter(@RequestParam String searchName,
                            Model model){
        List<Klient> klient = klientRepository.findByNameklientContaining(searchName);
        model.addAttribute("listklient", klient);
        return "klient/index";
    }

    @GetMapping("/details/{id}")
    public String zooDetails(Model model,
                             @PathVariable long id) {
        Klient klient = klientRepository.findById(id).orElseThrow();
        model.addAttribute("klientUGU", klient);
        return ("/klient/details");
    }
    @GetMapping("/delete/{id}")
    public String zooDelete(@PathVariable long id) {
        klientRepository.deleteById(id);
        return("redirect:/klient");
    }
}

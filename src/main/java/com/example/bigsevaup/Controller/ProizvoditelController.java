package com.example.bigsevaup.Controller;


import com.example.bigsevaup.Model.Proizvoditel;
import com.example.bigsevaup.Repository.ProizvoditelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/proizvoditel")
@PreAuthorize("hasAuthority('ADMIN') OR hasAuthority('USER')")
public class ProizvoditelController {
    @Autowired
    ProizvoditelRepository proizvoditelRepository;

    @GetMapping("")
    public String zooMain(Model model) {
        Iterable<Proizvoditel> listProizvoditel = proizvoditelRepository.findAll();
        model.addAttribute("listProizvoditel", listProizvoditel);
        return "proizvoditel/index";
    }

    @GetMapping("/add")
    public String zooAddView(Proizvoditel proizvoditel) {
        return "proizvoditel/add";
    }

    @PostMapping("/add")
    public String zooAdd(@Valid Proizvoditel proizvoditel, BindingResult result){
        if(result.hasErrors())
            return "proizvoditel/add";
        proizvoditelRepository.save(proizvoditel);
        return "redirect:/proizvoditel";
    }
    @GetMapping("/edit/{id}")
    public String zooEdit(Model model,
                          @PathVariable long id) {
        Proizvoditel proizvoditel = proizvoditelRepository.findById(id).orElseThrow();
        model.addAttribute("proizvoditel", proizvoditel);
        return("/proizvoditel/edit");
    }

    @PostMapping("/edit/{id}")
    public String zooEdit(@Valid Proizvoditel proizvoditel, BindingResult result) {
        if(result.hasErrors()) return "/proizvoditel/edit";
        proizvoditelRepository.save(proizvoditel);
        return("redirect:/proizvoditel/details/" + proizvoditel.getId());
    }

    @GetMapping("/filter")
    public String zooFilter(@RequestParam String searchName,
                            Model model){
        List<Proizvoditel> proizvoditel = proizvoditelRepository.findByNameproizvoditelContaining(searchName);
        model.addAttribute("listproizvoditel", proizvoditel);
        return "proizvoditel/index";
    }

    @GetMapping("/details/{id}")
    public String zooDetails(Model model,
                             @PathVariable long id) {
        Proizvoditel proizvoditel = proizvoditelRepository.findById(id).orElseThrow();
        model.addAttribute("proizvoditelUGU", proizvoditel);
        return ("/proizvoditel/details");
    }
    @GetMapping("/delete/{id}")
    public String zooDelete(@PathVariable long id) {
        proizvoditelRepository.deleteById(id);
        return("redirect:/proizvoditel");
    }
}
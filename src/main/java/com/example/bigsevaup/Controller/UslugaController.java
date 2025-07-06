package com.example.bigsevaup.Controller;

import com.example.bigsevaup.Model.Usluga;
import com.example.bigsevaup.Repository.UslugaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/usluga")
@PreAuthorize("hasAuthority('ADMIN') OR hasAuthority('USER')")
public class UslugaController {
    @Autowired
    UslugaRepository uslugaRepository;

    @GetMapping("")
    public String zooMain(Model model) {
        Iterable<Usluga> listUsluga = uslugaRepository.findAll();
        model.addAttribute("listUsluga", listUsluga);
        return "usluga/index";
    }

    @GetMapping("/add")
    public String zooAddView(Usluga usluga) {
        return "usluga/add";
    }

    @PostMapping("/add")
    public String zooAdd(@Valid Usluga usluga, BindingResult result){
        if(result.hasErrors())
            return "usluga/add";
        uslugaRepository.save(usluga);
        return "redirect:/usluga";
    }
    @GetMapping("/edit/{id}")
    public String zooEdit(Model model,
                          @PathVariable long id) {
        Usluga usluga = uslugaRepository.findById(id).orElseThrow();
        model.addAttribute("usluga", usluga);
        return("/usluga/edit");
    }

    @PostMapping("/edit/{id}")
    public String zooEdit(@Valid Usluga usluga, BindingResult result) {
        if(result.hasErrors()) return "/usluga/edit";
        uslugaRepository.save(usluga);
        return("redirect:/usluga/details/" + usluga.getId());
    }

    @GetMapping("/filter")
    public String zooFilter(@RequestParam String searchName,
                            Model model){
        List<Usluga> usluga = uslugaRepository.findByNameuslugaContaining(searchName);
        model.addAttribute("listUsluga", usluga);
        return "usluga/index";
    }

    @GetMapping("/details/{id}")
    public String zooDetails(Model model,
                             @PathVariable long id) {
        Usluga usluga = uslugaRepository.findById(id).orElseThrow();
        model.addAttribute("uslugaUGU", usluga);
        return ("/usluga/details");
    }
    @GetMapping("/delete/{id}")
    public String zooDelete(@PathVariable long id) {
        uslugaRepository.deleteById(id);
        return("redirect:/usluga");
    }
}

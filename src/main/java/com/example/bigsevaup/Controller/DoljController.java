package com.example.bigsevaup.Controller;

import com.example.bigsevaup.Model.Dolj;
import com.example.bigsevaup.Repository.DoljRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/dolj")
@PreAuthorize("hasAuthority('ADMIN') OR hasAuthority('USER')")
public class DoljController {
    @Autowired
    DoljRepository doljRepository;

    @GetMapping("")
    public String zooMain(Model model) {
        Iterable<Dolj> listDolj = doljRepository.findAll();
        model.addAttribute("listdolj", listDolj);
        return "dolj/index";
    }

    @GetMapping("/add")
    public String zooAddView(Dolj dolj) {
        return "dolj/add";
    }

    @PostMapping("/add")
    public String zooAdd(@Valid Dolj dolj, BindingResult result){
        if(result.hasErrors())
            return "dolj/add";
        doljRepository.save(dolj);
        return "redirect:/dolj";
    }
    @GetMapping("/edit/{id}")
    public String zooEdit(Model model,
                          @PathVariable long id) {
        Dolj dolj = doljRepository.findById(id).orElseThrow();
        model.addAttribute("dolj", dolj);
        return("/dolj/edit");
    }

    @PostMapping("/edit/{id}")
    public String zooEdit(@Valid Dolj dolj, BindingResult result) {
        if(result.hasErrors()) return "/dolj/edit";
        doljRepository.save(dolj);
        return("redirect:/dolj/details/" + dolj.getId());
    }

    @GetMapping("/filter")
    public String zooFilter(@RequestParam String searchName,
                            Model model){
        List<Dolj> dolj = doljRepository.findByNamedoljContaining(searchName);
        model.addAttribute("listDolj", dolj);
        return "dolj/index";
    }

    @GetMapping("/details/{id}")
    public String zooDetails(Model model,
                             @PathVariable long id) {
        Dolj dolj = doljRepository.findById(id).orElseThrow();
        model.addAttribute("doljUGU", dolj);
        return ("/dolj/details");
    }
    @GetMapping("/delete/{id}")
    public String zooDelete(@PathVariable long id) {
        doljRepository.deleteById(id);
        return("redirect:/dolj");
    }
}
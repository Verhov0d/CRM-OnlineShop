package com.example.bigsevaup.Controller;

import com.example.bigsevaup.Model.Sale;
import com.example.bigsevaup.Repository.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/sale")
@PreAuthorize("hasAuthority('ADMIN') OR hasAuthority('USER')")
public class SaleController {
    @Autowired
    SaleRepository saleRepository;

    @GetMapping("")
    public String zooMain(Model model) {
        Iterable<Sale> listSale = saleRepository.findAll();
        model.addAttribute("listSale", listSale);
        return "sale/index";
    }

    @GetMapping("/add")
    public String zooAddView(Sale sale) {
        return "sale/add";
    }

    @PostMapping("/add")
    public String zooAdd(@Valid Sale sale, BindingResult result){
        if(result.hasErrors())
            return "sale/add";
        saleRepository.save(sale);
        return "redirect:/sale";
    }
    @GetMapping("/edit/{id}")
    public String zooEdit(Model model,
                          @PathVariable long id) {
        Sale sale = saleRepository.findById(id).orElseThrow();
        model.addAttribute("sale", sale);
        return("/sale/edit");
    }

    @PostMapping("/edit/{id}")
    public String zooEdit(@Valid Sale sale, BindingResult result) {
        if(result.hasErrors()) return "/sale/edit";
        saleRepository.save(sale);
        return("redirect:/sale/details/" + sale.getId());
    }

    @GetMapping("/filter")
    public String zooFilter(@RequestParam String searchName,
                            Model model){
        List<Sale> sale = saleRepository.findByNamesaleContaining(searchName);
        model.addAttribute("listSale", sale);
        return "sale/index";
    }

    @GetMapping("/details/{id}")
    public String zooDetails(Model model,
                             @PathVariable long id) {
        Sale sale = saleRepository.findById(id).orElseThrow();
        model.addAttribute("saleUGU", sale);
        return ("/sale/details");
    }
    @GetMapping("/delete/{id}")
    public String zooDelete(@PathVariable long id) {
        saleRepository.deleteById(id);
        return("redirect:/sale");
    }
}
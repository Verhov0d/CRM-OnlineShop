package com.example.bigsevaup.Controller;
import com.example.bigsevaup.Model.KategoryProduct;
import com.example.bigsevaup.Model.Klient;
import com.example.bigsevaup.Repository.KategoryProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/kategoryproduct")
@PreAuthorize("hasAuthority('ADMIN') OR hasAuthority('USER')")
public class KategoryProductController {
    @Autowired
    KategoryProductRepository kategoryProductRepository;

    @GetMapping("")
    public String zooMain(Model model) {
        Iterable<KategoryProduct> listKategoryproduct = kategoryProductRepository.findAll();
        model.addAttribute("listKategoryproduct", listKategoryproduct);
        return "kategoryproduct/index";
    }

    @GetMapping("/add")
    public String zooAddView(KategoryProduct kategoryProduct, Model model)
    {
        model.addAttribute("kategoryproduct", kategoryProduct);
        return "kategoryproduct/add";
    }

    @PostMapping("/add")
    public String zooAdd(@Valid KategoryProduct kategoryProduct, BindingResult result){
        if(result.hasErrors())
            return "kategoryproduct/add";
        kategoryProductRepository.save(kategoryProduct);
        return "redirect:/kategoryproduct";
    }
    @GetMapping("/edit/{id}")
    public String zooEdit(Model model,
                          @PathVariable long id) {
        KategoryProduct kategoryproduct = kategoryProductRepository.findById(id).orElseThrow();
        model.addAttribute("kategoryproduct", kategoryproduct);
        return("/kategoryproduct/edit");
    }

    @PostMapping("/edit/{id}")
    public String zooEdit(@Valid KategoryProduct kategoryProduct, BindingResult result) {
        if(result.hasErrors()) return "/kategoryproduct/edit";
        kategoryProductRepository.save(kategoryProduct);
        return("redirect:/kategoryproduct/details/" + kategoryProduct.getId());
    }

    @GetMapping("/filter")
    public String zooFilter(@RequestParam String searchName,
                            Model model){
        List<KategoryProduct> kategoryProducts = kategoryProductRepository.findByNazvprodContaining(searchName);
        model.addAttribute("listkategoryproduct", kategoryProducts);
        return "kategoryproduct/index";
    }

    @GetMapping("/details/{id}")
    public String zooDetails(Model model,
                             @PathVariable long id) {
        KategoryProduct kategoryProduct = kategoryProductRepository.findById(id).orElseThrow();
        model.addAttribute("kategoryproductUGU", kategoryProduct);
        return ("/kategoryproduct/details");
    }
    @GetMapping("/delete/{id}")
    public String zooDelete(@PathVariable long id) {
        kategoryProductRepository.deleteById(id);
        return("redirect:/kategoryproduct");
    }
}

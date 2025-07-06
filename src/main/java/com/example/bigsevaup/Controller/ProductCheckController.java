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
import java.util.*;

@Controller
@RequestMapping("/productcheck")
@PreAuthorize("hasAuthority('ADMIN') OR hasAuthority('USER')")
public class ProductCheckController {
    @Autowired
    ProductCheckRepository productCheckRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    CheckRepository checkRepository;

    @GetMapping("")
    public String zooMain(Model model) {
        Iterable<ProductCheck> listProductCheck = productCheckRepository.findAll();
        model.addAttribute("listProductCheck", listProductCheck);
        return "productcheck/index";
    }

    @GetMapping("/add")
    public String zooAddView(ProductCheck productcheck, Model model) {
        Iterable<Product> products = productRepository.findAll();
        model.addAttribute("products", products);
        Iterable<Check> checks = checkRepository.findAll();
        model.addAttribute("checks", checks);
        return "productcheck/add";
    }

    @PostMapping("/add")
    public String zooAdd(Model model, @ModelAttribute("productCheck") @Valid ProductCheck productcheck,
                         BindingResult result,
                         @RequestParam(name = "product_name") String pro,
                         @RequestParam(name = "check_name") String che){
        if(result.hasErrors()){
            Iterable<Product> productIterable = productRepository.findAll();
            model.addAttribute("products", productIterable);
            Iterable<Check> checkIterable = checkRepository.findAll();
            model.addAttribute("checks", checkIterable);
            return "productcheck/add";
        }
        Product products1 = productRepository.findById(Long.parseLong(pro)).orElseThrow();
        productcheck.setProduct(products1);
        Check checks1 = checkRepository.findById(Long.parseLong(che)).orElseThrow();
        productcheck.setCheck(checks1);
        productCheckRepository.save(productcheck);
        return "redirect:/productcheck";
    }
    @GetMapping("/edit/{id}")
    public String zooEdit(Model model,
                          @PathVariable long id) {
        Optional<ProductCheck> productCheck = productCheckRepository.findById(id);
        ArrayList<ProductCheck> productCheckArrayList = new ArrayList<>();
        productCheck.ifPresent(productCheckArrayList::add);
        Iterable<Product> products = productRepository.findAll();
        Iterable<Check> checks = checkRepository.findAll();
        model.addAttribute("products", products);
        model.addAttribute("checks", checks);
        model.addAttribute("productcheck", productCheckArrayList.get(0));
        return("/productcheck/edit");
    }

    @PostMapping("/edit/{id}")
    public String zooEdit(Model model, @ModelAttribute("productCheck") @Valid ProductCheck productcheck, BindingResult result,
                          @PathVariable Long id) {
        if(!productCheckRepository.existsById(id)) {
            return "redirect:/productcheck/";
        }
        if(result.hasErrors()){
            Iterable<Product> productIterable = productRepository.findAll();
            model.addAttribute("products", productIterable);
            Iterable<Check> checkIterable = checkRepository.findAll();
            model.addAttribute("checks", checkIterable);
            productcheck.setId(id);
            return "/productcheck/edit";
        }
        Iterable<Product> productIterable = productRepository.findAll();
        model.addAttribute("products", productIterable);
        Iterable<Check> checkIterable = checkRepository.findAll();
        model.addAttribute("checks", checkIterable);
        productcheck.setId(id);
        productCheckRepository.save(productcheck);
        return("redirect:/productcheck/details/" + productcheck.getId());
    }

    @GetMapping("/details/{id}")
    public String zooDetails(Model model,
                             @PathVariable long id) {
        ProductCheck productcheck = productCheckRepository.findById(id).orElseThrow();
        model.addAttribute("productcheckUGU", productcheck);
        return ("/productcheck/details");
    }
    @GetMapping("/delete/{id}")
    public String zooDelete(@PathVariable long id) {
        productCheckRepository.deleteById(id);
        return("redirect:/productcheck");
    }
}
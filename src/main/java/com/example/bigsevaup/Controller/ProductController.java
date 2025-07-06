package com.example.bigsevaup.Controller;

import com.example.bigsevaup.Model.*;
import com.example.bigsevaup.Repository.KategoryProductRepository;
import com.example.bigsevaup.Repository.PostavshikRepository;
import com.example.bigsevaup.Repository.ProductRepository;
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
@RequestMapping("/product")
@PreAuthorize("hasAuthority('ADMIN') OR hasAuthority('USER')")
public class ProductController {
    @Autowired
    ProductRepository productRepository;
    @Autowired
    PostavshikRepository postavshikRepository;
    @Autowired
    KategoryProductRepository kategoryProductRepository;
    @Autowired
    ProizvoditelRepository proizvoditelRepository;

    @GetMapping("")
    public String zooMain(Model model) {
        Iterable<Product> listProduct = productRepository.findAll();
        model.addAttribute("listProduct", listProduct);
        return "product/index";
    }

    @GetMapping("/add")
    public String zooAddView(Product product, Model model) {
        Iterable<Postavshik> postavshiks = postavshikRepository.findAll();
        model.addAttribute("postavshiks", postavshiks);
        Iterable<KategoryProduct> kategoryProducts = kategoryProductRepository.findAll();
        model.addAttribute("kategoryProducts", kategoryProducts);
        Iterable<Proizvoditel> proizvoditels = proizvoditelRepository.findAll();
        model.addAttribute("proizvoditels", proizvoditels);
        return "product/add";
    }

    @PostMapping("/add")
    public String zooAdd(Model model, @Valid Product product, BindingResult result,
                         @RequestParam(name = "postavshiks_name") String postavshi,
                         @RequestParam(name = "kategoryProducts_name") String kategoryProdu,
                         @RequestParam(name = "proizvoditels_name") String proizvodite){
        if(result.hasErrors()){
            Iterable<Postavshik> postavshiks = postavshikRepository.findAll();
            model.addAttribute("postavshiks", postavshiks);
            Iterable<KategoryProduct> kategoryProducts = kategoryProductRepository.findAll();
            model.addAttribute("kategoryProducts", kategoryProducts);
            Iterable<Proizvoditel> proizvoditels = proizvoditelRepository.findAll();
            model.addAttribute("proizvoditels", proizvoditels);
            return "product/add";
        }
        Postavshik postavshik1 = postavshikRepository.findById(Long.parseLong(postavshi)).orElseThrow();
        product.setPostavshik(postavshik1);
        KategoryProduct kategoryProduct1 = kategoryProductRepository.findById(Long.parseLong(kategoryProdu)).orElseThrow();
        product.setKategoryProduct(kategoryProduct1);
        Proizvoditel proizvoditel1 = proizvoditelRepository.findById(Long.parseLong(proizvodite)).orElseThrow();
        product.setProizvoditel(proizvoditel1);
        productRepository.save(product);
        return "redirect:/product";
    }
    @GetMapping("/edit/{id}")
    public String zooEdit(Model model,
                          @PathVariable long id) {
        Product product = productRepository.findById(id).orElseThrow();
        model.addAttribute("product", product);
        Iterable<Postavshik> postavshikIterable = postavshikRepository.findAll();
        model.addAttribute("postavshiks", postavshikIterable);
        Iterable<KategoryProduct> kategoryProductIterable = kategoryProductRepository.findAll();
        model.addAttribute("kategoryProducts", kategoryProductIterable);
        Iterable<Proizvoditel> proizvoditelIterable = proizvoditelRepository.findAll();
        model.addAttribute("proizvoditels", proizvoditelIterable);
        return("/product/edit");
    }

    @PostMapping("/edit/{id}")
    public String zooEdit(Model model, @Valid Product product, BindingResult result,
                          Long postavshiks_name, Long kategoryProducts_name, Long proizvoditels_name) {
        if(result.hasErrors()){
            Iterable<Postavshik> postavshiks = postavshikRepository.findAll();
            model.addAttribute("postavshiks", postavshiks);
            Iterable<KategoryProduct> kategoryProducts = kategoryProductRepository.findAll();
            model.addAttribute("kategoryProducts", kategoryProducts);
            Iterable<Proizvoditel> proizvoditels = proizvoditelRepository.findAll();
            model.addAttribute("proizvoditels", proizvoditels);
            return "/product/edit";
        }
        product.setPostavshik(postavshikRepository.findById(postavshiks_name).orElseThrow());
        product.setKategoryProduct(kategoryProductRepository.findById(kategoryProducts_name).orElseThrow());
        product.setProizvoditel(proizvoditelRepository.findById(proizvoditels_name).orElseThrow());
        productRepository.save(product);
        return("redirect:/product/details/" + product.getId());
    }

    @GetMapping("/filter")
    public String zooFilter(@RequestParam String searchName,
                            Model model){
        List<Product> product = productRepository.findByNameproductContaining(searchName);
        model.addAttribute("listProduct", product);
        return "product/index";
    }

    @GetMapping("/details/{id}")
    public String zooDetails(Model model,
                             @PathVariable long id) {
        Product product = productRepository.findById(id).orElseThrow();
        model.addAttribute("productUGU", product);
        return ("/product/details");
    }
    @GetMapping("/delete/{id}")
    public String zooDelete(@PathVariable long id) {
        productRepository.deleteById(id);
        return("redirect:/product");
    }
}

package com.example.bigsevaup.Controller;
import com.example.bigsevaup.Model.Postavshik;
import com.example.bigsevaup.Repository.PostavshikRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/postavshik")
@PreAuthorize("hasAuthority('ADMIN') OR hasAuthority('USER')")
public class PostavshikController {
    @Autowired
    PostavshikRepository postavshikRepository;

    @GetMapping("")
    public String postavshikMain(Model model) {
        Iterable<Postavshik> listpostavshik = postavshikRepository.findAll();
        model.addAttribute("listpostavshik", listpostavshik);
        return "postavshik/index";
    }

    @GetMapping("/add")
    public String postavshikAddView(Postavshik postavshik) {
        return "postavshik/add";
    }

    @PostMapping("/add")
    public String postavshikAdd(@Valid Postavshik postavshik, BindingResult result){
        if(result.hasErrors())
            return "postavshik/add";
        postavshikRepository.save(postavshik);
        return "redirect:/postavshik";
    }
    @GetMapping("/edit/{id}")
    public String postavshikEdit(Model model,
                          @PathVariable long id) {
        Postavshik postavshik = postavshikRepository.findById(id).orElseThrow();
        model.addAttribute("postavshik", postavshik);
        return("/postavshik/edit");
    }

    @PostMapping("/edit/{id}")
    public String postavshikEdit(@Valid Postavshik postavshik, BindingResult result) {
        if(result.hasErrors()) return "/postavshik/edit";
        postavshikRepository.save(postavshik);
        return("redirect:/postavshik/details/" + postavshik.getId());
    }

    @GetMapping("/filter")
    public String postavshikFilter(@RequestParam String searchName,
                            Model model){
        List<Postavshik> postavshik = postavshikRepository.findByNamepostavshikContaining(searchName);
        model.addAttribute("listpostavshik", postavshik);
        return "postavshik/index";
    }

    @GetMapping("/details/{id}")
    public String postavshikDetails(Model model,
                             @PathVariable long id) {
        Postavshik postavshik = postavshikRepository.findById(id).orElseThrow();
        model.addAttribute("postavshikUGU", postavshik);
        return ("/postavshik/details");
    }
    @GetMapping("/delete/{id}")
    public String postavshikDelete(@PathVariable long id) {
        postavshikRepository.deleteById(id);
        return("redirect:/postavshik");
    }
}
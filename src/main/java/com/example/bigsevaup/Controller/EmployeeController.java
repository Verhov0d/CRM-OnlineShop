package com.example.bigsevaup.Controller;

import com.example.bigsevaup.Model.Dolj;
import com.example.bigsevaup.Model.Employee;
import com.example.bigsevaup.Model.Postavshik;
import com.example.bigsevaup.Model.Role;
import com.example.bigsevaup.Repository.DoljRepository;
import com.example.bigsevaup.Repository.EmployeeRepository;
import com.example.bigsevaup.Repository.PostavshikRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/employee")
@PreAuthorize("hasAuthority('ADMIN')")
public class EmployeeController {
    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    DoljRepository doljRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("")
    public String zooMain(Model model) {
        Iterable<Employee> listEmployee = employeeRepository.findAll();
        model.addAttribute("listEmployee", listEmployee);
        return "employee/index";
    }

    @GetMapping("/add")
    public String zooAddView(Employee employee, Model model) {
        Iterable<Dolj> doljs = doljRepository.findAll();
        model.addAttribute("doljs", doljs);
        Iterable<Role> role = List.of(Role.values());
        model.addAttribute("role", role);
        return "employee/add";
    }

    @PostMapping("/add")
    public String zooAdd(Model model, @Valid Employee employee, BindingResult result,
                         @RequestParam(name = "dolj_name") String dol,
                         @RequestParam(name = "role_name") String NazvRole){
        if(result.hasErrors()){
            Iterable<Dolj> doljIterable = doljRepository.findAll();
            model.addAttribute("doljs", doljIterable);
            Iterable<Role> roleIterable = List.of(Role.values());
            model.addAttribute("role", roleIterable);
            return "employee/add";
        }
        Dolj doljs1 = doljRepository.findById(Long.parseLong(dol)).orElseThrow();
        employee.setDolj(doljs1);
        employee.setRoles(Collections.singleton(Role.valueOf(NazvRole)));
        employee.setPassword(passwordEncoder.encode(employee.getPassword()));
        employeeRepository.save(employee);
        return "redirect:/employee";
    }
    @GetMapping("/edit/{id}")
    public String zooEdit(Model model,
                          @PathVariable long id) {
        Employee employee = employeeRepository.findById(id).orElseThrow();
        model.addAttribute("employee", employee);
        Iterable<Dolj> doljIterable = doljRepository.findAll();
        model.addAttribute("doljs", doljIterable);
        Iterable<Role> roleIterable = List.of(Role.values());
        model.addAttribute("role", roleIterable);
        return("/employee/edit");
    }

    @PostMapping("/edit/{id}")
    public String zooEdit(Model model, @Valid Employee employee, BindingResult result,
                          Long dolj_name, String role_name, String password) {
        if(result.hasErrors()){
            Iterable<Dolj> doljIterable = doljRepository.findAll();
            model.addAttribute("doljs", doljIterable);
            Iterable<Role> roleIterable = List.of(Role.values());
            model.addAttribute("role", roleIterable);
            return "/employee/edit";
        }
        employee.setDolj(doljRepository.findById(dolj_name).orElseThrow());
        employee.setRoles(Collections.singleton(Role.valueOf(role_name)));
        employee.setActive(true);
        employee.setPassword(passwordEncoder.encode(employee.getPassword()));
        employeeRepository.save(employee);
        return("redirect:/employee/details/" + employee.getId());
    }

    @GetMapping("/filter")
    public String zooFilter(@RequestParam String searchName,
                            Model model){
        List<Employee> employee = employeeRepository.findByNameContaining(searchName);
        model.addAttribute("listemployee", employee);
        return "employee/index";
    }

    @GetMapping("/details/{id}")
    public String zooDetails(Model model,
                             @PathVariable long id) {
        Employee employee = employeeRepository.findById(id).orElseThrow();
        model.addAttribute("employeeUGU", employee);
        return ("/employee/details");
    }
    @GetMapping("/delete/{id}")
    public String zooDelete(@PathVariable long id) {
        employeeRepository.deleteById(id);
        return("redirect:/employee");
    }
}
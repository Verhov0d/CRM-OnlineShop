package com.example.bigsevaup.Repository;
import com.example.bigsevaup.Model.Employee;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface EmployeeRepository extends CrudRepository<Employee, Long>{
    List<Employee> findByNameContaining(String name);
    Employee findByUsername(String username);
}
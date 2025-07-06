package com.example.bigsevaup.Repository;

import com.example.bigsevaup.Model.Employee;
import com.example.bigsevaup.Model.Korzina;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface KorzinaRepository extends CrudRepository<Korzina, Long> {
    List<Korzina> findByEmployeeInkorzina (Employee employee);
}
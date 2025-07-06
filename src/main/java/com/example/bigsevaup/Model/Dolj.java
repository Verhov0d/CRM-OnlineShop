package com.example.bigsevaup.Model;
import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Dolj {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull(message = "Поле не может быть пустым")
    @Min(value = 1, message ="Зарплата не может быть меньше 1" )
    private Integer salary;

    @NotBlank(message = "Поле не может быть пустым")
    @Size(min = 1, max = 50, message = "Поле должно содержать от 1 до 50 символов")
    private String namedolj;

    @OneToMany(mappedBy = "dolj", orphanRemoval = true)
    private List<Employee> lstdolj = new ArrayList<>();

    public Dolj() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    public String getNamedolj() {
        return namedolj;
    }

    public void setNamedolj(String namedolj) {
        this.namedolj = namedolj;
    }

    public List<Employee> getLstdolj() {
        return lstdolj;
    }

    public void setLstdolj(List<Employee> lstdolj) {
        this.lstdolj = lstdolj;
    }
}

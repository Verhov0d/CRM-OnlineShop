package com.example.bigsevaup.Model;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "[check]")
public class Check {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull(message = "Поле не может быть пустым")
    @Min(value = 0, message ="Цена не может быть ниже 0" )
    private Integer obshayacost;

    @NotBlank(message = "Поле не может быть пустым")
    @Size(min = 1, max = 300, message = "Поле должно содержать от 1 до 300 символов")
    private String adress;

    @NotBlank(message = "Поле не может быть пустым")
    @Size(min = 12, max = 12, message = "Поле должно содержать 12 символов")
    private String inn;


    @ManyToOne(fetch = FetchType.LAZY)
    private Klient klient;

    @ManyToOne(fetch = FetchType.LAZY)
    private Sale sale;

    @ManyToOne(fetch = FetchType.LAZY)
    private Employee employee;

    @OneToMany(mappedBy = "check", orphanRemoval = true)
    private List<ProductCheck> lstcheck = new ArrayList<>();

    @OneToMany(mappedBy = "check2", orphanRemoval = true)
    private List<ServiceCheck> lstcheck2 = new ArrayList<>();

    public Check() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getObshayacost() {
        return obshayacost;
    }

    public void setObshayacost(Integer obshayacost) {
        this.obshayacost = obshayacost;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getInn() {
        return inn;
    }

    public void setInn(String inn) {
        this.inn = inn;
    }

    public Klient getKlient() {
        return klient;
    }

    public void setKlient(Klient klient) {
        this.klient = klient;
    }

    public Sale getSale() {
        return sale;
    }

    public void setSale(Sale sale) {
        this.sale = sale;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public List<ProductCheck> getLstcheck() {
        return lstcheck;
    }

    public void setLstcheck(List<ProductCheck> lstcheck) {
        this.lstcheck = lstcheck;
    }

    public List<ServiceCheck> getLstcheck2() {
        return lstcheck2;
    }

    public void setLstcheck2(List<ServiceCheck> lstcheck2) {
        this.lstcheck2 = lstcheck2;
    }
}

package com.example.bigsevaup.Model;

import javax.persistence.Entity;

import javax.persistence.*;

@Entity
public class Korzina {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @ManyToOne(fetch = FetchType.EAGER)
    private Product productInkorxina;

    @ManyToOne(fetch = FetchType.EAGER)
    private Usluga uslugaInkorxina;

    @ManyToOne(fetch = FetchType.LAZY)
    private Employee employeeInkorzina;

    public Korzina() {
    }

    public Korzina(long id, Product productInkorxina, Usluga uslugaInkorxina, Employee employeeInkorzina) {
        this.id = id;
        this.productInkorxina = productInkorxina;
        this.uslugaInkorxina = uslugaInkorxina;
        this.employeeInkorzina = employeeInkorzina;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Product getProductInkorxina() {
        return productInkorxina;
    }

    public void setProductInkorxina(Product productInkorxina) {
        this.productInkorxina = productInkorxina;
    }

    public Usluga getUslugaInkorxina() {
        return uslugaInkorxina;
    }

    public void setUslugaInkorxina(Usluga uslugaInkorxina) {
        this.uslugaInkorxina = uslugaInkorxina;
    }

    public Employee getEmployeeInkorzina() {
        return employeeInkorzina;
    }

    public void setEmployeeInkorzina(Employee employeeInkorzina) {
        this.employeeInkorzina = employeeInkorzina;
    }

}
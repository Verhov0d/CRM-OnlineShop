package com.example.bigsevaup.Model;
import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Klient {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "Поле не может быть пустым")
    @Size(min = 1, max = 50, message = "Поле должно содержать от 1 до 50 символов")
    private String surnameklient;

    @NotBlank(message = "Поле не может быть пустым")
    @Size(min = 1, max = 50, message = "Поле должно содержать от 1 до 50 символов")
    private String nameklient;

    @NotBlank(message = "Поле не может быть пустым")
    @Size(min = 1, max = 50, message = "Поле должно содержать от 1 до 50 символов")
    private String otchestvoklient;

    @OneToMany(mappedBy = "klient", orphanRemoval = true)
    private List<Check> lstklient = new ArrayList<>();

    public Klient() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSurnameklient() {
        return surnameklient;
    }

    public void setSurnameklient(String surnameklient) {
        this.surnameklient = surnameklient;
    }

    public String getNameklient() {
        return nameklient;
    }

    public void setNameklient(String nameklient) {
        this.nameklient = nameklient;
    }

    public String getOtchestvoklient() {
        return otchestvoklient;
    }

    public void setOtchestvoklient(String otchestvoklient) {
        this.otchestvoklient = otchestvoklient;
    }

    public List<Check> getLstklient() {
        return lstklient;
    }

    public void setLstklient(List<Check> lstklient) {
        this.lstklient = lstklient;
    }
}

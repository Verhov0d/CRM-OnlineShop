package com.example.bigsevaup.Model;
import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Postavshik {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "Поле не может быть пустым")
    @Size(min = 1, max = 50, message = "Поле должно содержать от 1 до 50 символов")
    private String namepostavshik;

    @OneToMany(mappedBy = "postavshik")
    private List<Product> lstpostavshik = new ArrayList<>();

    public Postavshik() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNamepostavshik() {
        return namepostavshik;
    }

    public void setNamepostavshik(String namepostavshik) {
        this.namepostavshik = namepostavshik;
    }

    public List<Product> getLstpostavshik() {
        return lstpostavshik;
    }

    public void setLstpostavshik(List<Product> lstpostavshik) {
        this.lstpostavshik = lstpostavshik;
    }
}

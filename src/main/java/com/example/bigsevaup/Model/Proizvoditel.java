package com.example.bigsevaup.Model;
import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Proizvoditel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "Поле не может быть пустым")
    @Size(min = 1, max = 50, message = "Поле должно содержать от 1 до 50 символов")
    private String nameproizvoditel;

    @NotBlank(message = "Поле не может быть пустым")
    @Size(min = 1, max = 300, message = "Поле должно содержать от 1 до 300 символов")
    private String adressproizvoditel;

    @NotBlank(message = "Поле не может быть пустым")
    @Size(min = 11, max = 11, message = "Поле должно содержать 11 символов")
    private String kontaktniynumber;

    @OneToMany(mappedBy = "proizvoditel", orphanRemoval = true)
    private List<Product> lstproizvoditel = new ArrayList<>();

    public Proizvoditel() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameproizvoditel() {
        return nameproizvoditel;
    }

    public void setNameproizvoditel(String nameproizvoditel) {
        this.nameproizvoditel = nameproizvoditel;
    }

    public String getAdressproizvoditel() {
        return adressproizvoditel;
    }

    public void setAdressproizvoditel(String adressproizvoditel) {
        this.adressproizvoditel = adressproizvoditel;
    }

    public String getKontaktniynumber() {
        return kontaktniynumber;
    }

    public void setKontaktniynumber(String kontaktniynumber) {
        this.kontaktniynumber = kontaktniynumber;
    }

    public List<Product> getLstproizvoditel() {
        return lstproizvoditel;
    }

    public void setLstproizvoditel(List<Product> lstproizvoditel) {
        this.lstproizvoditel = lstproizvoditel;
    }
}

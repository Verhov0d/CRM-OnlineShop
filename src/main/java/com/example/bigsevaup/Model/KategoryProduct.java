package com.example.bigsevaup.Model;
import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class KategoryProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "Поле не может быть пустым")
    @Size(min = 1, max = 50, message = "Поле должно содержать от 1 до 50 символов")
    private String nazvprod;

    @OneToMany(mappedBy = "kategoryProduct", orphanRemoval = true)
    private List<Product> lstkategoryProduct = new ArrayList<>();

    public KategoryProduct() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNazvprod() {
        return nazvprod;
    }

    public void setNazvprod(String nazvprod) {
        this.nazvprod = nazvprod;
    }

    public List<Product> getLstkategoryProduct() {
        return lstkategoryProduct;
    }

    public void setLstkategoryProduct(List<Product> lstkategoryProduct) {
        this.lstkategoryProduct = lstkategoryProduct;
    }
}

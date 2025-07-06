package com.example.bigsevaup.Model;
import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class ProductCheck {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "Поле не может быть пустым")
    @Size(min = 1, max = 50, message = "Поле должно содержать от 1 до 50 символов")
    private String sernum;

    @NotBlank(message = "Поле не может быть пустым")
    private String datapokupk;

    @ManyToOne(fetch = FetchType.LAZY)
    private Check check;

    @ManyToOne(fetch = FetchType.LAZY)
    private Product product;

    public ProductCheck() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Check getCheck() {
        return check;
    }

    public void setCheck(Check check) {
        this.check = check;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getSernum() {
        return sernum;
    }

    public void setSernum(String sernum) {
        this.sernum = sernum;
    }

    public String getDatapokupk() {
        return datapokupk;
    }

    public void setDatapokupk(String datapokupk) {
        this.datapokupk = datapokupk;
    }
}

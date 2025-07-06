package com.example.bigsevaup.Model;
import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "Поле не может быть пустым")
    @Size(min = 1, max = 50, message = "Поле должно содержать от 1 до 50 символов")
    private String nameproduct;

    @NotBlank(message = "Поле не может быть пустым")
    @Size(min = 1, max = 300, message = "Поле должно содержать от 1 до 50 символов")
    private String opisproduct;

    @NotBlank(message = "Поле не может быть пустым")
    @Size(min = 1, max = 999, message = "Поле должно содержать от 1 до 999 символов")
    private String fotoproduct;

    @NotNull(message = "Поле не может быть пустым")
    @Min(value = 0, message ="Стоимость не может быть меньше 0")
    private Integer costproduct;

    @NotBlank(message = "Поле не может быть пустым")
    private String datapost;

    @ManyToOne(fetch = FetchType.LAZY)
    private Postavshik postavshik;

    @ManyToOne(fetch = FetchType.LAZY)
    private KategoryProduct kategoryProduct;

    @ManyToOne(fetch = FetchType.LAZY)
    private Proizvoditel proizvoditel;

    @OneToMany(mappedBy = "product", orphanRemoval = true)
    private List<ProductCheck> lstproduct = new ArrayList<>();

    @OneToMany(mappedBy = "productInkorxina", orphanRemoval = true)
    private List<Korzina> lstkorzinaPRD = new ArrayList<>();

    public Product() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOpisproduct() {
        return opisproduct;
    }

    public void setOpisproduct(String opisproduct) {
        this.opisproduct = opisproduct;
    }

    public String getFotoproduct() {
        return fotoproduct;
    }

    public void setFotoproduct(String fotoproduct) {
        this.fotoproduct = fotoproduct;
    }

    public String getNameproduct() {
        return nameproduct;
    }

    public void setNameproduct(String nameproduct) {
        this.nameproduct = nameproduct;
    }

    public Integer getCostproduct() {
        return costproduct;
    }

    public void setCostproduct(Integer costproduct) {
        this.costproduct = costproduct;
    }

    public Postavshik getPostavshik() {
        return postavshik;
    }

    public void setPostavshik(Postavshik postavshik) {
        this.postavshik = postavshik;
    }

    public KategoryProduct getKategoryProduct() {
        return kategoryProduct;
    }

    public void setKategoryProduct(KategoryProduct kategoryProduct) {
        this.kategoryProduct = kategoryProduct;
    }

    public Proizvoditel getProizvoditel() {
        return proizvoditel;
    }

    public void setProizvoditel(Proizvoditel proizvoditel) {
        this.proizvoditel = proizvoditel;
    }

    public List<ProductCheck> getLstproduct() {
        return lstproduct;
    }

    public void setLstproduct(List<ProductCheck> lstproduct) {
        this.lstproduct = lstproduct;
    }
    public String getnazvprod() {return kategoryProduct.getNazvprod();}

    public String getDatapost() {
        return datapost;
    }

    public void setDatapost(String datapost) {
        this.datapost = datapost;
    }

    public List<Korzina> getLstkorzinaPRD() {
        return lstkorzinaPRD;
    }

    public void setLstkorzinaPRD(List<Korzina> lstkorzinaPRD) {
        this.lstkorzinaPRD = lstkorzinaPRD;
    }
}

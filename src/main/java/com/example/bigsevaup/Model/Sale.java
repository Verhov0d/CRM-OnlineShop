package com.example.bigsevaup.Model;
import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "Поле не может быть пустым")
    @Size(min = 1, max = 50, message = "Поле должно содержать от 1 до 50 символов")
    private String namesale;

    @NotBlank(message = "Поле не может быть пустым")
    @Size(min = 1, max = 300, message = "Поле должно содержать от 1 до 300 символов")
    private String descriptionsale;

    @NotBlank(message = "Поле не может быть пустым")
    @Size(min = 1, max = 300, message = "Поле должно содержать от 1 до 300 символов")
    private String conditionsale;

    @NotNull(message = "Поле не может быть пустым")
    @Max(value = 100, message ="Скидка не может быть выше 100%" )
    @Min(value = 1, message ="Скидка не может быть ниже 1" )
    private Integer amountsale;

    @OneToMany(mappedBy = "sale", orphanRemoval = true)
    private List<Check> lstsale = new ArrayList<>();

    public Sale() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNamesale() {
        return namesale;
    }

    public void setNamesale(String namesale) {
        this.namesale = namesale;
    }

    public String getDescriptionsale() {
        return descriptionsale;
    }

    public void setDescriptionsale(String descriptionsale) {
        this.descriptionsale = descriptionsale;
    }

    public String getConditionsale() {
        return conditionsale;
    }

    public void setConditionsale(String conditionsale) {
        this.conditionsale = conditionsale;
    }

    public Integer getAmountsale() {
        return amountsale;
    }

    public void setAmountsale(Integer amountsale) {
        this.amountsale = amountsale;
    }

    public List<Check> getLstsale() {
        return lstsale;
    }

    public void setLstsale(List<Check> lstsale) {
        this.lstsale = lstsale;
    }
}

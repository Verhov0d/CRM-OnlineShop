package com.example.bigsevaup.Model;
import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Usluga {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "Поле не может быть пустым")
    @Size(min = 1, max = 50, message = "Поле должно содержать от 1 до 50 символов")
    private String nameusluga;

    @NotBlank(message = "Поле не может быть пустым")
    @Size(min = 1, max = 300, message = "Поле должно содержать от 1 до 300 символов")
    private String opisusluga;

    @NotNull(message = "Поле не может быть пустым")
    @Min(value = 0, message ="Цена не может быть ниже 0" )
    private Integer costusluga;

    @NotBlank(message = "Поле не может быть пустым")
    @Size(min = 1, max = 999, message = "Поле должно содержать от 1 до 999 символов")
    private String fotosluga;

    @OneToMany(mappedBy = "usluga", orphanRemoval = true)
    private List<ServiceCheck> lstusluga = new ArrayList<>();

    @OneToMany(mappedBy = "uslugaInkorxina", orphanRemoval = true)
    private List<Korzina> lstkorzinaUSL = new ArrayList<>();

    public Usluga() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameusluga() {
        return nameusluga;
    }

    public void setNameusluga(String nameusluga) {
        this.nameusluga = nameusluga;
    }

    public String getOpisusluga() {
        return opisusluga;
    }

    public void setOpisusluga(String opisusluga) {
        this.opisusluga = opisusluga;
    }

    public Integer getCostusluga() {
        return costusluga;
    }

    public void setCostusluga(Integer costusluga) {
        this.costusluga = costusluga;
    }

    public String getFotosluga() {
        return fotosluga;
    }

    public void setFotosluga(String fotosluga) {
        this.fotosluga = fotosluga;
    }

    public List<ServiceCheck> getLstusluga() {
        return lstusluga;
    }

    public void setLstusluga(List<ServiceCheck> lstusluga) {
        this.lstusluga = lstusluga;
    }

    public List<Korzina> getLstkorzinaUSL() {
        return lstkorzinaUSL;
    }

    public void setLstkorzinaUSL(List<Korzina> lstkorzinaUSL) {
        this.lstkorzinaUSL = lstkorzinaUSL;
    }
}

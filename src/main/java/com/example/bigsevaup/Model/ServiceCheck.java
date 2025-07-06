package com.example.bigsevaup.Model;
import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Table(name = "serviceCheck")
public class ServiceCheck {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "Поле не может быть пустым")
    private String dataPribor;

    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    private Check check2;

    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    private Usluga usluga;

    public ServiceCheck() {

    }

    public ServiceCheck(String dataPribor, Check check2, Usluga usluga) {
        this.dataPribor = dataPribor;
        this.check2 = check2;
        this.usluga = usluga;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Check getCheck2() {
        return check2;
    }

    public void setCheck2(Check check2) {
        this.check2 = check2;
    }

    public Usluga getUsluga() {
        return usluga;
    }

    public void setUsluga(Usluga usluga) {
        this.usluga = usluga;
    }

    public String getDataPribor() {
        return dataPribor;
    }

    public void setDataPribor(String dataPribor) {
        this.dataPribor = dataPribor;
    }
}

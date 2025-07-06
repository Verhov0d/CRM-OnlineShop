package com.example.bigsevaup.Model;
import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "Поле не может быть пустым")
    @Size(min = 1, max = 50, message = "Поле должно содержать от 1 до 50 символов")
    private String surname;

    @NotBlank(message = "Поле не может быть пустым")
    @Size(min = 1, max = 50, message = "Поле должно содержать от 1 до 50 символов")
    private String name;

    @NotBlank(message = "Поле не может быть пустым")
    @Size(min = 1, max = 50, message = "Поле должно содержать от 1 до 50 символов")
    private String otchestvo;

    @NotBlank(message = "Поле не должно быть пустым!")
    @Size(min = 2, max = 50, message = "Поле должно содержать от 2 до 50 символов")
    private String username;

    @Pattern(regexp = "^(?=.*[0-9])" +
            "(?=.*[a-z])" +
            "(?=.*[A-Z])" +
            "(?=.*[!@#$%^&+=])" +
            "(?=\\S+$)" +
            ".{6,}", message = "Пароль должен быть не меньше 6 символов,"+ "\n" +
            "иметь числа и латинкие строчные и заглавные буквы," + "\n" +
            "а также специальные символы ")
    private String password;

    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    private Dolj dolj;

    @OneToMany(mappedBy = "employee", orphanRemoval = true)
    private List<Check> lstemployee = new ArrayList<>();

    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;

    @OneToMany(mappedBy = "employeeInkorzina", orphanRemoval = true)
    private List<Korzina> lstkorzinaEMP = new ArrayList<>();

    private Boolean active;

    public Employee(String surname, String name, String otchestvo,
                    String username, String password, Boolean active, Set<Role> roles) {
        this.surname = surname;
        this.name = name;
        this.otchestvo = otchestvo;
        this.username = username;
        this.password = password;
        this.active = active;
        this.roles = roles;
    }

    public Employee() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOtchestvo() {
        return otchestvo;
    }

    public void setOtchestvo(String otchestvo) {
        this.otchestvo = otchestvo;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Dolj getDolj() {
        return dolj;
    }

    public void setDolj(Dolj dolj) {
        this.dolj = dolj;
    }

    public List<Check> getLstemployee() {
        return lstemployee;
    }

    public void setLstemployee(List<Check> lstemployee) {
        this.lstemployee = lstemployee;
    }


    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public List<Korzina> getLstkorzinaEMP() {
        return lstkorzinaEMP;
    }

    public void setLstkorzinaEMP(List<Korzina> lstkorzinaEMP) {
        this.lstkorzinaEMP = lstkorzinaEMP;
    }
}
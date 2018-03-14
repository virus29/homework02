package com.i.homework02.organization;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import com.i.homework02.office.Office;
import com.i.homework02.office.OfficeView;

import javax.persistence.*;
import javax.persistence.criteria.Expression;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity(name = "Organization")
public class Organization {
//    @NotNull
    @JsonView(OrganizationView.FindById.class)
    @Id
    @GeneratedValue
    @Column(name = "Id")
    private Long id;

//Служебное поле hibernate
    @Transient
    @Version
    private Integer version=0;

// Краткое название организации
@JsonView(OrganizationView.FindById.class)
    @NotNull
    private String name;

//Полное название оранизации
@JsonView(OrganizationView.FindById.class)
    private String fullName;

//ИНН организации
@JsonView(OrganizationView.FindById.class)
    private Long inn;

//КПП организации
@JsonView(OrganizationView.FindById.class)
    private Long kpp;

//Адрес организации
@JsonView(OrganizationView.FindById.class)
    private String address;

//Телефон организации
@JsonView(OrganizationView.FindById.class)
    private String phone;

//Активная ли организация
@JsonProperty
@JsonView(OrganizationView.FindById.class)
    private Boolean isActive;

    @JsonView(OrganizationView.FindById.class)
private Long officeId;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "organization", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Office> offices;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Long getInn() {
        return inn;
    }

    public void setInn(Long inn) {
        this.inn = inn;
    }

    public Long getKpp() {
        return kpp;
    }

    public void setKpp(Long kpp) {
        this.kpp = kpp;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public List<Office> getOffices() {
        return offices;
    }

    public void setOffices(List<Office> offices) {
        this.offices = offices;
    }

    public void setOfficeId(Long officeId) {
        this.officeId = officeId;
    }
}

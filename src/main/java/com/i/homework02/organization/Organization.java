package com.i.homework02.organization;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import com.i.homework02.office.Office;
import com.i.homework02.office.OfficeView;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity(name = "Organization")
public class Organization {
    @NotNull
    @JsonView(OfficeView.OfficeFindById.class)
    @Id
    @GeneratedValue
    @Column(name = "Id")
    private Long id;

//Служебное поле hibernate
    @Transient
    @Version
    private Integer version;

// Краткое название организации
    @NotNull
    private String organizationName;

//Полное название оранизации
    private String organizationFullname;

//ИНН организации
    private Long organizationInn;

//КПП организации
    private Long organizationKpp;

//Адрес организации
    private String organizationAddress;

//Телефон организации
    private String organizationPhone;

//Активная ли организация
@JsonProperty
    private Boolean organizationIsactive;

    @Transient
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "organization", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Office> offices;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public String getOrganizationFullname() {
        return organizationFullname;
    }

    public void setOrganizationFullname(String organizationFullname) {
        this.organizationFullname = organizationFullname;
    }

    public Long getOrganizationInn() {
        return organizationInn;
    }

    public void setOrganizationInn(Long organizationInn) {
        this.organizationInn = organizationInn;
    }

    public Long getOrganizationKpp() {
        return organizationKpp;
    }

    public void setOrganizationKpp(Long organizationKpp) {
        this.organizationKpp = organizationKpp;
    }

    public String getOrganizationAddress() {
        return organizationAddress;
    }

    public void setOrganizationAddress(String organizationAddress) {
        this.organizationAddress = organizationAddress;
    }

    public String getOrganizationPhone() {
        return organizationPhone;
    }

    public void setOrganizationPhone(String organizationPhone) {
        this.organizationPhone = organizationPhone;
    }

    public Boolean getOrganizationIsactive() {
        return organizationIsactive;
    }

    public void setOrganizationIsactive(Boolean organizationIsactive) {
        this.organizationIsactive = organizationIsactive;
    }

    public List<Office> getOffices() {
        return offices;
    }

    public void setOffices(List<Office> offices) {
        this.offices = offices;
    }

    @Override
    public String toString() {
        return "Organization{" +
                "id=" + id +
                ", organizationName='" + organizationName + '\'' +
                ", organizationFullname='" + organizationFullname + '\'' +
                ", organizationInn=" + organizationInn +
                ", organizationKpp=" + organizationKpp +
                ", organizationAddress='" + organizationAddress + '\'' +
                ", organizationPhone='" + organizationPhone + '\'' +
                ", organizationIsactive=" + organizationIsactive +
                ", offices=" + offices +
                '}';
    }
}

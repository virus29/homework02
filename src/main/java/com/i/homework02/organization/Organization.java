package com.i.homework02.organization;

import com.i.homework02.office.Office;

import javax.persistence.*;
import java.util.List;

@Entity(name = "Organization")
public class Organization {

    @Id
    @GeneratedValue
    @Column(name = "Id")
    private Long id;

//Служебное поле hibernate
    @Version
    private Integer version;

// Краткое название организации
    @Basic(optional = false)
    private String organizationName;

//Полное название оранизации
    @Basic(optional = false)
    private String organizationFullname;

//ИНН организации
    @Basic(optional = false)
    private String organizationInn;

//КПП организации
    @Basic(optional = false)
    private String organizationKpp;

//Адрес организации
    @Basic(optional = false)
    private String organizationAddress;

//Телефон организации
    @Basic(optional = false)
    private String organizationPhone;

//Активная ли организация
    @Basic(optional = false)
    private boolean organizationIsactive;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "organization", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Office> offices;

    public Long getId() {
        return id;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public String getOrganizationFullname() {
        return organizationFullname;
    }

    public String getOrganizationInn() {
        return organizationInn;
    }

    public String getOrganizationKpp() {
        return organizationKpp;
    }

    public String getOrganizationAddress() {
        return organizationAddress;
    }

    public String getOrganizationPhone() {
        return organizationPhone;
    }

    public boolean isOrganizationIsactive() {
        return organizationIsactive;
    }

    public List<Office> getOffices() {
        return offices;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public void setOrganizationFullname(String organizationFullname) {
        this.organizationFullname = organizationFullname;
    }

    public void setOrganizationInn(String organizationInn) {
        this.organizationInn = organizationInn;
    }

    public void setOrganizationKpp(String organizationKpp) {
        this.organizationKpp = organizationKpp;
    }

    public void setOrganizationAddress(String organizationAddress) {
        this.organizationAddress = organizationAddress;
    }

    public void setOrganizationPhone(String organizationPhone) {
        this.organizationPhone = organizationPhone;
    }

    public void setOrganizationIsactive(boolean organizationIsactive) {
        this.organizationIsactive = organizationIsactive;
    }

    public void setOffices(List<Office> offices) {
        this.offices = offices;
    }
}

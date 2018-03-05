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

    /**
     * Служебное поле hibernate
     */
    @Version
    private Integer version;

    /**
     * краткое название организации
     */
    @Basic(optional = false)
    @Column(name = "organization_name")
    private String name;

    /**
     * Полное название оранизации
     */
    @Basic(optional = false)
    @Column(name = "organization_fullName")
    private String fullName;

    /**
     * ИНН организации
     */
    @Basic(optional = false)
    @Column(name = "organization_inn")
    private String inn;

    /**
     * КПП организации
     */
    @Basic(optional = false)
    @Column(name = "organization_kpp")
    private String kpp;

    /**
     * Адрес организации
     */
    @Basic(optional = false)
    @Column(name = "organization_address")
    private String address;

    /**
     * Телефон организации
     */
    @Basic(optional = false)
    @Column(name = "organization_phone")
    private String phone;

    /**
     * Активная ли организация
     */
    @Basic(optional = false)
    @Column(name = "organization_isActive")
    private boolean isActive;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "organization", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Office> offices;

    /**
     * Конструктор для hibernate
     */

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

    public String getInn() {
        return inn;
    }

    public void setInn(String inn) {
        this.inn = inn;
    }

    public String getKpp() {
        return kpp;
    }

    public void setKpp(String kpp) {
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

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public List<Office> getOffices() {
        return offices;
    }

    public void setOffices(List<Office> offices) {
        this.offices = offices;
    }
}

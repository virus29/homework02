package com.i.homework02.office;

import com.i.homework02.organization.Organization;
import com.i.homework02.user.User;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Office")
public class Office {
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
     * Название офиса
     */
    @Basic(optional = false)
    @Column(name = "office_name")
    private String name;

    /**
     * Адрес
     */
    @Basic(optional = false)
    @Column(name = "office_address")
    private String address;

    /**
     * Телефон
     */
    @Basic(optional = false)
    @Column(name = "office_phone")
    private String phone;

    /**
     * Активен ли офис
     */
    @Basic(optional = false)
    @Column(name = "office_isActive")
    private boolean isActive;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "office", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<User> users;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "organization_id")
    private Organization organization;

    /**
     * Конструктор для hibernate
     */
    public Office() {
    }

    public Office(String name, String address, String phone, boolean isActive, Organization organization) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.isActive = isActive;
//        this.users = users;
        this.organization = organization;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }
}

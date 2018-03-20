package com.i.homework02.office;

import com.i.homework02.organization.Organization;
import com.i.homework02.user.User;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Basic;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Version;
import javax.persistence.CascadeType;
import javax.persistence.ManyToOne;

import java.util.List;

@Entity
@Table(name = "Office")
public class Office {
    /**
     * Id офиса
     */
    @Id
    @GeneratedValue
    private Long id;

//Служебное поле hibernate
    @Version
    private Integer version=1;

// Название офиса
//    @Basic(optional = false)
    private String name;

//Адрес
    @Basic(optional = false)
    private String address;

    private Long orgId;

//Телефон
    private String phone;

//Активен ли офис
    private Boolean isActive;


    @OneToMany(fetch = FetchType.LAZY, mappedBy = "office", cascade = {CascadeType.ALL}, orphanRemoval = true)
    private List<User> users;

    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "organization_id")
    private Organization organization;

    public Long getId() {
        return id;
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

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
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

    public Long getOrgId() {
        return orgId;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }


}

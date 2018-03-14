package com.i.homework02.office;

import com.fasterxml.jackson.annotation.JsonView;
import com.i.homework02.organization.Organization;
import com.i.homework02.organization.OrganizationView;
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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import java.util.List;

@Entity
@Table(name = "Office")
public class Office {
    @JsonView({OfficeView.OfficeFindById.class,OfficeView.OfficeFindByOrgidOffnameOffPhoneOffisactive.class})
    @Id
    @GeneratedValue
    private Long id;

//Служебное поле hibernate
    @Version
    private Integer version=0;

// Название офиса
    @JsonView({OfficeView.OfficeFindById.class,OfficeView.OfficeFindByOrgidOffnameOffPhoneOffisactive.class})
    @Basic(optional = false)
    private String name;

//Адрес
    @Basic(optional = false)
    private String address;

//Телефон
    @JsonView({OfficeView.OfficeFindById.class})
    @Basic(optional = false)
    private String phone;

//Активен ли офис
    @JsonView({OfficeView.OfficeFindById.class,OfficeView.OfficeFindByOrgidOffnameOffPhoneOffisactive.class})
    @Basic(optional = false)
    private Boolean isActive;


    @OneToMany(fetch = FetchType.LAZY, mappedBy = "office", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<User> users;

//    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "organization_id")
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
}

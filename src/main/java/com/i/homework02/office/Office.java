package com.i.homework02.office;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import com.i.homework02.organization.Organization;
import com.i.homework02.user.User;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
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
    private Integer version;

// Название офиса
    @JsonView({OfficeView.OfficeFindById.class,OfficeView.OfficeFindByOrgidOffnameOffPhoneOffisactive.class})
    @Basic(optional = false)
    private String officeName;

//Адрес
    @JsonView({OfficeView.OfficeFindById.class})
    @Basic(optional = false)
    private String officeAddress;

//Телефон
    @JsonView({OfficeView.OfficeFindById.class})
    @Basic(optional = false)
    private String officePhone;

//Активен ли офис
    @JsonView({OfficeView.OfficeFindById.class,OfficeView.OfficeFindByOrgidOffnameOffPhoneOffisactive.class})
    @Basic(optional = false)
    private Boolean officeIsactive;


    @OneToMany(fetch = FetchType.LAZY, mappedBy = "office", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<User> users;

    @JsonView({OfficeView.OfficeFindById.class})
//    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "organization_id")
    private Organization organization;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOfficeName() {
        return officeName;
    }

    public void setOfficeName(String officeName) {
        this.officeName = officeName;
    }

    public String getOfficeAddress() {
        return officeAddress;
    }

    public void setOfficeAddress(String officeAddress) {
        this.officeAddress = officeAddress;
    }

    public String getOfficePhone() {
        return officePhone;
    }

    public void setOfficePhone(String officePhone) {
        this.officePhone = officePhone;
    }

    public Boolean isOfficeIsactive() {
        return officeIsactive;
    }

    public void setOfficeIsactive(Boolean officeIsactive) {
        this.officeIsactive = officeIsactive;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}

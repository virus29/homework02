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

//Служебное поле hibernate
    @Version
    private Integer version;

// Название офиса
    @Basic(optional = false)

    private String officeName;

//Адрес
    @Basic(optional = false)
    private String officeAddress;

//Телефон
    @Basic(optional = false)
    @Column(name = "office_phone")
    private String phone;

//Активен ли офис
    @Basic(optional = false)
    @Column(name = "office_isactive")
    private boolean isactive;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "office", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<User> users;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "organization_id")
    private Organization organization;

    public Long getId() {
        return id;
    }

    public String getOfficeName() {
        return officeName;
    }

    public String getOfficeAddress() {
        return officeAddress;
    }

    public String getPhone() {
        return phone;
    }

    public boolean isIsactive() {
        return isactive;
    }

    public List<User> getUsers() {
        return users;
    }

    public Organization getOrganization() {
        return organization;
    }
}

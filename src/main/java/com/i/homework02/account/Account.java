package com.i.homework02.account;

import javax.persistence.*;

@Entity
@Table(name = "Account")
public class Account {
    @Id
    @GeneratedValue
    @Column(name = "Id")
    private Long id;

    //Служебное поле hibernate
    @Version
    private Integer version;

    // LogIn
    @Basic(optional = false)
    private String login;

    //Password
    @Basic(optional = false)
    private String password;

    //Account name
    @Basic(optional = false)
    private String name;

    //Role
    @Basic(optional = false)
    private String role;

    //Is active account?
    @Basic(optional = false)
    private Boolean isactive;

    //Activation code
    private String activationCode;

    public Long getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getRole() {
        return role;
    }

    public Boolean getIsactive() {
        return isactive;
    }

    public String getActivationCode() {
        return activationCode;
    }
}

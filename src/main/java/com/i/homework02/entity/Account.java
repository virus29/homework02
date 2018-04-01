package com.i.homework02.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.Email;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;

@Entity
@Table
public class Account {

    /**
     * Id аккаунта
     */
    @Id
    @GeneratedValue
    private Long id;

    /**
     * Служебное поле hibernate
     */
    @Version
    private Integer version;


    /**
     * Логин(представлен ввиде Email)
     */
    @Email
    @NotNull
    private String login;

    /**
     * Пароль аккаунта
     */
    @NotNull
    private String password;

    /**
     * Имя аккаунта
     */
    private String name;

    /**
     * Активный ли аккаунт?
     */
    private Boolean isActive=false;

    /**
     * Активационный код
     */
    private String activationCode;

    /**
     * Иммитация полученного кода активации на электронную почту
     */
    private String code;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public String getActivationCode() {
        return activationCode;
    }

    public void setActivationCode(String activationCode) {
        this.activationCode = activationCode;
    }
}

package com.i.homework02.user;

import com.i.homework02.country.Country;
import com.i.homework02.document.Document;
import com.i.homework02.office.Office;

import javax.persistence.*;

@Entity
@Table(name = "User")
public class User {
    @Id
    @GeneratedValue
    @Column(name = "Id")
    private Long id;

//Служебное поле hibernate
    @Version
    private Integer version;

//Имя пользователя
    @Basic(optional = false)
    private String userFirstname;

//Фамилия пользователя
    @Basic(optional = false)
    private String userLastname;

//Отчество пользователя
    @Basic(optional = false)
    private String userMiddlename;

    //Занимаемая должность
    @Basic(optional = false)
    private String userPosition;

    //Телефон
    @Basic(optional = false)
    private String userPhone;

//Номер документа пользователя
    @Basic(optional = false)
    private String userDocnumber;

//Дата выдачи документа пользователя
    @Basic(optional = false)
    private String userDocdate;

//Идентифицирован ли пользователь
    @Basic(optional = false)
    private String userIsidentified;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "office_id")
    private Office office;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_citizenshipcode")
    private Country country;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_doccode")
    private Document document;

    public Long getId() {
        return id;
    }

    public String getUserFirstname() {
        return userFirstname;
    }

    public String getUserLastname() {
        return userLastname;
    }

    public String getUserMiddlename() {
        return userMiddlename;
    }

    public String getUserPosition() {
        return userPosition;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public String getUserDocnumber() {
        return userDocnumber;
    }

    public String getUserDocdate() {
        return userDocdate;
    }

    public String getUserIsidentified() {
        return userIsidentified;
    }

    public Office getOffice() {
        return office;
    }

    public Country getCountry() {
        return country;
    }

    public Document getDocument() {
        return document;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUserFirstname(String userFirstname) {
        this.userFirstname = userFirstname;
    }

    public void setUserLastname(String userLastname) {
        this.userLastname = userLastname;
    }

    public void setUserMiddlename(String userMiddlename) {
        this.userMiddlename = userMiddlename;
    }

    public void setUserPosition(String userPosition) {
        this.userPosition = userPosition;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public void setUserDocnumber(String userDocnumber) {
        this.userDocnumber = userDocnumber;
    }

    public void setUserDocdate(String userDocdate) {
        this.userDocdate = userDocdate;
    }

    public void setUserIsidentified(String userIsidentified) {
        this.userIsidentified = userIsidentified;
    }

    public void setOffice(Office office) {
        this.office = office;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public void setDocument(Document document) {
        this.document = document;
    }
}

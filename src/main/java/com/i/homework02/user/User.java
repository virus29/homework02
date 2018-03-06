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
    private String userSecondname;

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

    public String getUserSecondname() {
        return userSecondname;
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
}

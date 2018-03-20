package com.i.homework02.user;

import com.fasterxml.jackson.annotation.JsonView;
import com.i.homework02.country.Country;
import com.i.homework02.doctype.DocType;
import com.i.homework02.office.Office;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "User")
public class User {
    @JsonView(UserView.FindById.class)
    @Id
    @GeneratedValue
    @Column(name = "Id")
    private Long id;

//Служебное поле hibernate
    @Version
    private Integer version=1;

//Имя пользователя
    @Basic(optional = false)
    private String firstName;

//Фамилия пользователя
    @Basic(optional = false)
    private String secondName;

//Отчество пользователя
    @Basic(optional = false)
    private String middleName;

    //Занимаемая должность
    @Basic(optional = false)
    private String position;

    //Телефон
    @Basic(optional = false)
    private String phone;

//Номер документа пользователя
    @Basic(optional = false)
    private String docNumber;

//Дата выдачи документа пользователя
    @JsonView(UserView.FindById.class)
    @Basic(optional = false)
    @Temporal(TemporalType.DATE)
    private Date docDate;

//Идентифицирован ли пользователь
    @Basic(optional = false)
    private String isIdentified;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "office_id")
    private Office office;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "citizenship_code")
    private Country country;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doc_code")
    private DocType docType;


    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDocNumber() {
        return docNumber;
    }

    public void setDocNumber(String docNumber) {
        this.docNumber = docNumber;
    }

    public Date getDocDate() {
        return docDate;
    }

    public void setDocDate(Date docDate) {
        this.docDate = docDate;
    }

    public String getIsIdentified() {
        return isIdentified;
    }

    public void setIsIdentified(String isIdentified) {
        this.isIdentified = isIdentified;
    }

    public Office getOffice() {
        return office;
    }

    public void setOffice(Office office) {
        this.office = office;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public DocType getDocType() {
        return docType;
    }

    public void setDocType(DocType docType) {
        this.docType = docType;
    }
}

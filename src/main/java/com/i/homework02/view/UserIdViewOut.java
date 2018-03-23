package com.i.homework02.view;

import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;
import java.util.Date;

public class UserIdViewOut {

    //Id пользователя
    private Long id;

    //Служебное поле hibernate
    @Version
    private Integer version=1;

    //Имя пользователя
    private String firstName;

    //Фамилия пользователя
    private String secondName;

    //Отчество пользователя
    private String middleName;

    //Занимаемая должность
    private String position;

    //Телефон
    private String phone;

    //Номер документа пользователя
    private String docName;

    //Номер документа пользователя
    private String docNumber;

    //Дата выдачи документа пользователя
    @Temporal(TemporalType.DATE)
    private Date docDate;

    //Страна гражданства
    private String citizenshipName;

    //Код страны гражданства
    private String citizenshipCode;

    //Идентифицирован ли пользователь
    private Boolean isIdentified;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getDocName() {
        return docName;
    }

    public void setDocName(String docName) {
        this.docName = docName;
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

    public String getCitizenshipName() {
        return citizenshipName;
    }

    public void setCitizenshipName(String citizenshipName) {
        this.citizenshipName = citizenshipName;
    }

    public String getCitizenshipCode() {
        return citizenshipCode;
    }

    public void setCitizenshipCode(String citizenshipCode) {
        this.citizenshipCode = citizenshipCode;
    }

    public Boolean getIsIdentified() {
        return isIdentified;
    }

    public void setIsIdentified(Boolean isIdentified) {
        this.isIdentified = isIdentified;
    }
}

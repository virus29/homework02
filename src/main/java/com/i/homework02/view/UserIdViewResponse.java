package com.i.homework02.view;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

public class UserIdViewResponse extends UserListViewResponse{

    /**
     * Телефон
     */
    private String phone;

    /**
     * Номер документа пользователя
     */
    private String docName;

    /**
     * /Код документа пользователя
     */
    private String docCode;

    /**
     * Номер документа пользователя
     */
    private String docNumber;

    /**
     * Дата выдачи документа пользователя
     */
    @Temporal(TemporalType.DATE)
    private Date docDate;

    /**
     * Страна гражданства
     */
    private String citizenshipName;

    /**
     * Код страны гражданства
     */
    private String citizenshipCode;
    
    /**
     * Идентифицирован ли пользователь
     */
    private Boolean isIdentified;


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

    public String getDocCode() {
        return docCode;
    }

    public void setDocCode(String docCode) {
        this.docCode = docCode;
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

    public Boolean getIdentified() {
        return isIdentified;
    }

    @JsonProperty(value = "isIdentified")
    public void setIdentified(Boolean identified) {
        isIdentified = identified;
    }
}

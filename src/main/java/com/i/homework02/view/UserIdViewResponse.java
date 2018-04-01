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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        UserIdViewResponse that = (UserIdViewResponse) o;

        if (phone != null ? !phone.equals(that.phone) : that.phone != null) return false;
        if (docName != null ? !docName.equals(that.docName) : that.docName != null) return false;
        if (docCode != null ? !docCode.equals(that.docCode) : that.docCode != null) return false;
        if (docNumber != null ? !docNumber.equals(that.docNumber) : that.docNumber != null) return false;
        if (docDate != null ? !docDate.equals(that.docDate) : that.docDate != null) return false;
        if (citizenshipName != null ? !citizenshipName.equals(that.citizenshipName) : that.citizenshipName != null)
            return false;
        if (citizenshipCode != null ? !citizenshipCode.equals(that.citizenshipCode) : that.citizenshipCode != null)
            return false;
        return isIdentified != null ? isIdentified.equals(that.isIdentified) : that.isIdentified == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (docName != null ? docName.hashCode() : 0);
        result = 31 * result + (docCode != null ? docCode.hashCode() : 0);
        result = 31 * result + (docNumber != null ? docNumber.hashCode() : 0);
        result = 31 * result + (docDate != null ? docDate.hashCode() : 0);
        result = 31 * result + (citizenshipName != null ? citizenshipName.hashCode() : 0);
        result = 31 * result + (citizenshipCode != null ? citizenshipCode.hashCode() : 0);
        result = 31 * result + (isIdentified != null ? isIdentified.hashCode() : 0);
        return result;
    }
}

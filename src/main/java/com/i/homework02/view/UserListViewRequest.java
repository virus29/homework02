package com.i.homework02.view;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.validation.constraints.NotNull;

public class UserListViewRequest extends UserListViewResponse{

    /**
     * Id Офиса пользователя
     */
    @NotNull
    private Long officeId;

    @JsonIgnore
    private Long id;

    /**
     * Код документа
     */
    private String docCode;

    /**
     * Код страны гражданства
     */
    private String citizenshipCode;

    public Long getOfficeId() {
        return officeId;
    }

    public void setOfficeId(Long officeId) {
        this.officeId = officeId;
    }

    public String getDocCode() {
        return docCode;
    }

    public void setDocCode(String docCode) {
        this.docCode = docCode;
    }

    public String getCitizenshipCode() {
        return citizenshipCode;
    }

    public void setCitizenshipCode(String citizenshipCode) {
        this.citizenshipCode = citizenshipCode;
    }
}

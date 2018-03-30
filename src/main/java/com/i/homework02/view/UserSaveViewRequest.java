package com.i.homework02.view;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.validation.constraints.NotNull;

public class UserSaveViewRequest extends UserIdViewResponse {
    @JsonIgnore
    private Long id;

    @JsonIgnore
    private String docName;

    @JsonIgnore
    private String citizenshipName;

    @NotNull
    private Long officeId;

    public Long getOfficeId() {
        return officeId;
    }

    public void setOfficeId(Long officeId) {
        this.officeId = officeId;
    }
}

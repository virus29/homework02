package com.i.homework02.view;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.validation.constraints.NotNull;

public class UserUpdateViewRequest extends UserIdViewResponse{
    /**
     * Id пользователя
     */
    @NotNull
    private Long id;

    /**
     * Номер документа пользователя
     */
    @JsonIgnore
    private String docName;

    /**
     * Страна гражданства
     */
    @JsonIgnore
    private String citizenshipName;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }
}

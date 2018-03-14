package com.i.homework02.document;

import com.fasterxml.jackson.annotation.JsonView;
import com.i.homework02.user.UserView;

import javax.persistence.*;

@Entity(name = "Doc_type")
public class DocType {

//Служебное поле hibernate
    @Version
    private Integer version=0;

//Код документа
    @Id
    @Basic(optional = false)
    @JsonView({DocTypeView.DocumentList.class,UserView.FindById.class})
    private String code;

//Название документа
    @Basic(optional = false)
    @JsonView({DocTypeView.DocumentList.class,UserView.FindById.class})
    private String name;


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
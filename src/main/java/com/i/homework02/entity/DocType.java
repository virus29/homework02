package com.i.homework02.entity;

import javax.persistence.*;

@Entity(name = "Doc_type")
public class DocType {

    /**
     * Служебное поле hibernate
     */
    @Version
    private Integer version=1;

    /**
     * Код документа
     */
    @Id
    @Basic(optional = false)
    private String code;

    /**
     * Название документа
     */
    @Basic(optional = false)
    private String name;

    public DocType() {}

    public DocType(String code, String name) {
        this.code = code;
        this.name = name;
    }

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
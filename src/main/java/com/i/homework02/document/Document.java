package com.i.homework02.document;

import com.i.homework02.user.User;

import javax.persistence.*;
import java.util.List;

@Entity(name = "Document")
public class Document {


    /**
     * Служебное поле hibernate
     */
    @Version
    private Integer version;

    /**
     * Код документа
     */
    @Id
    @Basic(optional = false)
    @Column(name = "document_code")
    private int code;

    /**
     * Название документа
     */
    @Basic(optional = false)
    @Column(name = "document_name")
    private String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "document", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<User> users;

    public Document() {
    }

    public Document(int code, String name, List<User> users) {
        this.code = code;
        this.name = name;
        this.users = users;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
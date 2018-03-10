package com.i.homework02.document;

import com.i.homework02.user.User;

import javax.persistence.*;
import java.util.List;

@Entity(name = "Document")
public class Document {


//Служебное поле hibernate
    @Version
    private Integer version;

//Код документа
    @Id
    @Basic(optional = false)
    private Long documentCode;

//Название документа
    @Basic(optional = false)
    private String documentName;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "document", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<User> users;

    public Long getDocumentCode() {
        return documentCode;
    }

    public void setDocumentCode(Long documentCode) {
        this.documentCode = documentCode;
    }

    public String getDocumentName() {
        return documentName;
    }

    public void setDocumentName(String documentName) {
        this.documentName = documentName;
    }
}
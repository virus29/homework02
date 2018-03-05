package com.i.homework02.user;

import com.i.homework02.country.Country;
import com.i.homework02.document.Document;
import com.i.homework02.office.Office;

import javax.persistence.*;

@Entity
@Table(name = "User")
public class User {
    @Id
    @GeneratedValue
    @Column(name = "Id")
    private Long id;

//Служебное поле hibernate
    @Version
    private Integer version;


// LogIn
    @Basic(optional = false)
    @Column(name = "login")
    private String login;

    /**
     * Password
     */
    @Basic(optional = false)
    @Column(name = "password")
    private String password;

    /**
     * Имя пользователя
     */
    @Basic(optional = false)
    @Column(name = "user_firstName")
    private String firstName;

    /**
     * Фамилия пользователя
     */
    @Basic(optional = false)
    @Column(name = "user_secondName")
    private String secondName;

    /**
     * Отчество пользователя
     */
    @Basic(optional = false)
    @Column(name = "user_middleName")
    private String middleName;
    /**
     * Занимаемая должность
     */
    @Basic(optional = false)
    @Column(name = "user_position")
    private String position;
    /**
     * Телефон
     */
    @Basic(optional = false)
    @Column(name = "user_phone")
    private String phone;

    /**
     * Номер документа пользователя
     */
    @Basic(optional = false)
    @Column(name = "user_docNumber")
    private String docNumber;

    /**
     * Дата выдачи документа пользователя
     */
    @Basic(optional = false)
    @Column(name = "user_docDate")
    private String docDate;

    /**
     * Идентифицирован ли пользователь
     */
    @Basic(optional = false)
    @Column(name = "user_isIdentified")
    private String isIdentified;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "office_id")
    private Office office;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_citizenshipCode")
    private Country country;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_docCode")
    private Document document;

    public User() {
    }

    /**
     * Конструктор для hibernate
     * @param login
     * @param password
     * @param firstName
     */
    public User(String login, String password, String firstName) {
        this.login = login;
        this.password = password;
        this.firstName = firstName;
    }

    @Override
    public String toString() {
        return "Quote{" +
                "login='" + login + '\'' +
                ", password=" + password + '\'' +
                ", firstName=" + firstName +
                '}';
    }

    public User(String login, String password, String firstName, String secondName, String middleName, String position, String phone, String docNumber, String docDate, String isIdentified, Office office, Document document, Country country) {
        this.login = login;
        this.password = password;
        this.firstName = firstName;
        this.secondName = secondName;
        this.middleName = middleName;
        this.position = position;
        this.phone = phone;
        this.docNumber = docNumber;
        this.docDate = docDate;
        this.isIdentified = isIdentified;
        this.office = office;
        this.country = country;
        this.document = document;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getDocNumber() {
        return docNumber;
    }

    public void setDocNumber(String docNumber) {
        this.docNumber = docNumber;
    }

    public String getDocDate() {
        return docDate;
    }

    public void setDocDate(String docDate) {
        this.docDate = docDate;
    }

    public String getIsIdentified() {
        return isIdentified;
    }

    public void setIsIdentified(String isIdentified) {
        this.isIdentified = isIdentified;
    }

    public Office getOffice() {
        return office;
    }

    public void setOffice(Office office) {
        this.office = office;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public Document getDocument() {
        return document;
    }

    public void setDocument(Document document) {
        this.document = document;
    }
}

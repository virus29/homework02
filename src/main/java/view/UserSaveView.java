package view;

import com.fasterxml.jackson.annotation.JsonView;
import com.i.homework02.user.UserView;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;
import java.util.Date;

public class UserSaveView {

    //Id Офиса пользователя
    private Long officeId;

    //Служебное поле hibernate
//    @Version
//    private Integer version=1;

    //Имя пользователя
    private String firstName;

    //Фамилия пользователя
    private String secondName;

    //Отчество пользователя
    private String middleName;

    //Занимаемая должность
    private String position;

    //Телефон
    private String phone;

    //Номер документа пользователя
    private String docCode;

    //Номер документа пользователя
    private String docNumber;

    //Дата выдачи документа пользователя
    @Temporal(TemporalType.DATE)
    private Date docDate;

    //Страна гражданства
    private String citizenshipCode;

    //Идентифицирован ли пользователь
    private String isIdentified;

    public Long getOfficeId() {
        return officeId;
    }

    public void setOfficeId(Long officeId) {
        this.officeId = officeId;
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

    public String getCitizenshipCode() {
        return citizenshipCode;
    }

    public void setCitizenshipCode(String citizenshipCode) {
        this.citizenshipCode = citizenshipCode;
    }

    public String getIsIdentified() {
        return isIdentified;
    }

    public void setIsIdentified(String isIdentified) {
        this.isIdentified = isIdentified;
    }
}

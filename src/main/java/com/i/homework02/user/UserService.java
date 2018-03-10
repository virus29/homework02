package com.i.homework02.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class UserService {

    @Autowired
    private UserRepository userRepository;

//7 Переменных
    public static Specification<User> findByOfficeOrgidFNameLNameMNamePositionDoccodeCitcode(Long officeId, String firstName, String lastName, String middleName, String position, Long docCode, Long citizenshipCode) {
        return (r, cq, cb) -> cb.and(
                cb.equal(r.join("office").get("id"), officeId),
                cb.equal(r.get("userFirstname"), firstName),
                cb.equal(r.get("userLastName"), lastName),
                cb.equal(r.get("userMiddleName"), middleName),
                cb.equal(r.get("userPosition"), position),
                cb.equal(r.join("document").get("documentCode"), docCode),
                cb.equal(r.join("country").get("countryCode"), citizenshipCode));
    }
//6 Переменных
//123456
    public static Specification<User> findByOfficeOrgidFNameLNameMNamePositionDoccode(Long officeId, String firstName, String lastName, String middleName, String position, Long docCode) {
    return (r, cq, cb) -> cb.and(
            cb.equal(r.join("office").get("id"), officeId),
            cb.equal(r.get("userFirstname"), firstName),
            cb.equal(r.get("userLastName"), lastName),
            cb.equal(r.get("userMiddleName"), middleName),
            cb.equal(r.get("userPosition"), position),
            cb.equal(r.join("document").get("documentCode"), docCode));
}
//123457
    public static Specification<User> findByOfficeOrgidFNameLNameMNamePositionCitcode(Long officeId, String firstName, String lastName, String middleName, String position, Long citizenshipCode) {
    return (r, cq, cb) -> cb.and(
            cb.equal(r.join("office").get("id"), officeId),
            cb.equal(r.get("userFirstname"), firstName),
            cb.equal(r.get("userLastName"), lastName),
            cb.equal(r.get("userMiddleName"), middleName),
            cb.equal(r.get("userPosition"), position),
            cb.equal(r.join("country").get("countryCode"), citizenshipCode));
}
//123467
    public static Specification<User> findByOfficeOrgidFNameLNameMNameDoccodeCitcode(Long officeId, String firstName, String lastName, String middleName, Long docCode, Long citizenshipCode) {
    return (r, cq, cb) -> cb.and(
            cb.equal(r.join("office").get("id"), officeId),
            cb.equal(r.get("userFirstname"), firstName),
            cb.equal(r.get("userLastName"), lastName),
            cb.equal(r.get("userMiddleName"), middleName),
            cb.equal(r.join("document").get("documentCode"), docCode),
            cb.equal(r.join("country").get("countryCode"), citizenshipCode));
}
//123567
    public static Specification<User> findByOfficeOrgidFNameLNamePositionDoccodeCitcode(Long officeId, String firstName, String lastName, String position, Long docCode, Long citizenshipCode) {
    return (r, cq, cb) -> cb.and(
            cb.equal(r.join("office").get("id"), officeId),
            cb.equal(r.get("userFirstname"), firstName),
            cb.equal(r.get("userLastName"), lastName),
            cb.equal(r.get("userPosition"), position),
            cb.equal(r.join("document").get("documentCode"), docCode),
            cb.equal(r.join("country").get("countryCode"), citizenshipCode));
}
//124567
    public static Specification<User> findByOfficeOrgidFNameMNamePositionDoccodeCitcode(Long officeId, String firstName, String middleName, String position, Long docCode, Long citizenshipCode) {
    return (r, cq, cb) -> cb.and(
            cb.equal(r.join("office").get("id"), officeId),
            cb.equal(r.get("userFirstname"), firstName),
            cb.equal(r.get("userMiddleName"), middleName),
            cb.equal(r.get("userPosition"), position),
            cb.equal(r.join("document").get("documentCode"), docCode),
            cb.equal(r.join("country").get("countryCode"), citizenshipCode));
}
//134567
    public static Specification<User> findByOfficeOrgidLNameMNamePositionDoccodeCitcode(Long officeId, String lastName, String middleName, String position, Long docCode, Long citizenshipCode) {
    return (r, cq, cb) -> cb.and(
            cb.equal(r.join("office").get("id"), officeId),
            cb.equal(r.get("userLastName"), lastName),
            cb.equal(r.get("userMiddleName"), middleName),
            cb.equal(r.get("userPosition"), position),
            cb.equal(r.join("document").get("documentCode"), docCode),
            cb.equal(r.join("country").get("countryCode"), citizenshipCode));
}
//5 Переменных
//12345
public static Specification<User> findByOfficeOrgidFNameLNameMNamePosition(Long officeId, String firstName, String lastName, String middleName, String position) {
    return (r, cq, cb) -> cb.and(
            cb.equal(r.join("office").get("id"), officeId),
            cb.equal(r.get("userFirstname"), firstName),
            cb.equal(r.get("userLastName"), lastName),
            cb.equal(r.get("userMiddleName"), middleName),
            cb.equal(r.get("userPosition"), position));
}

//12346
public static Specification<User> findByOfficeOrgidFNameLNameMNameDoccode(Long officeId, String firstName, String lastName, String middleName, Long docCode) {
    return (r, cq, cb) -> cb.and(
            cb.equal(r.join("office").get("id"), officeId),
            cb.equal(r.get("userFirstname"), firstName),
            cb.equal(r.get("userLastName"), lastName),
            cb.equal(r.get("userMiddleName"), middleName),
            cb.equal(r.join("document").get("documentCode"), docCode));
}

//12347
public static Specification<User> findByOfficeOrgidFNameLNameMNameCitcode(Long officeId, String firstName, String lastName, String middleName, Long citizenshipCode) {
    return (r, cq, cb) -> cb.and(
            cb.equal(r.join("office").get("id"), officeId),
            cb.equal(r.get("userFirstname"), firstName),
            cb.equal(r.get("userLastName"), lastName),
            cb.equal(r.get("userMiddleName"), middleName),
            cb.equal(r.get("userPosition"), position),
            cb.equal(r.join("document").get("documentCode"), docCode),
            cb.equal(r.join("country").get("countryCode"), citizenshipCode));
}

//12356
public static Specification<User> findByOfficeOrgidFNameLNamePositionDoccode(Long officeId, String firstName, String lastName, String position, Long docCode) {
    return (r, cq, cb) -> cb.and(
            cb.equal(r.join("office").get("id"), officeId),
            cb.equal(r.get("userFirstname"), firstName),
            cb.equal(r.get("userLastName"), lastName),
            cb.equal(r.get("userMiddleName"), middleName),
            cb.equal(r.get("userPosition"), position),
            cb.equal(r.join("document").get("documentCode"), docCode),
            cb.equal(r.join("country").get("countryCode"), citizenshipCode));
}

//12357
public static Specification<User> findByOfficeOrgidFNameLNamePositionCitcode(Long officeId, String firstName, String lastName, String position, Long citizenshipCode) {
    return (r, cq, cb) -> cb.and(
            cb.equal(r.join("office").get("id"), officeId),
            cb.equal(r.get("userFirstname"), firstName),
            cb.equal(r.get("userLastName"), lastName),
            cb.equal(r.get("userMiddleName"), middleName),
            cb.equal(r.get("userPosition"), position),
            cb.equal(r.join("document").get("documentCode"), docCode),
            cb.equal(r.join("country").get("countryCode"), citizenshipCode));
}

//12367
public static Specification<User> findByOfficeOrgidFNameLNameDoccodeCitcode(Long officeId, String firstName, String lastName, Long docCode, Long citizenshipCode) {
    return (r, cq, cb) -> cb.and(
            cb.equal(r.join("office").get("id"), officeId),
            cb.equal(r.get("userFirstname"), firstName),
            cb.equal(r.get("userLastName"), lastName),
            cb.equal(r.get("userMiddleName"), middleName),
            cb.equal(r.get("userPosition"), position),
            cb.equal(r.join("document").get("documentCode"), docCode),
            cb.equal(r.join("country").get("countryCode"), citizenshipCode));
}

//12456
public static Specification<User> findByOfficeOrgidFNameMNamePositionDoccode(Long officeId, String firstName, String middleName, String position, Long docCode) {
    return (r, cq, cb) -> cb.and(
            cb.equal(r.join("office").get("id"), officeId),
            cb.equal(r.get("userFirstname"), firstName),
            cb.equal(r.get("userLastName"), lastName),
            cb.equal(r.get("userMiddleName"), middleName),
            cb.equal(r.get("userPosition"), position),
            cb.equal(r.join("document").get("documentCode"), docCode),
            cb.equal(r.join("country").get("countryCode"), citizenshipCode));
}

//12457
public static Specification<User> findByOfficeOrgidFNameMNamePositionCitcode(Long officeId, String firstName, String middleName, String position, Long citizenshipCode) {
    return (r, cq, cb) -> cb.and(
            cb.equal(r.join("office").get("id"), officeId),
            cb.equal(r.get("userFirstname"), firstName),
            cb.equal(r.get("userLastName"), lastName),
            cb.equal(r.get("userMiddleName"), middleName),
            cb.equal(r.get("userPosition"), position),
            cb.equal(r.join("document").get("documentCode"), docCode),
            cb.equal(r.join("country").get("countryCode"), citizenshipCode));
}

//12467
public static Specification<User> findByOfficeOrgidFNameMNameDoccodeCitcode(Long officeId, String firstName, String middleName, Long docCode, Long citizenshipCode) {
    return (r, cq, cb) -> cb.and(
            cb.equal(r.join("office").get("id"), officeId),
            cb.equal(r.get("userFirstname"), firstName),
            cb.equal(r.get("userLastName"), lastName),
            cb.equal(r.get("userMiddleName"), middleName),
            cb.equal(r.get("userPosition"), position),
            cb.equal(r.join("document").get("documentCode"), docCode),
            cb.equal(r.join("country").get("countryCode"), citizenshipCode));
}

//12567
public static Specification<User> findByOfficeOrgidFNamePositionDoccodeCitcode(Long officeId, String firstName, String position, Long docCode, Long citizenshipCode) {
    return (r, cq, cb) -> cb.and(
            cb.equal(r.join("office").get("id"), officeId),
            cb.equal(r.get("userFirstname"), firstName),
            cb.equal(r.get("userLastName"), lastName),
            cb.equal(r.get("userMiddleName"), middleName),
            cb.equal(r.get("userPosition"), position),
            cb.equal(r.join("document").get("documentCode"), docCode),
            cb.equal(r.join("country").get("countryCode"), citizenshipCode));
}

//13456
public static Specification<User> findByOfficeOrgiLNameMNamePositionDoccode(Long officeId, String lastName, String middleName, String position, Long docCode) {
    return (r, cq, cb) -> cb.and(
            cb.equal(r.join("office").get("id"), officeId),
            cb.equal(r.get("userFirstname"), firstName),
            cb.equal(r.get("userLastName"), lastName),
            cb.equal(r.get("userMiddleName"), middleName),
            cb.equal(r.get("userPosition"), position),
            cb.equal(r.join("document").get("documentCode"), docCode),
            cb.equal(r.join("country").get("countryCode"), citizenshipCode));
}

//13467
public static Specification<User> findByOfficeOrgidLNameMNameDoccodeCitcode(Long officeId, String lastName, String middleName, Long docCode, Long citizenshipCode) {
    return (r, cq, cb) -> cb.and(
            cb.equal(r.join("office").get("id"), officeId),
            cb.equal(r.get("userFirstname"), firstName),
            cb.equal(r.get("userLastName"), lastName),
            cb.equal(r.get("userMiddleName"), middleName),
            cb.equal(r.get("userPosition"), position),
            cb.equal(r.join("document").get("documentCode"), docCode),
            cb.equal(r.join("country").get("countryCode"), citizenshipCode));
}

//13567
public static Specification<User> findByOfficeOrgidLNamePositionDoccodeCitcode(Long officeId, String lastName, String position, Long docCode, Long citizenshipCode) {
    return (r, cq, cb) -> cb.and(
            cb.equal(r.join("office").get("id"), officeId),
            cb.equal(r.get("userFirstname"), firstName),
            cb.equal(r.get("userLastName"), lastName),
            cb.equal(r.get("userMiddleName"), middleName),
            cb.equal(r.get("userPosition"), position),
            cb.equal(r.join("document").get("documentCode"), docCode),
            cb.equal(r.join("country").get("countryCode"), citizenshipCode));
}

//14567
public static Specification<User> findByOfficeOrgidMNamePositionDoccodeCitcode(Long officeId, String middleName, String position, Long docCode, Long citizenshipCode) {
    return (r, cq, cb) -> cb.and(
            cb.equal(r.join("office").get("id"), officeId),
            cb.equal(r.get("userFirstname"), firstName),
            cb.equal(r.get("userLastName"), lastName),
            cb.equal(r.get("userMiddleName"), middleName),
            cb.equal(r.get("userPosition"), position),
            cb.equal(r.join("document").get("documentCode"), docCode),
            cb.equal(r.join("country").get("countryCode"), citizenshipCode));
}

//4 Переменные
//1234
public static Specification<User> findByOfficeOrgidFNameLNameMName(Long officeId, String firstName, String lastName, String middleName) {
    return (r, cq, cb) -> cb.and(
            cb.equal(r.join("office").get("id"), officeId),
            cb.equal(r.get("userFirstname"), firstName),
            cb.equal(r.get("userLastName"), lastName),
            cb.equal(r.get("userMiddleName"), middleName),
            cb.equal(r.get("userPosition"), position),
            cb.equal(r.join("document").get("documentCode"), docCode),
            cb.equal(r.join("country").get("countryCode"), citizenshipCode));
}

//1235
public static Specification<User> findByOfficeOrgidFNameLNamePosition(Long officeId, String firstName, String lastName, String position) {
    return (r, cq, cb) -> cb.and(
            cb.equal(r.join("office").get("id"), officeId),
            cb.equal(r.get("userFirstname"), firstName),
            cb.equal(r.get("userLastName"), lastName),
            cb.equal(r.get("userMiddleName"), middleName),
            cb.equal(r.get("userPosition"), position),
            cb.equal(r.join("document").get("documentCode"), docCode),
            cb.equal(r.join("country").get("countryCode"), citizenshipCode));
}

//1236
public static Specification<User> findByOfficeOrgidFNameLNameDoccode(Long officeId, String firstName, String lastName, Long docCode) {
    return (r, cq, cb) -> cb.and(
            cb.equal(r.join("office").get("id"), officeId),
            cb.equal(r.get("userFirstname"), firstName),
            cb.equal(r.get("userLastName"), lastName),
            cb.equal(r.get("userMiddleName"), middleName),
            cb.equal(r.get("userPosition"), position),
            cb.equal(r.join("document").get("documentCode"), docCode),
            cb.equal(r.join("country").get("countryCode"), citizenshipCode));
}

//1237
public static Specification<User> findByOfficeOrgidFNameLNameCitcode(Long officeId, String firstName, String lastName, Long citizenshipCode) {
    return (r, cq, cb) -> cb.and(
            cb.equal(r.join("office").get("id"), officeId),
            cb.equal(r.get("userFirstname"), firstName),
            cb.equal(r.get("userLastName"), lastName),
            cb.equal(r.get("userMiddleName"), middleName),
            cb.equal(r.get("userPosition"), position),
            cb.equal(r.join("document").get("documentCode"), docCode),
            cb.equal(r.join("country").get("countryCode"), citizenshipCode));
}

//1245
public static Specification<User> findByOfficeOrgidFNameMNamePosition(Long officeId, String firstName, String middleName, String position) {
    return (r, cq, cb) -> cb.and(
            cb.equal(r.join("office").get("id"), officeId),
            cb.equal(r.get("userFirstname"), firstName),
            cb.equal(r.get("userLastName"), lastName),
            cb.equal(r.get("userMiddleName"), middleName),
            cb.equal(r.get("userPosition"), position),
            cb.equal(r.join("document").get("documentCode"), docCode),
            cb.equal(r.join("country").get("countryCode"), citizenshipCode));
}

//1246
public static Specification<User> findByOfficeOrgidFNameMNameDoccode(Long officeId, String firstName, String middleName, Long docCode) {
    return (r, cq, cb) -> cb.and(
            cb.equal(r.join("office").get("id"), officeId),
            cb.equal(r.get("userFirstname"), firstName),
            cb.equal(r.get("userLastName"), lastName),
            cb.equal(r.get("userMiddleName"), middleName),
            cb.equal(r.get("userPosition"), position),
            cb.equal(r.join("document").get("documentCode"), docCode),
            cb.equal(r.join("country").get("countryCode"), citizenshipCode));
}

//1247
public static Specification<User> findByOfficeOrgidFNameMNameCitcode(Long officeId, String firstName, String middleName, Long citizenshipCode) {
    return (r, cq, cb) -> cb.and(
            cb.equal(r.join("office").get("id"), officeId),
            cb.equal(r.get("userFirstname"), firstName),
            cb.equal(r.get("userLastName"), lastName),
            cb.equal(r.get("userMiddleName"), middleName),
            cb.equal(r.get("userPosition"), position),
            cb.equal(r.join("document").get("documentCode"), docCode),
            cb.equal(r.join("country").get("countryCode"), citizenshipCode));
}

//1256
public static Specification<User> findByOfficeOrgidFNamePositionDoccode(Long officeId, String firstName, String position, Long docCode) {
    return (r, cq, cb) -> cb.and(
            cb.equal(r.join("office").get("id"), officeId),
            cb.equal(r.get("userFirstname"), firstName),
            cb.equal(r.get("userLastName"), lastName),
            cb.equal(r.get("userMiddleName"), middleName),
            cb.equal(r.get("userPosition"), position),
            cb.equal(r.join("document").get("documentCode"), docCode),
            cb.equal(r.join("country").get("countryCode"), citizenshipCode));
}

//1257
public static Specification<User> findByOfficeOrgidFNamePositionCitcode(Long officeId, String firstName, String position, Long citizenshipCode) {
    return (r, cq, cb) -> cb.and(
            cb.equal(r.join("office").get("id"), officeId),
            cb.equal(r.get("userFirstname"), firstName),
            cb.equal(r.get("userLastName"), lastName),
            cb.equal(r.get("userMiddleName"), middleName),
            cb.equal(r.get("userPosition"), position),
            cb.equal(r.join("document").get("documentCode"), docCode),
            cb.equal(r.join("country").get("countryCode"), citizenshipCode));
}

//1267
public static Specification<User> findByOfficeOrgidFNameDoccodeCitcode(Long officeId, String firstName, Long docCode, Long citizenshipCode) {
    return (r, cq, cb) -> cb.and(
            cb.equal(r.join("office").get("id"), officeId),
            cb.equal(r.get("userFirstname"), firstName),
            cb.equal(r.get("userLastName"), lastName),
            cb.equal(r.get("userMiddleName"), middleName),
            cb.equal(r.get("userPosition"), position),
            cb.equal(r.join("document").get("documentCode"), docCode),
            cb.equal(r.join("country").get("countryCode"), citizenshipCode));
}

//1345
public static Specification<User> findByOfficeOrgidLNameMNamePosition(Long officeId, String lastName, String middleName, String position) {
    return (r, cq, cb) -> cb.and(
            cb.equal(r.join("office").get("id"), officeId),
            cb.equal(r.get("userFirstname"), firstName),
            cb.equal(r.get("userLastName"), lastName),
            cb.equal(r.get("userMiddleName"), middleName),
            cb.equal(r.get("userPosition"), position),
            cb.equal(r.join("document").get("documentCode"), docCode),
            cb.equal(r.join("country").get("countryCode"), citizenshipCode));
}

//1346
public static Specification<User> findByOfficeOrgidLNameMNameDoccode(Long officeId, String lastName, String middleName, Long docCode) {
    return (r, cq, cb) -> cb.and(
            cb.equal(r.join("office").get("id"), officeId),
            cb.equal(r.get("userFirstname"), firstName),
            cb.equal(r.get("userLastName"), lastName),
            cb.equal(r.get("userMiddleName"), middleName),
            cb.equal(r.get("userPosition"), position),
            cb.equal(r.join("document").get("documentCode"), docCode),
            cb.equal(r.join("country").get("countryCode"), citizenshipCode));
}

//1347
public static Specification<User> findByOfficeOrgidLNameMNameCitcode(Long officeId, String lastName, String middleName, Long citizenshipCode) {
    return (r, cq, cb) -> cb.and(
            cb.equal(r.join("office").get("id"), officeId),
            cb.equal(r.get("userFirstname"), firstName),
            cb.equal(r.get("userLastName"), lastName),
            cb.equal(r.get("userMiddleName"), middleName),
            cb.equal(r.get("userPosition"), position),
            cb.equal(r.join("document").get("documentCode"), docCode),
            cb.equal(r.join("country").get("countryCode"), citizenshipCode));
}

//1356
public static Specification<User> findByOfficeOrgidLNamePositionDoccode(Long officeId, String lastName, String position, Long docCode) {
    return (r, cq, cb) -> cb.and(
            cb.equal(r.join("office").get("id"), officeId),
            cb.equal(r.get("userFirstname"), firstName),
            cb.equal(r.get("userLastName"), lastName),
            cb.equal(r.get("userMiddleName"), middleName),
            cb.equal(r.get("userPosition"), position),
            cb.equal(r.join("document").get("documentCode"), docCode),
            cb.equal(r.join("country").get("countryCode"), citizenshipCode));
}

//1357
public static Specification<User> findByOfficeOrgidLNamePositionCitcode(Long officeId, String lastName, String position, Long citizenshipCode) {
    return (r, cq, cb) -> cb.and(
            cb.equal(r.join("office").get("id"), officeId),
            cb.equal(r.get("userFirstname"), firstName),
            cb.equal(r.get("userLastName"), lastName),
            cb.equal(r.get("userMiddleName"), middleName),
            cb.equal(r.get("userPosition"), position),
            cb.equal(r.join("document").get("documentCode"), docCode),
            cb.equal(r.join("country").get("countryCode"), citizenshipCode));
}

//1367
public static Specification<User> findByOfficeOrgidFNameLNameMNamePositionDoccodeCitcode(Long officeId, String firstName, String lastName, String middleName, String position, Long docCode, Long citizenshipCode) {
    return (r, cq, cb) -> cb.and(
            cb.equal(r.join("office").get("id"), officeId),
            cb.equal(r.get("userFirstname"), firstName),
            cb.equal(r.get("userLastName"), lastName),
            cb.equal(r.get("userMiddleName"), middleName),
            cb.equal(r.get("userPosition"), position),
            cb.equal(r.join("document").get("documentCode"), docCode),
            cb.equal(r.join("country").get("countryCode"), citizenshipCode));
}

//1456
public static Specification<User> findByOfficeOrgidFNameLNameMNamePositionDoccodeCitcode(Long officeId, String firstName, String lastName, String middleName, String position, Long docCode, Long citizenshipCode) {
    return (r, cq, cb) -> cb.and(
            cb.equal(r.join("office").get("id"), officeId),
            cb.equal(r.get("userFirstname"), firstName),
            cb.equal(r.get("userLastName"), lastName),
            cb.equal(r.get("userMiddleName"), middleName),
            cb.equal(r.get("userPosition"), position),
            cb.equal(r.join("document").get("documentCode"), docCode),
            cb.equal(r.join("country").get("countryCode"), citizenshipCode));
}

//1457
public static Specification<User> findByOfficeOrgidFNameLNameMNamePositionDoccodeCitcode(Long officeId, String firstName, String lastName, String middleName, String position, Long docCode, Long citizenshipCode) {
    return (r, cq, cb) -> cb.and(
            cb.equal(r.join("office").get("id"), officeId),
            cb.equal(r.get("userFirstname"), firstName),
            cb.equal(r.get("userLastName"), lastName),
            cb.equal(r.get("userMiddleName"), middleName),
            cb.equal(r.get("userPosition"), position),
            cb.equal(r.join("document").get("documentCode"), docCode),
            cb.equal(r.join("country").get("countryCode"), citizenshipCode));
}

//1467
public static Specification<User> findByOfficeOrgidFNameLNameMNamePositionDoccodeCitcode(Long officeId, String firstName, String lastName, String middleName, String position, Long docCode, Long citizenshipCode) {
    return (r, cq, cb) -> cb.and(
            cb.equal(r.join("office").get("id"), officeId),
            cb.equal(r.get("userFirstname"), firstName),
            cb.equal(r.get("userLastName"), lastName),
            cb.equal(r.get("userMiddleName"), middleName),
            cb.equal(r.get("userPosition"), position),
            cb.equal(r.join("document").get("documentCode"), docCode),
            cb.equal(r.join("country").get("countryCode"), citizenshipCode));
}

//1567
public static Specification<User> findByOfficeOrgidFNameLNameMNamePositionDoccodeCitcode(Long officeId, String firstName, String lastName, String middleName, String position, Long docCode, Long citizenshipCode) {
    return (r, cq, cb) -> cb.and(
            cb.equal(r.join("office").get("id"), officeId),
            cb.equal(r.get("userFirstname"), firstName),
            cb.equal(r.get("userLastName"), lastName),
            cb.equal(r.get("userMiddleName"), middleName),
            cb.equal(r.get("userPosition"), position),
            cb.equal(r.join("document").get("documentCode"), docCode),
            cb.equal(r.join("country").get("countryCode"), citizenshipCode));
}

//3 Переменные
//123
public static Specification<User> findByOfficeOrgidFNameLNameMNamePositionDoccodeCitcode(Long officeId, String firstName, String lastName, String middleName, String position, Long docCode, Long citizenshipCode) {
    return (r, cq, cb) -> cb.and(
            cb.equal(r.join("office").get("id"), officeId),
            cb.equal(r.get("userFirstname"), firstName),
            cb.equal(r.get("userLastName"), lastName),
            cb.equal(r.get("userMiddleName"), middleName),
            cb.equal(r.get("userPosition"), position),
            cb.equal(r.join("document").get("documentCode"), docCode),
            cb.equal(r.join("country").get("countryCode"), citizenshipCode));
}

//124
public static Specification<User> findByOfficeOrgidFNameLNameMNamePositionDoccodeCitcode(Long officeId, String firstName, String lastName, String middleName, String position, Long docCode, Long citizenshipCode) {
    return (r, cq, cb) -> cb.and(
            cb.equal(r.join("office").get("id"), officeId),
            cb.equal(r.get("userFirstname"), firstName),
            cb.equal(r.get("userLastName"), lastName),
            cb.equal(r.get("userMiddleName"), middleName),
            cb.equal(r.get("userPosition"), position),
            cb.equal(r.join("document").get("documentCode"), docCode),
            cb.equal(r.join("country").get("countryCode"), citizenshipCode));
}

//125
public static Specification<User> findByOfficeOrgidFNameLNameMNamePositionDoccodeCitcode(Long officeId, String firstName, String lastName, String middleName, String position, Long docCode, Long citizenshipCode) {
    return (r, cq, cb) -> cb.and(
            cb.equal(r.join("office").get("id"), officeId),
            cb.equal(r.get("userFirstname"), firstName),
            cb.equal(r.get("userLastName"), lastName),
            cb.equal(r.get("userMiddleName"), middleName),
            cb.equal(r.get("userPosition"), position),
            cb.equal(r.join("document").get("documentCode"), docCode),
            cb.equal(r.join("country").get("countryCode"), citizenshipCode));
}

//126
public static Specification<User> findByOfficeOrgidFNameLNameMNamePositionDoccodeCitcode(Long officeId, String firstName, String lastName, String middleName, String position, Long docCode, Long citizenshipCode) {
    return (r, cq, cb) -> cb.and(
            cb.equal(r.join("office").get("id"), officeId),
            cb.equal(r.get("userFirstname"), firstName),
            cb.equal(r.get("userLastName"), lastName),
            cb.equal(r.get("userMiddleName"), middleName),
            cb.equal(r.get("userPosition"), position),
            cb.equal(r.join("document").get("documentCode"), docCode),
            cb.equal(r.join("country").get("countryCode"), citizenshipCode));
}

//127
public static Specification<User> findByOfficeOrgidFNameLNameMNamePositionDoccodeCitcode(Long officeId, String firstName, String lastName, String middleName, String position, Long docCode, Long citizenshipCode) {
    return (r, cq, cb) -> cb.and(
            cb.equal(r.join("office").get("id"), officeId),
            cb.equal(r.get("userFirstname"), firstName),
            cb.equal(r.get("userLastName"), lastName),
            cb.equal(r.get("userMiddleName"), middleName),
            cb.equal(r.get("userPosition"), position),
            cb.equal(r.join("document").get("documentCode"), docCode),
            cb.equal(r.join("country").get("countryCode"), citizenshipCode));
}

//134
public static Specification<User> findByOfficeOrgidFNameLNameMNamePositionDoccodeCitcode(Long officeId, String firstName, String lastName, String middleName, String position, Long docCode, Long citizenshipCode) {
    return (r, cq, cb) -> cb.and(
            cb.equal(r.join("office").get("id"), officeId),
            cb.equal(r.get("userFirstname"), firstName),
            cb.equal(r.get("userLastName"), lastName),
            cb.equal(r.get("userMiddleName"), middleName),
            cb.equal(r.get("userPosition"), position),
            cb.equal(r.join("document").get("documentCode"), docCode),
            cb.equal(r.join("country").get("countryCode"), citizenshipCode));
}

//135
public static Specification<User> findByOfficeOrgidFNameLNameMNamePositionDoccodeCitcode(Long officeId, String firstName, String lastName, String middleName, String position, Long docCode, Long citizenshipCode) {
    return (r, cq, cb) -> cb.and(
            cb.equal(r.join("office").get("id"), officeId),
            cb.equal(r.get("userFirstname"), firstName),
            cb.equal(r.get("userLastName"), lastName),
            cb.equal(r.get("userMiddleName"), middleName),
            cb.equal(r.get("userPosition"), position),
            cb.equal(r.join("document").get("documentCode"), docCode),
            cb.equal(r.join("country").get("countryCode"), citizenshipCode));
}

//136
public static Specification<User> findByOfficeOrgidFNameLNameMNamePositionDoccodeCitcode(Long officeId, String firstName, String lastName, String middleName, String position, Long docCode, Long citizenshipCode) {
    return (r, cq, cb) -> cb.and(
            cb.equal(r.join("office").get("id"), officeId),
            cb.equal(r.get("userFirstname"), firstName),
            cb.equal(r.get("userLastName"), lastName),
            cb.equal(r.get("userMiddleName"), middleName),
            cb.equal(r.get("userPosition"), position),
            cb.equal(r.join("document").get("documentCode"), docCode),
            cb.equal(r.join("country").get("countryCode"), citizenshipCode));
}

//137
public static Specification<User> findByOfficeOrgidFNameLNameMNamePositionDoccodeCitcode(Long officeId, String firstName, String lastName, String middleName, String position, Long docCode, Long citizenshipCode) {
    return (r, cq, cb) -> cb.and(
            cb.equal(r.join("office").get("id"), officeId),
            cb.equal(r.get("userFirstname"), firstName),
            cb.equal(r.get("userLastName"), lastName),
            cb.equal(r.get("userMiddleName"), middleName),
            cb.equal(r.get("userPosition"), position),
            cb.equal(r.join("document").get("documentCode"), docCode),
            cb.equal(r.join("country").get("countryCode"), citizenshipCode));
}

//145
public static Specification<User> findByOfficeOrgidFNameLNameMNamePositionDoccodeCitcode(Long officeId, String firstName, String lastName, String middleName, String position, Long docCode, Long citizenshipCode) {
    return (r, cq, cb) -> cb.and(
            cb.equal(r.join("office").get("id"), officeId),
            cb.equal(r.get("userFirstname"), firstName),
            cb.equal(r.get("userLastName"), lastName),
            cb.equal(r.get("userMiddleName"), middleName),
            cb.equal(r.get("userPosition"), position),
            cb.equal(r.join("document").get("documentCode"), docCode),
            cb.equal(r.join("country").get("countryCode"), citizenshipCode));
}

//146
public static Specification<User> findByOfficeOrgidFNameLNameMNamePositionDoccodeCitcode(Long officeId, String firstName, String lastName, String middleName, String position, Long docCode, Long citizenshipCode) {
    return (r, cq, cb) -> cb.and(
            cb.equal(r.join("office").get("id"), officeId),
            cb.equal(r.get("userFirstname"), firstName),
            cb.equal(r.get("userLastName"), lastName),
            cb.equal(r.get("userMiddleName"), middleName),
            cb.equal(r.get("userPosition"), position),
            cb.equal(r.join("document").get("documentCode"), docCode),
            cb.equal(r.join("country").get("countryCode"), citizenshipCode));
}

//147
public static Specification<User> findByOfficeOrgidFNameLNameMNamePositionDoccodeCitcode(Long officeId, String firstName, String lastName, String middleName, String position, Long docCode, Long citizenshipCode) {
    return (r, cq, cb) -> cb.and(
            cb.equal(r.join("office").get("id"), officeId),
            cb.equal(r.get("userFirstname"), firstName),
            cb.equal(r.get("userLastName"), lastName),
            cb.equal(r.get("userMiddleName"), middleName),
            cb.equal(r.get("userPosition"), position),
            cb.equal(r.join("document").get("documentCode"), docCode),
            cb.equal(r.join("country").get("countryCode"), citizenshipCode));
}

//156
public static Specification<User> findByOfficeOrgidFNameLNameMNamePositionDoccodeCitcode(Long officeId, String firstName, String lastName, String middleName, String position, Long docCode, Long citizenshipCode) {
    return (r, cq, cb) -> cb.and(
            cb.equal(r.join("office").get("id"), officeId),
            cb.equal(r.get("userFirstname"), firstName),
            cb.equal(r.get("userLastName"), lastName),
            cb.equal(r.get("userMiddleName"), middleName),
            cb.equal(r.get("userPosition"), position),
            cb.equal(r.join("document").get("documentCode"), docCode),
            cb.equal(r.join("country").get("countryCode"), citizenshipCode));
}

//157
public static Specification<User> findByOfficeOrgidFNameLNameMNamePositionDoccodeCitcode(Long officeId, String firstName, String lastName, String middleName, String position, Long docCode, Long citizenshipCode) {
    return (r, cq, cb) -> cb.and(
            cb.equal(r.join("office").get("id"), officeId),
            cb.equal(r.get("userFirstname"), firstName),
            cb.equal(r.get("userLastName"), lastName),
            cb.equal(r.get("userMiddleName"), middleName),
            cb.equal(r.get("userPosition"), position),
            cb.equal(r.join("document").get("documentCode"), docCode),
            cb.equal(r.join("country").get("countryCode"), citizenshipCode));
}

//167
public static Specification<User> findByOfficeOrgidFNameLNameMNamePositionDoccodeCitcode(Long officeId, String firstName, String lastName, String middleName, String position, Long docCode, Long citizenshipCode) {
    return (r, cq, cb) -> cb.and(
            cb.equal(r.join("office").get("id"), officeId),
            cb.equal(r.get("userFirstname"), firstName),
            cb.equal(r.get("userLastName"), lastName),
            cb.equal(r.get("userMiddleName"), middleName),
            cb.equal(r.get("userPosition"), position),
            cb.equal(r.join("document").get("documentCode"), docCode),
            cb.equal(r.join("country").get("countryCode"), citizenshipCode));
}

//2 Переменные
//12
public static Specification<User> findByOfficeOrgidFNameLNameMNamePositionDoccodeCitcode(Long officeId, String firstName, String lastName, String middleName, String position, Long docCode, Long citizenshipCode) {
    return (r, cq, cb) -> cb.and(
            cb.equal(r.join("office").get("id"), officeId),
            cb.equal(r.get("userFirstname"), firstName),
            cb.equal(r.get("userLastName"), lastName),
            cb.equal(r.get("userMiddleName"), middleName),
            cb.equal(r.get("userPosition"), position),
            cb.equal(r.join("document").get("documentCode"), docCode),
            cb.equal(r.join("country").get("countryCode"), citizenshipCode));
}

//13
public static Specification<User> findByOfficeOrgidFNameLNameMNamePositionDoccodeCitcode(Long officeId, String firstName, String lastName, String middleName, String position, Long docCode, Long citizenshipCode) {
    return (r, cq, cb) -> cb.and(
            cb.equal(r.join("office").get("id"), officeId),
            cb.equal(r.get("userFirstname"), firstName),
            cb.equal(r.get("userLastName"), lastName),
            cb.equal(r.get("userMiddleName"), middleName),
            cb.equal(r.get("userPosition"), position),
            cb.equal(r.join("document").get("documentCode"), docCode),
            cb.equal(r.join("country").get("countryCode"), citizenshipCode));
}

//14
public static Specification<User> findByOfficeOrgidFNameLNameMNamePositionDoccodeCitcode(Long officeId, String firstName, String lastName, String middleName, String position, Long docCode, Long citizenshipCode) {
    return (r, cq, cb) -> cb.and(
            cb.equal(r.join("office").get("id"), officeId),
            cb.equal(r.get("userFirstname"), firstName),
            cb.equal(r.get("userLastName"), lastName),
            cb.equal(r.get("userMiddleName"), middleName),
            cb.equal(r.get("userPosition"), position),
            cb.equal(r.join("document").get("documentCode"), docCode),
            cb.equal(r.join("country").get("countryCode"), citizenshipCode));
}

//15
public static Specification<User> findByOfficeOrgidFNameLNameMNamePositionDoccodeCitcode(Long officeId, String firstName, String lastName, String middleName, String position, Long docCode, Long citizenshipCode) {
    return (r, cq, cb) -> cb.and(
            cb.equal(r.join("office").get("id"), officeId),
            cb.equal(r.get("userFirstname"), firstName),
            cb.equal(r.get("userLastName"), lastName),
            cb.equal(r.get("userMiddleName"), middleName),
            cb.equal(r.get("userPosition"), position),
            cb.equal(r.join("document").get("documentCode"), docCode),
            cb.equal(r.join("country").get("countryCode"), citizenshipCode));
}

//16
public static Specification<User> findByOfficeOrgidFNameLNameMNamePositionDoccodeCitcode(Long officeId, String firstName, String lastName, String middleName, String position, Long docCode, Long citizenshipCode) {
    return (r, cq, cb) -> cb.and(
            cb.equal(r.join("office").get("id"), officeId),
            cb.equal(r.get("userFirstname"), firstName),
            cb.equal(r.get("userLastName"), lastName),
            cb.equal(r.get("userMiddleName"), middleName),
            cb.equal(r.get("userPosition"), position),
            cb.equal(r.join("document").get("documentCode"), docCode),
            cb.equal(r.join("country").get("countryCode"), citizenshipCode));
}

//17
public static Specification<User> findByOfficeOrgidFNameLNameMNamePositionDoccodeCitcode(Long officeId, String firstName, String lastName, String middleName, String position, Long docCode, Long citizenshipCode) {
    return (r, cq, cb) -> cb.and(
            cb.equal(r.join("office").get("id"), officeId),
            cb.equal(r.get("userFirstname"), firstName),
            cb.equal(r.get("userLastName"), lastName),
            cb.equal(r.get("userMiddleName"), middleName),
            cb.equal(r.get("userPosition"), position),
            cb.equal(r.join("document").get("documentCode"), docCode),
            cb.equal(r.join("country").get("countryCode"), citizenshipCode));
}




    @Transactional(readOnly = true)
    public List<User> searchUser(Long officeId, String firstName, String lastName, String middleName, String position, Long docCode, Long citizenshipCode) {
//        User findUser = new User();
//        Office newOfiice = new Office();
//        newOfiice.setId(officeId);
//        findUser.setOffice(newOfiice);
//        Document newDocument = new Document();
//        Country newCountry = new Country();
//        if (firstName != null) {findUser.setUserFirstname(firstName);}
//        if (lastName != null) findUser.setUserLastname(lastName);
//        if (middleName != null) findUser.setUserMiddlename(middleName);
//        if (position != null) findUser.setUserPosition(position);
//        if (docCode != null){
//            newDocument.setDocumentCode(docCode);
//        findUser.setDocument(newDocument);
//        newOfiice.setId(officeId);
//        findUser.setOffice(newOfiice);}
//        if (citizenshipCode != null){
//            newCountry.setCountryCode(citizenshipCode);
//        findUser.setCountry(newCountry);
//        }
//
//        public static Specification<User> findByUser (User findUser) {
//        return (r, cq, cb) -> cb.and(
//                cb.equal(r.join("office").get("id"), findUser.getOffice().getId()),
//                cb.equal(r.get("userFirstname"), firstName),
//                cb.equal(r.get("userLastName"), lastName),
//                cb.equal(r.get("userMiddleName"), middleName),
//                cb.equal(r.get("userPosition"), position),
//                cb.equal(r.join("document").get("documentCode"), docCode),
//                cb.equal(r.join("country").get("countryCode"), citizenshipCode));
//        }
//
//        return userRepository.findAll();
//    }



        if (firstName == null) {2/=0
            if (lastName == null) {3/=0
                if (middleName == null) {4/=0
                    if (position == null) {5/=0
                        if (docCode == null) {6/=0
                            if (citizenshipCode == null) {7/=0
                                return userRepository.findAll();
                            } else {7=0
                                return userRepository.findAll();
                            }
                        } else {6=0
                            if (citizenshipCode == null) {7/=0
                                return userRepository.findAll();
                            } else {7=0
                                return userRepository.findAll();
                            }
                        }
                    } else {5=0
                        if (docCode == null) {6/=0
                            if (citizenshipCode == null) {7/=0
                                return userRepository.findAll();
                            } else {7=0
                                return userRepository.findAll();
                            }
                        } else {6=0
                            if (citizenshipCode == null) {7/=0
                                return userRepository.findAll();
                            } else {7=0
                                return userRepository.findAll();
                            }
                        }
                    }
                } else {4=0
                    if (position == null) {5/=0
                        if (docCode == null) {6/=0
                            if (citizenshipCode == null) {7/=0
                                return userRepository.findAll();
                            } else {7=0
                                return userRepository.findAll();
                            }
                        } else {6=0
                            if (citizenshipCode == null) {7/=0
                                return userRepository.findAll();
                            } else {7=0
                                return userRepository.findAll();
                            }
                        }
                    } else {5=0
                        if (docCode == null) {6/=0
                            if (citizenshipCode == null) {7/=0
                                return userRepository.findAll();
                            } else {7=0
                                return userRepository.findAll();
                            }
                        } else {6=0
                            if (citizenshipCode == null) {7/=0
                                return userRepository.findAll();
                            } else {7=0
                                return userRepository.findAll();
                            }
                        }
                    }
                }
            } else {3=0
                if (middleName == null) {4/=0
                    if (position == null) {5/=0
                        if (docCode == null) {6/=0
                            if (citizenshipCode == null) {7/=0
                                return userRepository.findAll();
                            } else {7=0
                                return userRepository.findAll();
                            }
                        } else {6=0
                            if (citizenshipCode == null) {7/=0
                                return userRepository.findAll();
                            } else {7=0
                                return userRepository.findAll();
                            }
                        }
                    } else {5=0
                        if (docCode == null) {6/=0
                            if (citizenshipCode == null) {7/=0
                                return userRepository.findAll();
                            } else {7=0
                                return userRepository.findAll();
                            }
                        } else {6=0
                            if (citizenshipCode == null) {7/=0
                                return userRepository.findAll();
                            } else {7=0
                                return userRepository.findAll();
                            }
                        }
                    }
                } else {4=0
                    if (position == null) {5/=0
                        if (docCode == null) {6/=0
                            if (citizenshipCode == null) {7/=0
                                return userRepository.findAll();
                            } else {7=0
                                return userRepository.findAll();
                            }
                        } else {6=0
                            if (citizenshipCode == null) {7/=0
                                return userRepository.findAll();
                            } else {7=0
                                return userRepository.findAll();
                            }
                        }
                    } else {5=0
                        if (docCode == null) {6/=0
                            if (citizenshipCode == null) {7/=0
                                return userRepository.findAll();
                            } else {7=0
                                return userRepository.findAll();
                            }
                        } else {6=0
                            if (citizenshipCode == null) {7/=0
                                return userRepository.findAll();
                            } else {7=0
                                return userRepository.findAll();
                            }
                        }
                    }
                }
            }
        } else {2=0
            if (lastName == null) {3/=0
                if (middleName == null) {4/=0
                    if (position == null) {5/=0
                        if (docCode == null) {6/=0
                            if (citizenshipCode == null) {7/=0
                                return userRepository.findAll();
                            } else {7=0
                                return userRepository.findAll();
                            }
                        } else {6=0
                            if (citizenshipCode == null) {7/=0
                                return userRepository.findAll();
                            } else {7=0
                                return userRepository.findAll();
                            }
                        }
                    } else {5=0
                        if (docCode == null) {6/=0
                            if (citizenshipCode == null) {7/=0
                                return userRepository.findAll();
                            } else {7=0
                                return userRepository.findAll();
                            }
                        } else {6=0
                            if (citizenshipCode == null) {7/=0
                                return userRepository.findAll();
                            } else {7=0
                                return userRepository.findAll();
                            }
                        }
                    }
                } else {4=0
                    if (position == null) {5/=0
                        if (docCode == null) {6/=0
                            if (citizenshipCode == null) {7/=0
                                return userRepository.findAll();
                            } else {7=0
                                return userRepository.findAll();
                            }
                        } else {6=0
                            if (citizenshipCode == null) {7/=0
                                return userRepository.findAll();
                            } else {7=0
                                return userRepository.findAll();
                            }
                        }
                    } else {5=0
                        if (docCode == null) {6/=0
                            if (citizenshipCode == null) {7/=0
                                return userRepository.findAll();
                            } else {7=0
                                return userRepository.findAll();
                            }
                        } else {6=0
                            if (citizenshipCode == null) {7/=0
                                return userRepository.findAll();
                            } else {7=0
                                return userRepository.findAll();
                            }
                        }
                    }
                }
            } else {3=0
                if (middleName == null) {4/=0
                    if (position == null) {5/=0
                        if (docCode == null) {6/=0
                            if (citizenshipCode == null) {7/=0
                                return userRepository.findAll();
                            } else {7=0
                                return userRepository.findAll();
                            }
                        } else {6=0
                            if (citizenshipCode == null) {7/=0
                                return userRepository.findAll();
                            } else {7=0
                                return userRepository.findAll();
                            }
                        }
                    } else {5=0
                        if (docCode == null) {6/=0
                            if (citizenshipCode == null) {7/=0
                                return userRepository.findAll();
                            } else {7=0
                                return userRepository.findAll();
                            }
                        } else {6=0
                            if (citizenshipCode == null) {7/=0
                                return userRepository.findAll();
                            } else {7=0
                                return userRepository.findAll();
                            }
                        }
                    }
                } else {4=0
                    if (position == null) {5/=0
                        if (docCode == null) {6/=0
                            if (citizenshipCode == null) {7/=0
                                return userRepository.findAll();
                            } else {7=0
                                return userRepository.findAll();
                            }
                        } else {6=0
                            if (citizenshipCode == null) {7/=0
                                return userRepository.findAll();
                            } else {7=0
                                return userRepository.findAll();
                            }
                        }
                    } else {5=0
                        if (docCode == null) {6/=0
                            if (citizenshipCode == null) {7/=0
                                return userRepository.findAll();
                            } else {7=0
                                return userRepository.findAll();
                            }
                        } else {6=0
                            if (citizenshipCode == null) {7/=0
                                return userRepository.findAll();
                            } else {7=0
                                return userRepository.findAll();
                            }
                        }
                    }
                }
            }
        }
    }



@Transactional(readOnly = true)
public User findById(Long id){
        return userRepository.findOne(id);
        }

        }
//14. api/user/list
//        In (фильтр):
//        {
//        “officeId”:””, //обязательный параметр 1
//        “firstName”:””, 2
//        “lastName”:””, 3
//        “middleName”:””, 4
//        “position”,””, 5
//        “docCode”:””, 6
//        “citizenshipCode”:”” 7
//        }

//7 Переменные
//1234567
//6 Переменных
//123456
//123457
//123467
//5 Переменных
//12345
//12346
//12347
//12356
//12357
//12367
//12456
//12457
//12467
//12567
//13456
//13467
//13567
//14567
//4 Переменные
//1234
//1235
//1236
//1237
//1245
//1246
//1247
//1256
//1257
//1267
//1345
//1346
//1347
//1356
//1357
//1367
//1456
//1457
//1467
//1567
//3 Переменные
//123
//124
//125
//126
//127
//134
//135
//136
//137
//145
//146
//147
//156
//157
//167
//2 Переменные
//12
//13
//14
//15
//16
//17

//        Out:
//        {
//        “id”:””,
//        “firstName”:””,
//        “secondName”:””,
//        “middleName”:””,
//        “position”:””
//        }
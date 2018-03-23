package com.i.homework02.servise.serviceImpl;

import com.i.homework02.entity.User;
import com.i.homework02.exeption.CustomUserException;
import com.i.homework02.repository.CountryRepository;
import com.i.homework02.repository.DocTypeRepository;
import com.i.homework02.repository.OfficeRepository;
import com.i.homework02.repository.UserRepository;
import com.i.homework02.servise.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.i.homework02.view.UserIdViewOut;
import com.i.homework02.view.UserListViewOut;
import com.i.homework02.view.UserSaveView;
import com.i.homework02.view.UserUpdateView;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Service
@Repository
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OfficeRepository officeRepository;

    @Autowired
    private CountryRepository countryRepository;

    @Autowired
    private DocTypeRepository docTypeRepository;

    /**
     * Поиск списка сотрудников по параметрам
     *
     * @param officeId        - Id офиса, которому принадлежит сотрудник
     * @param firstName       - имя сотрудника
     * @param secondName      -фамилия сотрудника
     * @param middleName      - отчество сотрудника
     * @param position        - позиция(должность) сотрудника занимаемая в организации
     * @param docCode         -Код документа сотрудника
     * @param citizenshipCode - Код страны, гражданином, которой сотрудник является
     * @return List<User> - список сотрудников, отвечающий критериям поиска
     */
    @Transactional(readOnly = true)
    @Override
    public List<UserListViewOut> searchUser(Long officeId, String firstName, String secondName, String middleName, String position, String docCode, String citizenshipCode) throws CustomUserException {
        Specification<User> findUserByParam = new Specification<User>() {
            @Override
            public Predicate toPredicate(Root<User> r, CriteriaQuery<?> cq, CriteriaBuilder cb) {
                List<Predicate> prs = new ArrayList<>();
                prs.add(cb.equal(r.get("office").get("id"), officeId));
                if (firstName != null) prs.add(cb.like(r.get("firstName"), "%" + firstName + "%"));
                if (secondName != null) prs.add(cb.like(r.get("secondName"), "%" + secondName + "%"));
                if (middleName != null) prs.add(cb.like(r.get("middleName"), "%" + middleName + "%"));
                if (position != null) prs.add(cb.equal(r.get("position"), position));
                if (docCode != null) prs.add(cb.equal(r.get("docType").get("code"), docCode));
                if (citizenshipCode != null) prs.add(cb.equal(r.get("country").get("code"), citizenshipCode));
                return cb.and(prs.toArray(new Predicate[prs.size()]));
            }
        };
        if (userRepository.findAll(findUserByParam).isEmpty()) {
            throw new CustomUserException("По заданным параметрам ничего не найдено");
        }
        List<User> us = userRepository.findAll(findUserByParam);
        List<UserListViewOut> ulvo = new ArrayList<>();
        for (User user : us
                ) {
            UserListViewOut userListViewOut = new UserListViewOut();
            userListViewOut.setId(user.getId());
            userListViewOut.setFirstName(user.getFirstName());
            userListViewOut.setSecondName(user.getSecondName());
            userListViewOut.setMiddleName(user.getMiddleName());
            userListViewOut.setPosition(user.getPosition());
            ulvo.add(userListViewOut);
        }
        return ulvo;
    }

    /**
     * Изменение(обновление) параметров сотрудника по переданным парметрам в теле объекта userUpdateView
     *
     * @param userUpdateView - объект содержащий параметры сотрудника, для изменения данных хранящихся в базе
     */
    @Override
    @Transactional
    public void update(UserUpdateView userUpdateView) throws CustomUserException {
        if (userUpdateView.getId() == null) {
            throw new CustomUserException("Введите Id сотрудника для изменения его параметров");
        }
        if (userRepository.findOne(userUpdateView.getId()) == null) {
            throw new CustomUserException("Сотрудник с Id: " + userUpdateView.getId() + " не найден!");
        }
        User updateUser = userRepository.findOne(userUpdateView.getId());
        if (userUpdateView.getFirstName() != null)
            updateUser.setFirstName(userUpdateView.getFirstName());
        if (userUpdateView.getSecondName() != null)
            updateUser.setSecondName(userUpdateView.getSecondName());
        if (userUpdateView.getMiddleName() != null)
            updateUser.setMiddleName(userUpdateView.getMiddleName());
        if (userUpdateView.getPosition() != null)
            updateUser.setPosition(userUpdateView.getPosition());
        if (userUpdateView.getPhone() != null)
            updateUser.setPhone(userUpdateView.getPhone());
        if (userUpdateView.getDocName() != null)
            updateUser.getDocType().setName(userUpdateView.getDocName());
        if (userUpdateView.getDocNumber() != null)
            updateUser.setDocNumber(userUpdateView.getDocNumber());
        if (userUpdateView.getDocDate() != null)
            updateUser.setDocDate(userUpdateView.getDocDate());
        if (userUpdateView.getCitizenshipCode() != null)
            updateUser.getCountry().setCode(userUpdateView.getCitizenshipCode());
        if (userUpdateView.getSecondName() != null)
            updateUser.getCountry().setName(userUpdateView.getSecondName());
        if (userUpdateView.getIsIdentified() != null)
            updateUser.setIdentified(userUpdateView.getIsIdentified());
        userRepository.save(updateUser);
    }


    /**
     * Сохранение
     * Запись нового сотрудника в базу, по переданным парметрам в теле объекта userUpdateView
     *
     * @param userSaveViewIn - объект содержащий параметры сотрудника, для сохранения их в базе
     */
    @Override
    @Transactional
    public void save(UserSaveView userSaveViewIn) throws CustomUserException {
        if (userSaveViewIn.getOfficeId() == null) {
            throw new CustomUserException("Для заведения нового сотрудника, нужно прикрепить сотрудника к существующему офису, для этого нужно ввести существующий officeId");
        }
        if (officeRepository.findOne(userSaveViewIn.getOfficeId()) != null) {
            if (userSaveViewIn.getFirstName() == null &
                    userSaveViewIn.getSecondName() == null &
                    userSaveViewIn.getMiddleName() == null &
                    userSaveViewIn.getPosition() == null &
                    userSaveViewIn.getPhone() == null &
                    userSaveViewIn.getDocCode() == null &
                    userSaveViewIn.getDocNumber() == null &
                    userSaveViewIn.getDocDate() == null &
                    userSaveViewIn.getCitizenshipCode() == null &
                    userSaveViewIn.getIsIdentified() == null
                    ) {
                throw new CustomUserException("Поля не заполнены! Для сохранения, необходимо заполнить, хотя бы, одно поле, помимо Id офиса!");
            }
            User sUser = new User();
            if (userSaveViewIn.getFirstName() != null)
                sUser.setFirstName(userSaveViewIn.getFirstName());
            if (userSaveViewIn.getSecondName() != null)
                sUser.setSecondName(userSaveViewIn.getSecondName());
            if (userSaveViewIn.getMiddleName() != null)
                sUser.setMiddleName(userSaveViewIn.getMiddleName());
            if (userSaveViewIn.getPosition() != null)
                sUser.setPosition(userSaveViewIn.getPosition());
            if (userSaveViewIn.getPhone() != null)
                sUser.setPhone(userSaveViewIn.getPhone());
            if (userSaveViewIn.getDocCode() != null)
                sUser.setDocType(docTypeRepository.findDocTypeByCode(userSaveViewIn.getDocCode()));
//                sUser.getDocType().setCode(userSaveViewIn.getDocCode());
            if (userSaveViewIn.getDocNumber() != null)
                sUser.setDocNumber(userSaveViewIn.getDocNumber());
            if (userSaveViewIn.getDocDate() != null)
                sUser.setDocDate(userSaveViewIn.getDocDate());
            if (userSaveViewIn.getCitizenshipCode() != null)
                sUser.setCountry(countryRepository.findCountryByCode(userSaveViewIn.getCitizenshipCode()));
//                sUser.getCountry().setCode(userSaveViewIn.getCitizenshipCode());
            if (userSaveViewIn.getIsIdentified() != null)
                sUser.setIdentified(userSaveViewIn.getIsIdentified());
            userRepository.save(sUser);
        } else {
            throw new CustomUserException("По officeId: " + userSaveViewIn.getOfficeId() + " офис не найден! Для заведения нового сотрудника, нужно прикрепить сотрудника к существующему офису!");
        }
    }

    /**
     * Поиск по Id Сотрудника
     *
     * @param id - Id сотрудника
     * @return userIdViewOut - сотрудник найденый по Id
     */
    @Override
    @Transactional(readOnly = true)
    public UserIdViewOut findById(Long id) throws CustomUserException {
        if (userRepository.findOne(id) == null) {
            throw new CustomUserException("С Id = " + id + ", сотрудники не найдены! Введите существующий Id.");
        }
        User user = userRepository.findOne(id);
        UserIdViewOut userIdViewOut = new UserIdViewOut();
        userIdViewOut.setId(user.getId());
        userIdViewOut.setFirstName(user.getFirstName());
        userIdViewOut.setSecondName(user.getSecondName());
        userIdViewOut.setMiddleName(user.getMiddleName());
        userIdViewOut.setPosition(user.getPosition());
        userIdViewOut.setPhone(user.getPhone());
        userIdViewOut.setDocName(user.getDocType().getName());
        userIdViewOut.setDocNumber(user.getDocNumber());
        userIdViewOut.setDocDate(user.getDocDate());
        userIdViewOut.setCitizenshipCode(user.getCountry().getCode());
        userIdViewOut.setCitizenshipName(user.getCountry().getName());
        userIdViewOut.setIsIdentified(user.getIdentified());
        return userIdViewOut;
    }

    /**
     * Удаление из базы сотрудника по Id
     *
     * @param userUpdateView - объект содержащий параметр Id сотрудника, для удаления по нему из базы
     */
    @Override
    @Transactional
    public void delete(UserUpdateView userUpdateView) throws CustomUserException {
        if (userUpdateView.getId() == null) {
            throw new CustomUserException("Для удаления Id сотрудника не должно быть пустым");
        }
        if (userRepository.findOne(userUpdateView.getId()) == null) {
            throw new CustomUserException("С данным Id, сотрудник не найден!");
        }
        userRepository.delete(userUpdateView.getId());
    }
}

package com.i.homework02.user;

import com.i.homework02.exeption.CustomUserException;
import com.i.homework02.office.OfficeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import view.UserIdViewOut;
import view.UserListViewOut;
import view.UserSaveView;
import view.UserUpdateView;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Service
@Repository
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OfficeRepository officeRepository;

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
    @Override
    public List<UserListViewOut> searchUser(Long officeId, String firstName, String secondName, String middleName, String position, String docCode, String citizenshipCode) {
        Specification<User> findUserByParam = new Specification<User>() {
            @Override
            public Predicate toPredicate(Root<User> r, CriteriaQuery<?> cq, CriteriaBuilder cb) {
                List<Predicate> prs = new ArrayList<>();
                    /*if (officeId != null)*/
                prs.add(cb.equal(r.get("office").get("id"), officeId));
                if (firstName != null) prs.add(cb.like(r.get("firstName"), "%" + firstName + "%"));
                if (secondName != null) prs.add(cb.like(r.get("secondName"), "%" + secondName + "%"));
                if (middleName != null) prs.add(cb.like(r.get("middleName"), "%" + middleName + "%"));
                if (position != null) prs.add(cb.like(r.get("position"), "%" + position + "%"));
                if (docCode != null) prs.add(cb.like(r.get("docCode"), "%" + docCode + "%"));
                if (citizenshipCode != null) prs.add(cb.like(r.get("citizenshipCode"), "%" + citizenshipCode + "%"));
                    /*if (prs.isEmpty()) {return cb.createQuery(User.class).select(r).getRestriction();
                    }
                    else {*/
                return cb.and(prs.toArray(new Predicate[prs.size()]));
                    /*}*/
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
    public void update(UserUpdateView userUpdateView) {
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
            updateUser.setIsIdentified(userUpdateView.getIsIdentified());
        userRepository.save(updateUser);
    }


    /**
     * Сохранение
     * Запись нового сотрудника в базу, по переданным парметрам в теле объекта userUpdateView
     *
     * @param userSaveView - объект содержащий параметры сотрудника, для сохранения их в базе
     */
    @Override
    public void save(UserSaveView userSaveView) {
        if (userSaveView.getOfficeId() == null) {
            throw new CustomUserException("Для заведения нового сотрудника, нужно прикрепить сотрудника к существующему офису, для этого нужно ввести существующий officeId");
        }
        if (officeRepository.findOne(userSaveView.getOfficeId()) != null) {
            if (userSaveView.getFirstName() == null &
                    userSaveView.getSecondName() == null &
                    userSaveView.getMiddleName() == null &
                    userSaveView.getPosition() == null &
                    userSaveView.getPhone() == null &
                    userSaveView.getDocCode() == null &
                    userSaveView.getDocNumber() == null &
                    userSaveView.getDocDate() == null &
                    userSaveView.getCitizenshipCode() == null &
                    userSaveView.getIsIdentified() == null
                    ) {
                throw new CustomUserException("Поля не заполнены! Для сохранения, необходимо заполнить, хотя бы, одно поле, помимо Id офиса!");
            }
            User sUser = new User();
            if (userSaveView.getFirstName() != null)
                sUser.setFirstName(userSaveView.getFirstName());
            if (userSaveView.getSecondName() != null)
                sUser.setSecondName(userSaveView.getSecondName());
            if (userSaveView.getMiddleName() != null)
                sUser.setMiddleName(userSaveView.getMiddleName());
            if (userSaveView.getPosition() != null)
                sUser.setPosition(userSaveView.getPosition());
            if (userSaveView.getPhone() != null)
                sUser.setPhone(userSaveView.getPhone());
            if (userSaveView.getDocCode() != null)
                sUser.getDocType().setCode(userSaveView.getDocCode());
            if (userSaveView.getDocNumber() != null)
                sUser.setDocNumber(userSaveView.getDocNumber());
            if (userSaveView.getDocDate() != null)
                sUser.setDocDate(userSaveView.getDocDate());
            if (userSaveView.getCitizenshipCode() != null)
                sUser.getCountry().setCode(userSaveView.getCitizenshipCode());
            if (userSaveView.getIsIdentified() != null)
                sUser.setIsIdentified(userSaveView.getIsIdentified());
            userRepository.save(sUser);
        } else {
            throw new CustomUserException("По officeId: " + userSaveView.getOfficeId() + " офис не найден! Для заведения нового сотрудника, нужно прикрепить сотрудника к существующему офису");
        }
    }

    /**
     * Поиск по Id Сотрудника
     *
     * @param id - Id сотрудника
     * @return userIdViewOut - сотрудник найденый по Id
     */
    @Override
    public UserIdViewOut findById(Long id) {
        if (userRepository.findOne(id) == null) {
            throw new CustomUserException("С данным Id, сотрудник не найден!");
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
        userIdViewOut.setIsIdentified(user.getIsIdentified());
        return userIdViewOut;
    }

    /**
     * Удаление из базы сотрудника по Id
     *
     * @param user - объект содержащий параметр Id сотрудника, для удаления по нему из базы
     */
    @Override
    public void delete(User user) {
        if (user.getId() == null) {
            throw new CustomUserException("Для удаления Id сотрудника не должно быть пустым");
        }
        if (userRepository.findOne(user.getId()) == null) {
            throw new CustomUserException("С данным Id, сотрудник не найден!");
        }
        userRepository.delete(user.getId());
    }
}

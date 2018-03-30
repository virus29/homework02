package com.i.homework02.service.impl;

import com.i.homework02.entity.Country;
import com.i.homework02.entity.DocType;
import com.i.homework02.entity.Office;
import com.i.homework02.entity.User;
import com.i.homework02.exeption.CustomUserException;
import com.i.homework02.repository.CountryRepository;
import com.i.homework02.repository.DocTypeRepository;
import com.i.homework02.repository.OfficeRepository;
import com.i.homework02.repository.UserRepository;
import com.i.homework02.service.UserService;
import com.i.homework02.view.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.text.ParseException;
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
     * @param user - Объект содержащий параметры для поиска сотрудника
     * @return List<User> - список сотрудников, отвечающий критериям поиска
     */
    @Transactional(readOnly = true)
    @Override
    public List<UserListViewResponse> searchUser(User user) throws CustomUserException {
        Specification<User> findUserByParam = new Specification<User>() {
            @Override
            public Predicate toPredicate(Root<User> r, CriteriaQuery<?> cq, CriteriaBuilder cb) {
                List<Predicate> prs = new ArrayList<>();
                prs.add(cb.equal(r.get("office").get("id"), user.getOffice().getId()));
                if (user.getFirstName() != null) prs.add(cb.like(r.get("firstName"), "%" + user.getFirstName() + "%"));
                if (user.getSecondName() != null)
                    prs.add(cb.like(r.get("secondName"), "%" + user.getSecondName() + "%"));
                if (user.getMiddleName() != null)
                    prs.add(cb.like(r.get("middleName"), "%" + user.getMiddleName() + "%"));
                if (user.getPosition() != null)
                    prs.add(cb.equal(r.get("position"), user.getPosition()));
                if (user.getDocType().getCode() != null)
                    prs.add(cb.equal(r.get("docType").get("code"), user.getDocType().getCode()));
                if (user.getDocType().getCode() != null)
                    prs.add(cb.equal(r.get("country").get("code"), user.getCountry().getCode()));
                return cb.and(prs.toArray(new Predicate[prs.size()]));
            }
        };
        if (userRepository.findAll(findUserByParam).isEmpty()) {
            throw new CustomUserException("По заданным параметрам ничего не найдено");
        }
        List<User> us = userRepository.findAll(findUserByParam);
        List<UserListViewResponse> result = new ArrayList<>();
        for (User userN : us
                ) {
            UserListViewResponse userListViewResponse = new UserListViewResponse();
            userListViewResponse.setId(userN.getId());
            userListViewResponse.setFirstName(userN.getFirstName());
            userListViewResponse.setSecondName(userN.getSecondName());
            userListViewResponse.setMiddleName(userN.getMiddleName());
            userListViewResponse.setPosition(userN.getPosition());
            result.add(userListViewResponse);
        }
        return result;
    }

    /**
     * Изменение(обновление) параметров сотрудника по переданным парметрам в теле объекта userUpdateViewRequest
     *
     * @param user - объект содержащий параметры сотрудника, для изменения данных хранящихся в базе
     */
    @Override
    @Transactional
    public void update(User user) throws CustomUserException {

        if (userRepository.findOne(user.getId()) == null) {
            throw new CustomUserException("Сотрудник с Id: " + user.getId() + " не найден!");
        }
        User updateUser = userRepository.findOne(user.getId());
        if (user.getFirstName() != null)
            updateUser.setFirstName(user.getFirstName());
        if (user.getSecondName() != null)
            updateUser.setSecondName(user.getSecondName());
        if (user.getMiddleName() != null)
            updateUser.setMiddleName(user.getMiddleName());
        if (user.getPosition() != null)
            updateUser.setPosition(user.getPosition());
        if (user.getPhone() != null)
            updateUser.setPhone(user.getPhone());
        if (user.getDocType().getCode() != null)
            updateUser.getDocType().setCode(user.getDocType().getCode());
        if (user.getDocNumber() != null)
            updateUser.setDocNumber(user.getDocNumber());
        if (user.getDocDate() != null)
            updateUser.setDocDate(user.getDocDate());
        if (user.getCountry().getCode() != null)
            updateUser.getCountry().setCode(user.getCountry().getCode());
        if (user.getIdentified() != null)
            updateUser.setIdentified(user.getIdentified());
        userRepository.save(updateUser);
    }


    /**
     * Сохранение
     * Запись нового сотрудника в базу, по переданным парметрам в теле объекта userUpdateView
     *
     * @param user - объект содержащий параметры сотрудника, для сохранения их в базе
     */
    @Override
    @Transactional
    public void save(User user) throws CustomUserException {
        if (officeRepository.findOne(user.getOffice().getId()) != null) {
            if (user.getFirstName() == null &
                    user.getSecondName() == null &
                    user.getMiddleName() == null &
                    user.getPosition() == null &
                    user.getPhone() == null &
                    user.getDocType().getCode() == null &
                    user.getDocNumber() == null &
                    user.getDocDate() == null &
                    user.getCountry().getCode() == null &
                    user.getIdentified() == null
                    ) {
                throw new CustomUserException("Поля не заполнены! Для сохранения, необходимо заполнить, хотя бы, одно поле, помимо Id офиса!");
            }
            User sUser = new User();
            if (user.getFirstName() != null)
                sUser.setFirstName(user.getFirstName());
            if (user.getSecondName() != null)
                sUser.setSecondName(user.getSecondName());
            if (user.getMiddleName() != null)
                sUser.setMiddleName(user.getMiddleName());
            if (user.getPosition() != null)
                sUser.setPosition(user.getPosition());
            if (user.getPhone() != null)
                sUser.setPhone(user.getPhone());
            if (user.getDocType().getCode() != null)
                sUser.setDocType(docTypeRepository.findDocTypeByCode(user.getDocType().getCode()));
            if (user.getDocNumber() != null)
                sUser.setDocNumber(user.getDocNumber());
            if (user.getDocDate() != null)
                sUser.setDocDate(user.getDocDate());
            if (user.getCountry().getCode() != null)
                sUser.setCountry(countryRepository.findCountryByCode(user.getCountry().getCode()));
            if (user.getIdentified() != null)
                sUser.setIdentified(user.getIdentified());
            userRepository.save(sUser);
        } else {
            throw new CustomUserException("По officeId: " + user.getOffice().getId() + " офис не найден! Для заведения нового сотрудника, нужно прикрепить сотрудника к существующему офису!");
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
    public UserIdViewResponse findById(Long id) throws CustomUserException {
        if (userRepository.findOne(id) == null) {
            throw new CustomUserException("С Id = " + id + ", сотрудники не найдены! Введите существующий Id.");
        }
        User user = userRepository.findOne(id);
        UserIdViewResponse userIdViewResponse = new UserIdViewResponse();
        userIdViewResponse.setId(user.getId());
        userIdViewResponse.setFirstName(user.getFirstName());
        userIdViewResponse.setSecondName(user.getSecondName());
        userIdViewResponse.setMiddleName(user.getMiddleName());
        userIdViewResponse.setPosition(user.getPosition());
        userIdViewResponse.setPhone(user.getPhone());
        userIdViewResponse.setDocName(user.getDocType().getName());
        userIdViewResponse.setDocCode(user.getDocType().getCode());
        userIdViewResponse.setDocNumber(user.getDocNumber());
        userIdViewResponse.setDocDate(user.getDocDate());
        userIdViewResponse.setCitizenshipCode(user.getCountry().getCode());
        userIdViewResponse.setCitizenshipName(user.getCountry().getName());
        userIdViewResponse.setIdentified(user.getIdentified());
        return userIdViewResponse;
    }

    /**
     * Удаление из базы сотрудника по Id
     *
     * @param user - объект содержащий параметр Id сотрудника, для удаления по нему из базы
     */
    @Override
    @Transactional
    public void delete(User user) throws CustomUserException {
        if (userRepository.findOne(user.getId()) == null) {
            throw new CustomUserException("С данным Id, сотрудник не найден!");
        }
        userRepository.delete(user.getId());
    }

    @Autowired
    private ModelMapper modelMapper;

    public User convertToEntity(UserDeleteViewRequest userDeleteViewRequest) throws ParseException {
        User user = modelMapper.map(userDeleteViewRequest, User.class);
        return user;
    }

    public User convertToEntity(UserListViewRequest userListViewRequest) throws ParseException {
        User user = modelMapper.map(userListViewRequest, User.class);
            Office office = new Office();
            office.setId(userListViewRequest.getOfficeId());
            user.setOffice(office);
            DocType docType = new DocType();
            docType.setCode(userListViewRequest.getDocCode());
            user.setDocType(docType);
            Country country = new Country();
            country.setCode(userListViewRequest.getCitizenshipCode());
            user.setCountry(country);

        return user;
    }

    public User convertToEntity(UserUpdateViewRequest userUpdateViewRequest) throws ParseException {
        User user = modelMapper.map(userUpdateViewRequest, User.class);
            DocType docType = new DocType();
            docType.setCode(userUpdateViewRequest.getDocCode());
            docType.setName(userUpdateViewRequest.getDocName());
            user.setDocType(docType);
            Country country = new Country();
            country.setCode(userUpdateViewRequest.getCitizenshipCode());
            country.setName(userUpdateViewRequest.getCitizenshipName());
            user.setCountry(country);
        return user;
    }

    public User convertToEntity(UserSaveViewRequest userSaveViewRequest) throws ParseException {
        User user = modelMapper.map(userSaveViewRequest, User.class);
        if (userSaveViewRequest.getOfficeId() != null) {
            Office office = new Office();
            office.setId(userSaveViewRequest.getOfficeId());
            user.setOffice(office);
        }
        if (userSaveViewRequest.getDocCode() != null) {
            DocType docType = new DocType();
            docType.setCode(userSaveViewRequest.getDocCode());
            user.setDocType(docType);
        }
        if (userSaveViewRequest.getCitizenshipCode() != null) {
            Country country = new Country();
            country.setCode(userSaveViewRequest.getCitizenshipCode());
            user.setCountry(country);
        }
        return user;
    }
}



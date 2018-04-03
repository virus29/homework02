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
     * @param userListViewRequest - Объект содержащий параметры для поиска сотрудника
     * @return List<User> - список сотрудников, отвечающий критериям поиска
     */
    @Transactional(readOnly = true)
    @Override
    public List<UserListViewResponse> searchUser(UserListViewRequest userListViewRequest) throws CustomUserException, ParseException {
        User user = convertToEntity(userListViewRequest);
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
     * @param userUpdateViewRequest - объект содержащий параметры сотрудника, для изменения данных хранящихся в базе
     */
    @Override
    @Transactional
    public void update(UserUpdateViewRequest userUpdateViewRequest) throws CustomUserException, ParseException {
        User user = convertToEntity(userUpdateViewRequest);
        if (userRepository.findOne(user.getId()) == null) {
            throw new CustomUserException("Сотрудник с Id: " + user.getId() + " не найден!");
        }
        User updateUser = userRepository.findOne(user.getId());
        updateUser.setFirstName(user.getFirstName());
        updateUser.setSecondName(user.getSecondName());
        updateUser.setMiddleName(user.getMiddleName());
        updateUser.setPosition(user.getPosition());
        updateUser.setPhone(user.getPhone());
        updateUser.getDocType().setCode(user.getDocType().getCode());
        updateUser.setDocNumber(user.getDocNumber());
        updateUser.setDocDate(user.getDocDate());
        updateUser.getCountry().setCode(user.getCountry().getCode());
        updateUser.setIdentified(user.getIdentified());
        userRepository.save(updateUser);
    }


    /**
     * Сохранение
     * Запись нового сотрудника в базу, по переданным парметрам в теле объекта userUpdateView
     *
     * @param userSaveViewRequest - объект содержащий параметры сотрудника, для сохранения их в базе
     */
    @Override
    @Transactional
    public void save(UserSaveViewRequest userSaveViewRequest) throws CustomUserException, ParseException {
        User user = convertToEntity(userSaveViewRequest);
        if (officeRepository.findOne(user.getOffice().getId()) != null) {
            if (user.getFirstName() == null & user.getSecondName() == null
                    ) {
                throw new CustomUserException("Поля: firstName и secondName не заполнены! Для сохранения, необходимо заполнить Имя и Фамилию, помимо Id офиса!");
            }
            User sUser = new User();
            Office office = officeRepository.findOne(user.getOffice().getId());
            sUser.setOffice(office);
            sUser.setFirstName(user.getFirstName());
            sUser.setSecondName(user.getSecondName());
            sUser.setMiddleName(user.getMiddleName());
            sUser.setPosition(user.getPosition());
            sUser.setPhone(user.getPhone());
            sUser.setDocType(docTypeRepository.findDocTypeByCode(user.getDocType().getCode()));
            sUser.setDocNumber(user.getDocNumber());
            sUser.setDocDate(user.getDocDate());
            sUser.setCountry(countryRepository.findCountryByCode(user.getCountry().getCode()));
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
     * @param userDeleteViewRequest - объект содержащий параметр Id сотрудника, для удаления по нему из базы
     */
    @Override
    @Transactional
    public void delete(UserDeleteViewRequest userDeleteViewRequest) throws CustomUserException, ParseException {
        User user = convertToEntity(userDeleteViewRequest);
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
            Office office = new Office();
            office.setId(userSaveViewRequest.getOfficeId());
            user.setOffice(office);

            DocType docType = new DocType();
            docType.setCode(userSaveViewRequest.getDocCode());
            user.setDocType(docType);

            Country country = new Country();
            country.setCode(userSaveViewRequest.getCitizenshipCode());
            user.setCountry(country);
        return user;
    }
}



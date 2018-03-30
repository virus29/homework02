package com.i.homework02.controller;

import com.i.homework02.entity.User;
import com.i.homework02.exeption.CustomUserException;
import com.i.homework02.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.i.homework02.view.*;


import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping(value = "/api/user", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

    @Autowired
    private UserServiceImpl userServiceImpl;

    /**
     * Поиск по нескольким параметрам сотрудника
     *
     * @param userListViewRequest
     * @return список сотрудников исходя из параметров поиска
     */
    @PostMapping(value = "/list")
    public ResponseEntity searchUser(@RequestBody @Valid UserListViewRequest userListViewRequest) throws CustomUserException, ParseException {
        User user = userServiceImpl.convertToEntity(userListViewRequest);
        List<UserListViewResponse> listUsers = userServiceImpl.searchUser(user);
        DataView<List<UserListViewResponse>> dataView = new DataView(listUsers);
        return new ResponseEntity<>(dataView, HttpStatus.FOUND);
    }

    /**
     * Поиск по Id Сотрудника
     * @param id - Id сотрудника
     * @return userIdViewOut - сотрудник найденый по Id
     */
    @GetMapping(path = "/{id}")
    public ResponseEntity findById(@PathVariable Long id) throws CustomUserException {
        UserIdViewResponse us = userServiceImpl.findById(id);
        DataView<UserIdViewResponse> dataView = new DataView<>(us);
        return new ResponseEntity<>(dataView, HttpStatus.FOUND);
    }

    /**
     * Изменение(обновление) параметров сотрудника по переданным парметрам в теле объекта userUpdateViewRequest
     *
     * @param userUpdateViewRequest - объект содержащий параметры сотрудника, для изменения данных хранящихся в базе
     */
    @PostMapping(value = "/update")
    public ResponseEntity updaterUser(@RequestBody @Valid UserUpdateViewRequest userUpdateViewRequest) throws CustomUserException, ParseException {
        User user = userServiceImpl.convertToEntity(userUpdateViewRequest);
        userServiceImpl.update(user);
        return new ResponseEntity<>(new PositiveResponseView(), HttpStatus.OK);
    }

    /**
     * Сохранение
     * Запись нового сотрудника в базу, по переданным парметрам в теле объекта userUpdateView
     *
     * @param userSaveViewRequest - объект содержащий параметры сотрудника, для сохранения их в базе
     */
    @PostMapping(value = "/save")
    public ResponseEntity addUser(@RequestBody @Valid UserSaveViewRequest userSaveViewRequest) throws CustomUserException, ParseException {
        User user=userServiceImpl.convertToEntity(userSaveViewRequest);
        userServiceImpl.save(user);
        return new ResponseEntity<>(new PositiveResponseView(), HttpStatus.CREATED);
    }

    /**
     * Удаление из базы сотрудника по Id
     *
     * @param userDeleteViewRequest - объект содержащий параметр Id сотрудника, для удаления по нему из базы
     */
    @PostMapping(value = "/delete")
    public ResponseEntity delete(@RequestBody @Valid UserDeleteViewRequest userDeleteViewRequest) throws CustomUserException, ParseException {
        User user=userServiceImpl.convertToEntity(userDeleteViewRequest);
        userServiceImpl.delete(user);
        return new ResponseEntity<>(new PositiveResponseView(), HttpStatus.OK);
    }
}

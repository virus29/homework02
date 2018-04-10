package com.i.homework02.controller;

import com.i.homework02.exeption.CustomUserException;
import com.i.homework02.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@Api(value = "/api/user", description = "Операции над сотрудниками")
@RequestMapping(value = "/api/user", produces = MediaType.APPLICATION_JSON_VALUE)

public class UserController {

    @Autowired
    private UserService userService;

    /**
     * Поиск по нескольким параметрам сотрудника
     *
     * @param userListViewRequest
     * @return список сотрудников исходя из параметров поиска
     */
    @PostMapping(value = "/list")
    @ApiOperation(value = "Поиск по нескольким параметрам сотрудника")
    public ResponseEntity searchUser(@RequestBody @Valid UserListViewRequest userListViewRequest) throws CustomUserException, ParseException {
        List<UserListViewResponse> listUsers = userService.searchUser(userListViewRequest);
        DataView<List<UserListViewResponse>> dataView = new DataView(listUsers);
        return new ResponseEntity<>(dataView, HttpStatus.FOUND);
    }

    /**
     * Поиск по Id Сотрудника
     * @param id - Id сотрудника
     * @return userIdViewOut - сотрудник найденый по Id
     */
    @GetMapping(path = "/{id}")
    @ApiOperation(value = "Поиск по Id Сотрудника")
    public ResponseEntity findById(@PathVariable Long id) throws CustomUserException {
        UserIdViewResponse us = userService.findById(id);
        DataView<UserIdViewResponse> dataView = new DataView<>(us);
        return new ResponseEntity<>(dataView, HttpStatus.FOUND);
    }

    /**
     * Изменение(обновление) параметров сотрудника по переданным парметрам в теле объекта userUpdateViewRequest
     *
     * @param userUpdateViewRequest - объект содержащий параметры сотрудника, для изменения данных хранящихся в базе
     */
    @PostMapping(value = "/update")
    @ApiOperation(value = "Изменение(обновление) параметров сотрудника по переданным парметрам в теле объекта userUpdateViewRequest")
    public ResponseEntity updaterUser(@RequestBody @Valid UserUpdateViewRequest userUpdateViewRequest) throws CustomUserException, ParseException {
        userService.update(userUpdateViewRequest);
        return new ResponseEntity<>(new PositiveResponseView(), HttpStatus.OK);
    }

    /**
     * Сохранение
     * Запись нового сотрудника в базу, по переданным парметрам в теле объекта userUpdateView
     *
     * @param userSaveViewRequest - объект содержащий параметры сотрудника, для сохранения их в базе
     */
    @PostMapping(value = "/save")
    @ApiOperation(value = "Сохранение. Запись нового сотрудника в базу, по переданным парметрам в теле объекта userUpdateView")
    public ResponseEntity addUser(@RequestBody @Valid UserSaveViewRequest userSaveViewRequest) throws CustomUserException, ParseException {
        userService.save(userSaveViewRequest);
        return new ResponseEntity<>(new PositiveResponseView(), HttpStatus.CREATED);
    }

    /**
     * Удаление из базы сотрудника по Id
     *
     * @param userDeleteViewRequest - объект содержащий параметр Id сотрудника, для удаления по нему из базы
     */
    @PostMapping(value = "/delete")
    @ApiOperation(value = "Удаление из базы сотрудника")
    public ResponseEntity delete(@RequestBody @Valid UserDeleteViewRequest userDeleteViewRequest) throws CustomUserException, ParseException {
        userService.delete(userDeleteViewRequest);
        return new ResponseEntity<>(new PositiveResponseView(), HttpStatus.OK);
    }
}

package com.i.homework02.controller;

import com.i.homework02.entity.User;
import com.i.homework02.exeption.CustomUserException;
import com.i.homework02.servise.serviceImpl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.i.homework02.view.*;


import java.util.List;

@RestController
@RequestMapping(value = "/api/user", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

    @Autowired
    private UserServiceImpl userServiceImpl;

    /**
     * Поиск по нескольким параметрам сотрудника
     * @param userListViewIn
     * @return
     */
    @PostMapping(value = "/list")
    public ResponseEntity searchUser(@RequestBody @Valid UserListViewIn userListViewIn) throws CustomUserException {
        List<UserListViewOut> listUsers = userServiceImpl.searchUser(userListViewIn.getOfficeId(), userListViewIn.getFirstName(),userListViewIn.getSecondName(),userListViewIn.getMiddleName(),userListViewIn.getPosition(),userListViewIn.getDocCode(), userListViewIn.getCitizenshipCode());
        DataView<List<UserListViewOut>> dataView =new DataView<>(listUsers);
        return new ResponseEntity<>(dataView, HttpStatus.FOUND);
    }

    /**
     * Поиск по Id сотрудника
     * @param id
     * @return
     */
    @GetMapping(path = "/{id}")
    public ResponseEntity findById(@PathVariable Long id) throws CustomUserException {
        UserIdViewOut us=userServiceImpl.findById(id);
        DataView<UserIdViewOut> dataView =new DataView<>(us);
        return new ResponseEntity<>(dataView, HttpStatus.FOUND);
    }

    /**
     * Изменение(обновление) данных сотрудника
     * @param userUpdateView
     * @return
     */
    @PostMapping(value = "/update")
    public
    ResponseEntity updaterUser(@RequestBody @Valid UserUpdateView userUpdateView) throws CustomUserException {
     userServiceImpl.update(userUpdateView);
     return new ResponseEntity<>(new PositiveResponseView(), HttpStatus.OK);}

    /**
     * Сохранение сотрудника
     * @param userSaveView
     * @return
     */
    @PostMapping(value = "/save")
    public ResponseEntity addUser(@RequestBody @Valid UserSaveView userSaveView) throws CustomUserException {
     userServiceImpl.save(userSaveView);
     return new ResponseEntity<>(new PositiveResponseView(), HttpStatus.CREATED);}

    /**
     *Удаление сотрудника
     * @param userUpdateView
     * @return
     */
    @PostMapping(value = "/delete")
    public ResponseEntity delete(@RequestBody @Valid UserUpdateView userUpdateView) throws CustomUserException {
    userServiceImpl.delete(userUpdateView);
    return new ResponseEntity<>(new PositiveResponseView(), HttpStatus.OK); }
}

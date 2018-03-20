package com.i.homework02.user;

import com.fasterxml.jackson.annotation.JsonView;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import view.*;


import java.util.List;

@RestController
@RequestMapping(value = "/api/user", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

    @Autowired
    private UserServiceImpl userServiceImpl;

    //Поиск по officeId, firstName, secondName, middleName, position, docCode, citizenshipCode параметрам
    @PostMapping(value = "/list")
    public ResponseEntity searchUser(@RequestBody @Valid UserListViewIn userListViewIn){
        List<UserListViewOut> listUsers = userServiceImpl.searchUser(userListViewIn.getOfficeId(), userListViewIn.getFirstName(),userListViewIn.getSecondName(),userListViewIn.getMiddleName(),userListViewIn.getPosition(),userListViewIn.getDocCode(), userListViewIn.getCitizenshipCode());
        DataView<List<UserListViewOut>> dataView =new DataView<>(listUsers);
        return new ResponseEntity<>(dataView, HttpStatus.FOUND);
    }

    //Поиск по Id
    /**
     *
     * @param id
     * @return
     */
    @GetMapping(path = "/{id}")
    public ResponseEntity findById(@PathVariable Long id) {
        UserIdViewOut us=userServiceImpl.findById(id);
        DataView<UserIdViewOut> dataView =new DataView<>(us);
        return new ResponseEntity<>(dataView, HttpStatus.FOUND);
    }

    //Изменение(обновление)
    /**
     *
     * @param userUpdateView
     * @return
     */
    @PostMapping(value = "/update")
    public
    ResponseEntity updaterUser(@RequestBody @Valid UserUpdateView userUpdateView){
     userServiceImpl.update(userUpdateView);
     return new ResponseEntity<>(new PositiveResponseView(), HttpStatus.OK);}

    //Сохранение
    /**
     *
     * @param userSaveView
     * @return
     */
    @PostMapping(value = "/save")
    public ResponseEntity addUser(@RequestBody @Valid UserSaveView userSaveView){
     userServiceImpl.save(userSaveView);
     return new ResponseEntity<>(new PositiveResponseView(), HttpStatus.CREATED);}

    //Удаление

    /**
     *
     * @param user
     * @return
     */
    @PostMapping(value = "/delete")
    public ResponseEntity delete(@RequestBody @Valid User user){
    userServiceImpl.delete(user);
    return new ResponseEntity<>(new PositiveResponseView(), HttpStatus.OK); }
}

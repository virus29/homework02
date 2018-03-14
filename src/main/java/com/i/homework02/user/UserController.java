package com.i.homework02.user;

import com.fasterxml.jackson.annotation.JsonView;

import com.i.homework02.user.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import javax.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@RestController
@RequestMapping(value = "/api/user", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

    @Autowired
    private UserServiceImpl userServiceImpl;

//    final Logger logger = LoggerFactory.getLogger(UserController.class);



    //Поиск по officeId, firstName, secondName, middleName, position, docCode, citizenshipCode параметрам
    @JsonView({UserView.FindByOffidFNameLNameMnamePositionDoccodeCitcode.class})
    @PostMapping(value = "/list")
    @ResponseStatus(HttpStatus.FOUND)
    public @ResponseBody
    List<User> searchUser(@Valid User user){
//        logger.info(user.toString());
        return userServiceImpl.searchUser(user.getOfficeId(), user.getFirstName(),user.getSecondName(),user.getMiddleName(),user.getPosition(),user.getDocCode(), user.getCitizenshipCode());}

    //Поиск Id
    @JsonView(UserView.FindById.class)
    @GetMapping(path = "/{id}")
//    @ResponseStatus(HttpStatus.FOUND)
    public @ResponseBody User findById(@PathVariable Long id) {
        return userServiceImpl.findById(id);
    }

    //Изменение(обновление)
    @PostMapping(value = "/update")
    @ResponseStatus(HttpStatus.UPGRADE_REQUIRED)
    public @ResponseBody
    User updaterUser(@Valid User user){
    return userServiceImpl.update(user);}

    //Сохранение
    @PostMapping(value = "/save")
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody
    User addUser(@Valid User user){
    return userServiceImpl.save(user);}

    //Удаление
    @PostMapping(value = "/delete")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody
    void updaterser(@Valid User user){
    userServiceImpl.delete(user);}
}
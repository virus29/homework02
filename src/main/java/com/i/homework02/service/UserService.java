package com.i.homework02.service;

import com.i.homework02.entity.User;
import com.i.homework02.exeption.CustomUserException;
import com.i.homework02.view.*;

import java.text.ParseException;
import java.util.List;

public interface UserService {

    /**
     * Поиск списка сотрудников по параметрам
     * @param userListViewRequest        - Объект содержащий параметры для поиска сотрудника
     * @return List<User> - список сотрудников, отвечающий критериям поиска
     */
    public List<UserListViewResponse> searchUser (UserListViewRequest userListViewRequest) throws CustomUserException, ParseException;


    /**
     * Изменение(обновление) параметров сотрудника по переданным парметрам в теле объекта userUpdateViewRequest
     * @param userUpdateViewRequest - объект содержащий параметры сотрудника, для изменения данных хранящихся в базе
     */
    public void update(UserUpdateViewRequest userUpdateViewRequest) throws CustomUserException, ParseException;

    /**
     * Сохранение
     * Запись нового сотрудника в базу, по переданным парметрам в теле объекта userUpdateView
     * @param userSaveViewRequest - объект содержащий параметры сотрудника, для сохранения их в базе
     */
    public void save(UserSaveViewRequest userSaveViewRequest) throws CustomUserException, ParseException;

    /**
     * Поиск по Id Сотрудника
     * @param id - Id сотрудника
     * @return userIdViewOut - сотрудник найденый по Id
     */
    public UserIdViewResponse findById(Long id) throws CustomUserException;

    /**
     * Удаление из базы сотрудника по Id
     * @param userDeleteViewRequest - объект содержащий параметр Id сотрудника, для удаления по нему из базы
     */
    public void delete(UserDeleteViewRequest userDeleteViewRequest) throws CustomUserException, ParseException;
}

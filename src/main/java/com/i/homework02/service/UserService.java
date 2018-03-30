package com.i.homework02.service;

import com.i.homework02.entity.User;
import com.i.homework02.exeption.CustomUserException;
import com.i.homework02.view.UserIdViewResponse;
import com.i.homework02.view.UserListViewResponse;
import com.i.homework02.view.UserSaveViewRequest;
import com.i.homework02.view.UserUpdateViewRequest;

import java.util.List;

public interface UserService {

    /**
     * Поиск списка сотрудников по параметрам
     * @param user        - Объект содержащий параметры для поиска сотрудника
     * @return List<User> - список сотрудников, отвечающий критериям поиска
     */
    public List<UserListViewResponse> searchUser (User user) throws CustomUserException;


    /**
     * Изменение(обновление) параметров сотрудника по переданным парметрам в теле объекта userUpdateViewRequest
     * @param user - объект содержащий параметры сотрудника, для изменения данных хранящихся в базе
     */
    public void update(User user) throws CustomUserException;

    /**
     * Сохранение
     * Запись нового сотрудника в базу, по переданным парметрам в теле объекта userUpdateView
     * @param user - объект содержащий параметры сотрудника, для сохранения их в базе
     */
    public void save(User user) throws CustomUserException;

    /**
     * Поиск по Id Сотрудника
     * @param id - Id сотрудника
     * @return userIdViewOut - сотрудник найденый по Id
     */
    public UserIdViewResponse findById(Long id) throws CustomUserException;

    /**
     * Удаление из базы сотрудника по Id
     * @param user - объект содержащий параметр Id сотрудника, для удаления по нему из базы
     */
    public void delete(User user) throws CustomUserException;
}

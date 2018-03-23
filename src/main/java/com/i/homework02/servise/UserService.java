package com.i.homework02.servise;

import com.i.homework02.entity.User;
import com.i.homework02.exeption.CustomUserException;
import com.i.homework02.view.UserIdViewOut;
import com.i.homework02.view.UserListViewOut;
import com.i.homework02.view.UserSaveView;
import com.i.homework02.view.UserUpdateView;

import java.util.List;

public interface UserService {

    /**
     * Поиск по нескольким параметрам
     * @param officeId
     * @param firstName
     * @param secondName
     * @param middleName
     * @param position
     * @param docCode
     * @param citizenshipCode
     * @return
     */
    public List<UserListViewOut> searchUser (Long officeId, String firstName, String secondName, String middleName, String position, String docCode, String citizenshipCode) throws CustomUserException;

    /**
     * Изменение(обновление)
     * @param userUpdateView
     */
    public void update(UserUpdateView userUpdateView) throws CustomUserException;

    /**
     * Сохранение
     * @param userSaveView
     */
    public void save(UserSaveView userSaveView) throws CustomUserException;

    /**
     * Поиск по Id
     * @param id
     * @return
     */
    public UserIdViewOut findById(Long id) throws CustomUserException;

    /**
     * Удаление
     * @param userUpdateView
     */
    public void delete(UserUpdateView userUpdateView) throws CustomUserException;
}

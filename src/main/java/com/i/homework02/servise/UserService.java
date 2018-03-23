package com.i.homework02.servise;

import com.i.homework02.exeption.CustomUserException;
import com.i.homework02.view.UserIdViewOut;
import com.i.homework02.view.UserListViewOut;
import com.i.homework02.view.UserSaveViewIn;
import com.i.homework02.view.UserUpdateView;

import java.util.List;

public interface UserService {

    /**
     * Поиск списка сотрудников по параметрам
     * @param officeId        - Id офиса, которому принадлежит сотрудник
     * @param firstName       - имя сотрудника
     * @param secondName      -фамилия сотрудника
     * @param middleName      - отчество сотрудника
     * @param position        - позиция(должность) сотрудника занимаемая в организации
     * @param docCode         -Код документа сотрудника
     * @param citizenshipCode - Код страны, гражданином, которой сотрудник является
     * @return List<User> - список сотрудников, отвечающий критериям поиска
     */
    public List<UserListViewOut> searchUser (Long officeId, String firstName, String secondName, String middleName, String position, String docCode, String citizenshipCode) throws CustomUserException;


    /**
     * Изменение(обновление) параметров сотрудника по переданным парметрам в теле объекта userUpdateView
     * @param userUpdateView - объект содержащий параметры сотрудника, для изменения данных хранящихся в базе
     */
    public void update(UserUpdateView userUpdateView) throws CustomUserException;

    /**
     * Сохранение
     * Запись нового сотрудника в базу, по переданным парметрам в теле объекта userUpdateView
     * @param userSaveViewIn - объект содержащий параметры сотрудника, для сохранения их в базе
     */
    public void save(UserSaveViewIn userSaveViewIn) throws CustomUserException;

    /**
     * Поиск по Id Сотрудника
     * @param id - Id сотрудника
     * @return userIdViewOut - сотрудник найденый по Id
     */
    public UserIdViewOut findById(Long id) throws CustomUserException;

    /**
     * Удаление из базы сотрудника по Id
     * @param userUpdateView - объект содержащий параметр Id сотрудника, для удаления по нему из базы
     */
    public void delete(UserUpdateView userUpdateView) throws CustomUserException;
}

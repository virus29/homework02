package com.i.homework02.user;

import view.UserIdViewOut;
import view.UserListViewOut;
import view.UserSaveView;
import view.UserUpdateView;

import java.util.List;

public interface UserService {
    //Поиск по нескольким параметрам
    public List<UserListViewOut> searchUser (Long officeId, String firstName, String secondName, String middleName, String position, String docCode, String citizenshipCode);

    //Изменение(обновление)
    public void update(UserUpdateView userUpdateView);

    //Сохранение
    public void save(UserSaveView userSaveView);

    //Поиск по Id
    public UserIdViewOut findById(Long id);

    //Удаление
    public void delete(User user);
}

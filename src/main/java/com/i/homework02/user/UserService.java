package com.i.homework02.user;

import java.util.List;

public interface UserService {
    //Поиск по нескольким параметрам
    public List<User> searchUser (Long officeId, String firstName,String secondName,String middleName,String position,String docCode, String citizenshipCode);

    //Изменение(обновление)
    public User update(User user);

    //Сохранение
    public User save(User user);

    //Поиск по Id
    public User findById(Long id);

    //Удаление
    public void delete(User user);
}

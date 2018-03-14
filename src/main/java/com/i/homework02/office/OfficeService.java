package com.i.homework02.office;

import java.util.List;

public interface OfficeService {

    //Поиск по нескольким параметрам
    public List<Office> searchOffice(Long organizationId, String officeName, String officePhone, Boolean officeIsactive);

    //Изменение(обновление)
    public void update(Office office);

    //Сохранение
    public Office save(Office office);

    //Поиск по Id
    public Office findById(Long id);

    //Удаление
    public void delete(Office office);
}

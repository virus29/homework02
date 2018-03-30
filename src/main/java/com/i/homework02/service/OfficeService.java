package com.i.homework02.service;

import com.i.homework02.entity.Office;
import com.i.homework02.exeption.CustomOfficeException;
import com.i.homework02.view.*;

import java.util.List;

public interface OfficeService {


    /**
     * Поиск офиса(ов) по нескольким параметрам
     * @param office - объект с параметрами поиска
     * @return Список офисов полученый из параметров запроса
     */
    public List<OfficeListViewResponse> searchOffice(Office office) throws CustomOfficeException;

    /**
     * Изменение(обновление) параметров офиса
     * @param office - парметры офиса переданные для удаления
     */
    public void update(Office office) throws CustomOfficeException;

    /**
     * Сохранение офиса
     * @param office - объект с параметрами для сохранения
     */
    public void save(Office office) throws CustomOfficeException;

    /**
     * Поиск офиса по Id
     * @param id - id офиса
     * @return - офис найденный по id
     */
    public OfficeViewResponse findById(Long id) throws CustomOfficeException;

    /**
     * Удаление офиса
     * @param office объект с параметром id офиса
     */
    public void delete(Office office) throws CustomOfficeException;
}

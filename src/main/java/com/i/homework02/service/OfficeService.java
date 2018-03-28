package com.i.homework02.service;

import com.i.homework02.exeption.CustomOfficeException;
import com.i.homework02.view.*;

import java.util.List;

public interface OfficeService {


    /**
     * Поиск офиса(ов) по нескольким параметрам
     * @param officeListViewIn - объект с параметрами поиска
     * @return Список офисов полученый из параметров запроса
     */
    public List<OfficeListViewOut> searchOffice(OfficeListViewIn officeListViewIn) throws CustomOfficeException;

    /**
     * Изменение(обновление) параметров офиса
     * @param officeViewIn - парметры офиса переданные для удаления
     */
    public void update(OfficeViewIn officeViewIn) throws CustomOfficeException;

    /**
     * Сохранение офиса
     * @param officeSaveViewIn - объект с параметрами для сохранения
     */
    public void save(OfficeSaveViewIn officeSaveViewIn) throws CustomOfficeException;

    /**
     * Поиск офиса по Id
     * @param id - id офиса
     * @return - офис найденный по id
     */
    public OfficeIdViewOut findById(Long id) throws CustomOfficeException;

    /**
     * Удаление офиса
     * @param officeViewIn объект с параметром id офиса
     */
    public void delete(OfficeViewIn officeViewIn) throws CustomOfficeException;
}

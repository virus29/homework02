package com.i.homework02.service;

import com.i.homework02.entity.Account;
import com.i.homework02.entity.Office;
import com.i.homework02.entity.Organization;
import com.i.homework02.exeption.CustomOfficeException;
import com.i.homework02.view.*;

import java.text.ParseException;
import java.util.List;

public interface OfficeService {


    /**
     * Поиск офиса(ов) по нескольким параметрам
     * @param officeListViewRequest - объект с параметрами поиска
     * @return Список офисов полученый из параметров запроса
     */
    public List<OfficeListViewResponse> searchOffice(OfficeListViewRequest officeListViewRequest) throws CustomOfficeException, ParseException;

    /**
     * Изменение(обновление) параметров офиса
     * @param officeUpdateViewRequest - парметры офиса переданные для удаления
     */
    public void update(OfficeUpdateViewRequest officeUpdateViewRequest) throws CustomOfficeException, ParseException;

    /**
     * Сохранение офиса
     * @param officeSaveViewRequest - объект с параметрами для сохранения
     */
    public void save(OfficeSaveViewRequest officeSaveViewRequest) throws CustomOfficeException, ParseException;

    /**
     * Поиск офиса по Id
     * @param id - id офиса
     * @return - офис найденный по id
     */
    public OfficeViewResponse findById(Long id) throws CustomOfficeException;

    /**
     * Удаление офиса
     * @param officeDeleteViewRequest объект с параметром id офиса
     */
    public void delete(OfficeDeleteViewRequest officeDeleteViewRequest) throws CustomOfficeException, ParseException;

    /**
     * Конвертация объекта DTO  в entity класс
     * @param officeDeleteViewRequest -  объекта DTO Office
     * @return сконвертированный Entity класс Office
     */
    public Office convertToEntity(OfficeDeleteViewRequest officeDeleteViewRequest) throws ParseException;

    /**
     * Конвертация объекта DTO  в entity класс
     * @param officeListViewRequest -  объекта DTO Office
     * @return сконвертированный Entity класс Office
     */
    public Office convertToEntity(OfficeListViewRequest officeListViewRequest) throws ParseException;

    /**
     * Конвертация объекта DTO  в entity класс
     * @param officeSaveViewRequest -  объекта DTO Office
     * @return сконвертированный Entity класс Office
     */
    public Office convertToEntity(OfficeSaveViewRequest officeSaveViewRequest) throws ParseException;

    /**
     * Конвертация объекта DTO  в entity класс
     * @param officeUpdateViewRequest -  объекта DTO Office
     * @return сконвертированный Entity класс Office
     */
    public Office convertToEntity(OfficeUpdateViewRequest officeUpdateViewRequest) throws ParseException;
}

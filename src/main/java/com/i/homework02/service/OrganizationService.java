package com.i.homework02.service;

import com.i.homework02.entity.Organization;
import com.i.homework02.exeption.CustomOrganizationException;
import com.i.homework02.view.*;

import java.text.ParseException;
import java.util.List;

public interface OrganizationService {
//        /**
//         * Поиск организации по нескольким параметрам
//         * @param orgListViewRequest - объект содержащий параметры для поиска
//         * @return список организаций подходящие критериям поиска
//         */
//        public List<OrgListViewResponse> search(OrgListViewRequest orgListViewRequest) throws CustomOrganizationException;


        /**
         * Поиск организации по нескольким параметрам
         * @param orgListViewRequest - объект содержащий параметры для поиска
         * @return список организаций подходящие критериям поиска
         */
        public List<OrgListViewResponse> search(OrgListViewRequest orgListViewRequest) throws CustomOrganizationException, ParseException;

        /**
         * Изменение(обновление) организации
         * @param orgViewRequest - объект содержащий параметры для обновления
         */
        public void update(OrgViewRequest orgViewRequest) throws CustomOrganizationException, ParseException;

        /**
         * Сохранение организации
         * @param orgSaveViewRequest - объект содержащий параметры для сохранения
         */
        public void save(OrgSaveViewRequest orgSaveViewRequest) throws CustomOrganizationException, ParseException;

        /**
         * Поиск по Id организации
         * @param id - Id организации
         * @return - Организация найденная по id
         */
        public OrgViewResponse findById(Long id) throws CustomOrganizationException;

        /**
         * Удаление организации
         * @param orgViewRequest - объект содержащий id организации
         */
        public void delete(OrgViewRequest orgViewRequest) throws CustomOrganizationException, ParseException;

        /**
         * Конвертация объекта DTO  в entity класс
         * @param orgSaveViewRequest -  объекта DTO Organization
         * @return сконвертированный Entity класс Organization
         */
        public Organization convertToEntity(OrgSaveViewRequest orgSaveViewRequest) throws ParseException;

        /**
         * Конвертация объекта DTO  в entity класс
         * @param orgListViewRequest -  объекта DTO Organization
         * @return сконвертированный Entity класс Organization
         */
        public Organization convertToEntity(OrgListViewRequest orgListViewRequest) throws ParseException;

        /**
         * Конвертация объекта DTO  в entity класс
         * @param orgViewRequest -  объекта DTO Organization
         * @return сконвертированный Entity класс Organization
         */
        public Organization convertToEntity(OrgViewRequest orgViewRequest) throws ParseException;
}

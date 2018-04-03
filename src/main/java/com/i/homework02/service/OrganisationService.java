package com.i.homework02.service;

import com.i.homework02.entity.Organization;
import com.i.homework02.exeption.CustomOrganizationException;
import com.i.homework02.view.*;

import java.text.ParseException;
import java.util.List;

public interface OrganisationService {
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
}

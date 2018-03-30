package com.i.homework02.service;

import com.i.homework02.entity.Organization;
import com.i.homework02.exeption.CustomOrganizationException;
import com.i.homework02.view.*;

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
         * @param organization - объект содержащий параметры для поиска
         * @return список организаций подходящие критериям поиска
         */
        public List<OrgListViewResponse> search(Organization organization) throws CustomOrganizationException;

        /**
         * Изменение(обновление) организации
         * @param organization - объект содержащий параметры для обновления
         */
        public void update(Organization organization) throws CustomOrganizationException;

        /**
         * Сохранение организации
         * @param organization - объект содержащий параметры для сохранения
         */
        public void save(Organization organization) throws CustomOrganizationException;

        /**
         * Поиск по Id организации
         * @param id - Id организации
         * @return - Организация найденная по id
         */
        public OrgViewResponse findById(Long id) throws CustomOrganizationException;

        /**
         * Удаление организации
         * @param organization - объект содержащий id организации
         */
        public void delete(Organization organization) throws CustomOrganizationException;
}

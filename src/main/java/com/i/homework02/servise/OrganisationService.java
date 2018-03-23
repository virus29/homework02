package com.i.homework02.servise;

import com.i.homework02.entity.Organization;
import com.i.homework02.exeption.CustomOrganizationException;
import com.i.homework02.view.OrgIdViewOut;
import com.i.homework02.view.OrgListViewIn;
import com.i.homework02.view.OrgListViewOut;
import com.i.homework02.view.OrgViewIn;

import java.util.List;

public interface OrganisationService {
        /**
         * Поиск организации по нескольким параметрам
         * @param orgListViewIn - объект содержащий параметры для поиска
         * @return список организаций подходящие критериям поиска
         */
        public List<OrgListViewOut> search(OrgListViewIn orgListViewIn) throws CustomOrganizationException;

        /**
         * Изменение(обновление) организации
         * @param orgViewIn - объект содержащий параметры для обновления
         */
        public void update(OrgViewIn orgViewIn) throws CustomOrganizationException;

        /**
         * Сохранение организации
         * @param orgViewIn - объект содержащий параметры для сохранения
         */
        public void save(OrgViewIn orgViewIn) throws CustomOrganizationException;

        /**
         * Поиск по Id организации
         * @param id - Id организации
         * @return - Организация найденная по id
         */
        public OrgIdViewOut findById(Long id) throws CustomOrganizationException;

        /**
         * Удаление организации
         * @param orgViewIn - объект содержащий id организации
         */
        public void delete(OrgViewIn orgViewIn) throws CustomOrganizationException;
}

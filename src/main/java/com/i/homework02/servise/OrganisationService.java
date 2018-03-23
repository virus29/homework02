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
         * Поиск по нескольким параметрам
         * @param orgListViewIn
         * @return
         */
        public List<OrgListViewOut> search(OrgListViewIn orgListViewIn) throws CustomOrganizationException;

        /**
         * Изменение(обновление)
         * @param orgViewIn
         */
        public void update(OrgViewIn orgViewIn) throws CustomOrganizationException;

        /**
         * Сохранение
         * @param orgViewIn
         */
        public void save(OrgViewIn orgViewIn) throws CustomOrganizationException;

        /**
         * Поиск по Id
         * @param id
         * @return
         */
        public OrgIdViewOut findById(Long id) throws CustomOrganizationException;

        /**
         * Удаление
         * @param orgViewIn
         */
        public void delete(OrgViewIn orgViewIn) throws CustomOrganizationException;
}

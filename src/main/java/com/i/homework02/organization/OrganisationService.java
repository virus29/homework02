package com.i.homework02.organization;

import view.OrgIdViewOut;
import view.OrgListViewIn;
import view.OrgListViewOut;

import java.util.List;

public interface OrganisationService {

        //Поиск по нескольким параметрам
        public List<OrgListViewOut> search(OrgListViewIn orgListViewIn);

        //Изменение(обновление)
        public void update(Organization organization);

        //Сохранение
        public void save(Organization organization);

        //Поиск по Id
        public OrgIdViewOut findById(Long id);

        //Удаление
        public void delete(Organization organization);
}

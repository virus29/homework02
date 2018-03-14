package com.i.homework02.organization;

import java.util.List;

public interface OrganisationService {

        //Поиск по нескольким параметрам
        public List<Organization> search(String name, Long inn, Boolean isActive);

        //Изменение(обновление)
        public void update(Organization organization);

        //Сохранение
        public void save(Organization organization);

        //Поиск по Id
        public Organization findById(Long id);

        //Удаление
        public void delete(Organization organization);
}

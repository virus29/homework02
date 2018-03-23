package com.i.homework02.servise.serviceImpl;

import com.i.homework02.entity.Office;
import com.i.homework02.exeption.CustomOfficeException;
import com.i.homework02.repository.OfficeRepository;
import com.i.homework02.repository.OrganizationRepository;
import com.i.homework02.repository.UserRepository;
import com.i.homework02.servise.OfficeService;
import com.i.homework02.view.OfficeIdViewOut;
import com.i.homework02.view.OfficeListViewIn;
import com.i.homework02.view.OfficeListViewOut;
import com.i.homework02.view.OfficeSaveViewIn;
import com.i.homework02.view.OfficeViewIn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;


@Service
@Repository
public class OfficeServiceImpl implements OfficeService {
    @Autowired
    private OfficeRepository officeRepository;

    /**
     * Поиск офиса(ов) по нескольким параметрам
     * @param officeListViewIn - объект с параметрами поиска
     * @return Список офисов полученый из параметров запроса
     */
    @Override
    @Transactional(readOnly = true)
    public List<OfficeListViewOut> searchOffice(OfficeListViewIn officeListViewIn) throws CustomOfficeException {
        Specification<Office> findOfficeByParam = new Specification<Office>() {
            @Override
            public Predicate toPredicate(Root<Office> r, CriteriaQuery<?> cq, CriteriaBuilder cb) {
                List<Predicate> prs = new ArrayList<>();
                if (officeListViewIn.getOrgId() != null)
                    prs.add(cb.equal(r.get("organization").get("id"), officeListViewIn.getOrgId()));
                if (officeListViewIn.getName() != null)
                    prs.add(cb.like(r.get("name"), "%" + officeListViewIn.getName() + "%"));
                if (officeListViewIn.getPhone() != null)
                    prs.add(cb.equal(r.get("phone"), officeListViewIn.getPhone()));
                if (officeListViewIn.getActive() != null)
                    prs.add(cb.equal(r.get("isActive"), officeListViewIn.getActive()));
                if (prs.isEmpty()) {
                    return cb.createQuery(Office.class).select(r).getRestriction();
                } else {
                    return cb.and(prs.toArray(new Predicate[prs.size()]));
                }
            }
        };
        if (officeRepository.findAll(findOfficeByParam).isEmpty()) {
            throw new CustomOfficeException("По заданным параметрам ничего не найдено");
        }
        List<Office> os = officeRepository.findAll(findOfficeByParam);
        List<OfficeListViewOut> result = new ArrayList<>();
        for (Office office : os
                ) {
            OfficeListViewOut officeListViewOut = new OfficeListViewOut();
            officeListViewOut.setId(office.getId());
            officeListViewOut.setName(office.getName());
            officeListViewOut.setActive(office.getActive());
            result.add(officeListViewOut);
        }
        return result;
    }

    /**
     * Изменение(обновление) параметров офиса
     * @param officeViewIn - парметры офиса переданные для удаления
     */
    @Override
    @Transactional
    public void update(OfficeViewIn officeViewIn) throws CustomOfficeException {
        if (officeViewIn.getId() == null) throw new CustomOfficeException("Не передан обязательный параметр ID");
        Office officeUpdate = officeRepository.findOne(officeViewIn.getId());
        if (officeUpdate != null) {
            officeUpdate.setName(officeViewIn.getName());
            officeUpdate.setAddress(officeViewIn.getAddress());
            officeUpdate.setPhone(officeViewIn.getPhone());
            officeUpdate.setActive(officeViewIn.getActive());
            officeRepository.save(officeUpdate);
        } else {
            throw new CustomOfficeException("Не найден офис с ID: " + officeViewIn.getId());
        }
    }

    @Autowired
    OrganizationRepository organizationRepository;

    /**
     * Сохранение офиса
     * @param officeSaveViewIn - объект с параметрами для сохранения
     */
    @Override
    @Transactional
    public void save(OfficeSaveViewIn officeSaveViewIn) throws CustomOfficeException {
        if (organizationRepository.findOne(officeSaveViewIn.getOrgId()) != null) {
            if (officeSaveViewIn.getName() == null &
                    officeSaveViewIn.getAddress() == null &
                    officeSaveViewIn.getPhone() == null &
                    officeSaveViewIn.getActive() == null
                    ) {
                throw new CustomOfficeException("Поля не заполнены! Для сохранения, необходимо заполнить, хотя бы, одно поле, помимо Id организации!");
            }
            Office sOffice = new Office();
            if (officeSaveViewIn.getOrgId() != null)
                sOffice.setOrganization(organizationRepository.findOne(officeSaveViewIn.getOrgId()));
            if (officeSaveViewIn.getName() != null)
                sOffice.setName(officeSaveViewIn.getName());
            if (officeSaveViewIn.getAddress() != null)
                sOffice.setAddress(officeSaveViewIn.getAddress());
            if (officeSaveViewIn.getPhone() != null)
                sOffice.setPhone(officeSaveViewIn.getPhone());
            if (officeSaveViewIn.getActive() != null)
                sOffice.setActive(officeSaveViewIn.getActive());

            officeRepository.save(sOffice);
        } else {
            throw new CustomOfficeException("Не возможно добавить офис, принадлежащий организациии, с ID: " + officeSaveViewIn.getOrgId() + ", отсутствующим в базе");
        }
    }


    /**
     * Поиск офиса по Id
     * @param id - id офиса
     * @return - офис найденный по id
     */
    @Override
    @Transactional(readOnly = true)
    public OfficeIdViewOut findById(Long id) throws CustomOfficeException {
        if (officeRepository.findOne(id) != null) {
            Office of = officeRepository.findOne(id);
            OfficeIdViewOut officeIdViewOut = new OfficeIdViewOut();
            officeIdViewOut.setId(of.getId());
            if (of.getName() != null) officeIdViewOut.setName(of.getName());
            if (of.getAddress() != null) officeIdViewOut.setAddress(of.getAddress());
            if (of.getPhone() != null) officeIdViewOut.setPhone(of.getPhone());
            if (of.getActive() != null) officeIdViewOut.setActive(of.getActive());
            return officeIdViewOut;
        } else {
            throw new CustomOfficeException("С Id = " + id + ", офисов не найдено! Введите существующий Id.");
        }
    }


    @Autowired
    UserRepository userRepository;

    /**
     * Удаление офиса
     * @param officeViewIn объект с параметром id офиса
     */
    @Override
    @Transactional
    public void delete(OfficeViewIn officeViewIn) throws CustomOfficeException {
        if (officeViewIn.getId() == null) {
            throw new CustomOfficeException("Для удаления, Id офиса не должно быть пустым");
        }
        if (officeRepository.exists(officeViewIn.getId())) {
            officeRepository.delete(officeViewIn.getId());
        } else {
            throw new CustomOfficeException("С данным Id, офис не найден!");
        }
    }
}

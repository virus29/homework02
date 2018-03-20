package com.i.homework02.office;

import com.i.homework02.exeption.CustomOfficeException;
import com.i.homework02.organization.OrganizationRepository;
import com.i.homework02.user.User;
import com.i.homework02.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import view.OfficeIdViewOut;
import view.OfficeListViewIn;
import view.OfficeListViewOut;
import view.OfficeSaveView;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;


@Service
@Repository
@Transactional
public class OfficeServiceImpl implements OfficeService {
    @Autowired
    private OfficeRepository officeRepository;

    //Поиск офисов по нескольким параметрам.
    @Override
    @Transactional(readOnly = true)
    public List<OfficeListViewOut> searchOffice(OfficeListViewIn officeListViewIn) {
        Specification<Office> findOfficeByParam = new Specification<Office>() {
            @Override
            public Predicate toPredicate(Root<Office> r, CriteriaQuery<?> cq, CriteriaBuilder cb) {
                List<Predicate> prs = new ArrayList<>();
                if (officeListViewIn.getOrgId() != null)
                    prs.add(cb.equal(r.get("organization").get("id"), officeListViewIn.getOrgId()));
                if (officeListViewIn.getName() != null)
                    prs.add(cb.like(r.get("firstName"), "%" + officeListViewIn.getName() + "%"));
                if (officeListViewIn.getPhone() != null)
                    prs.add(cb.like(r.get("secondName"), "%" + officeListViewIn.getPhone() + "%"));
                if (officeListViewIn.getActive() != null)
                    prs.add(cb.like(r.get("middleName"), "%" + officeListViewIn.getActive() + "%"));
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
        List<OfficeListViewOut> olvo = new ArrayList<>();
        for (Office office : os
                ) {
            OfficeListViewOut officeListViewOut = new OfficeListViewOut();
            officeListViewOut.setId(office.getId());
            officeListViewOut.setName(office.getName());
            officeListViewOut.setActive(office.getActive());
            olvo.add(officeListViewOut);
        }
        return olvo;
    }

    //Изменение(обновление) параметров офиса
    @Override
    public void update(Office office) {
        if (office.getId() == null) throw new CustomOfficeException("Не передан обязательный параметр ID");
        Office officeUpdate = officeRepository.findOne(office.getId());
        if (officeUpdate != null) {
            officeUpdate.setName(office.getName());
            officeUpdate.setAddress(office.getAddress());
            officeUpdate.setPhone(office.getPhone());
            officeUpdate.setActive(office.getActive());
            officeRepository.save(officeUpdate);
        } else {
            throw new CustomOfficeException("Не найден офис с ID: " + office.getId());
        }
    }

    @Autowired
    OrganizationRepository organizationRepository;

    //Добавление офиса
    @Override
    public void save(OfficeSaveView officeSaveView) {
        if (organizationRepository.findOne(officeSaveView.getOrgId()) != null) {
            if (officeSaveView.getName() == null &
                    officeSaveView.getAddress() == null &
                    officeSaveView.getPhone() == null &
                    officeSaveView.getActive() == null
                    ) {
                throw new CustomOfficeException("Поля не заполнены! Для сохранения, необходимо заполнить, хотя бы, одно поле, помимо Id организации!");
            }
            Office sOffice = new Office();
            if (officeSaveView.getOrgId() != null)
                sOffice.setOrganization(organizationRepository.findOne(officeSaveView.getOrgId()));
            if (officeSaveView.getName() != null)
                sOffice.setName(officeSaveView.getName());
            if (officeSaveView.getAddress() != null)
                sOffice.setAddress(officeSaveView.getAddress());
            if (officeSaveView.getPhone() != null)
                sOffice.setPhone(officeSaveView.getPhone());
            if (officeSaveView.getActive() != null)
                sOffice.setActive(officeSaveView.getActive());

            officeRepository.save(sOffice);
        } else {
            throw new CustomOfficeException("Не возможно добавить офис принадлежащей организациии с ID: " + officeSaveView.getOrgId() + " отсутствующим в базе");
        }
    }


    //Поиск офиса по Id
    @Override
    @Transactional(readOnly = true)
    public OfficeIdViewOut findById(Long id) {
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
            throw new CustomOfficeException("С Id = " + id + " офисов не найдено! Введите существующий Id.");
        }
    }


    @Autowired
    UserRepository userRepository;

    //Удаление офиса
    @Override
    public void delete(Office office) {
        officeRepository.delete(office.getId());
    }

}

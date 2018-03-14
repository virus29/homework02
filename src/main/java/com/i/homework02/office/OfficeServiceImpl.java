package com.i.homework02.office;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Repository
@Transactional
public class OfficeServiceImpl implements OfficeService{
    @Autowired
    private OfficeRepository officeRepository;

    //Спецификация для поиска по 4 переменным
    public static Specification<Office> findByOfficeOrgidNamePhoneIsactive(Long organizationId, String name, String phone, Boolean isActive) {
        return (r, cq, cb) -> cb.and(
                cb.equal(r.join("organization").get("id"), organizationId),
                cb.equal(r.get("name"), name),
                cb.equal(r.get("phone"), phone),
                cb.equal(r.get("isActive"), isActive));
    }

    //Спецификация для поиска по 3 переменным
    public static Specification<Office> findByOfficeOrgidNamePhone(Long organizationId, String name, String phone) {
        return (r, cq, cb) -> cb.and(
                cb.equal(r.join("organization").get("id"), organizationId),
                cb.equal(r.get("officeName"), name),
                cb.equal(r.get("phone"), phone));
    }

    public static Specification<Office> findByOfficeOrgidNameIsactive(Long organizationId, String name, Boolean isActive) {
        return (r, cq, cb) -> cb.and(
                cb.equal(r.join("organization").get("id"), organizationId),
                cb.equal(r.get("officeName"), name),
                cb.equal(r.get("isActive"), isActive));
    }

    public static Specification<Office> findByOfficeOrgidPhoneIsactive(Long organizationId, String phone, Boolean isActive) {
        return (r, cq, cb) -> cb.and(
                cb.equal(r.join("organization").get("id"), organizationId),
                cb.equal(r.get("phone"), phone),
                cb.equal(r.get("isActive"), isActive));
    }

    //Спецификация для поиска по 2 переменным
    public static Specification<Office> findByOfficeOrgidName(Long organizationId, String name) {
        return (r, cq, cb) -> cb.and(
                        cb.equal(r.join("organization").get("id"), organizationId),
                        cb.equal(r.get("officeName"), name));
    }

    public static Specification<Office> findByOfficeOrgidPhone(Long organizationId, String phone) {
        return (r, cq, cb) -> cb.and(
                cb.equal(r.join("organization").get("id"), organizationId),
                cb.equal(r.get("phone"), phone));
    }

    public static Specification<Office> findByOfficeOrgidIsactive(Long organizationId, Boolean isActive) {
        return (r, cq, cb) -> cb.and(
                cb.equal(r.join("organization").get("id"), organizationId),
                cb.equal(r.get("isActive"), isActive));
    }

    //Спецификация для поиска офисов по Id организации
    public static Specification<Office> findByOfficeOrgid(Long organizationId) {
        return (r, cq, cb) -> cb.equal(r.get("organization").get("id"), organizationId);
    }

    //Поиск офисов по Id организации, названию офиса, телефона офиса и активности офиса.
    @Override
    @Transactional(readOnly = true)
    public List<Office> searchOffice(Long organizationId, String name, String phone, Boolean isActive) {
        if (name == null) {
            if (phone == null) {
                if (isActive == null) {
                    return officeRepository.
                            findAll(findByOfficeOrgid(organizationId));
                } else {
                    return officeRepository.findAll(findByOfficeOrgidIsactive(organizationId, isActive));
                }
            } else {
                if (isActive == null) {
                    return officeRepository.findAll(findByOfficeOrgidPhone(organizationId, phone));
                } else {
                    return officeRepository.findAll(findByOfficeOrgidPhoneIsactive(organizationId, phone, isActive));
                }
            }
        } else {
            if (phone == null) {
                if (isActive == null) {
                    return officeRepository.findAll(findByOfficeOrgidName(organizationId, name));
                } else {
                    return officeRepository.findAll(findByOfficeOrgidNameIsactive(organizationId, name, isActive));
                }
            } else {
                if (isActive == null) {
                    return officeRepository.findAll(findByOfficeOrgidNamePhone(organizationId, name, phone));
                } else {
                    return officeRepository.findAll(findByOfficeOrgidNamePhoneIsactive(organizationId, name, phone, isActive));
                }
            }
        }
    }

    //Изменение(обновление) параметров офиса
    @Override
    public void update(Office office) {
        if(officeRepository.exists(office.getId()))
            officeRepository.save(office);
    }

    //Добавление офиса
    @Override
    public Office save(Office office) {
        return officeRepository.save(office);
    }

    //Поиск офиса по Id
    @Override
    @Transactional(readOnly=true)
    public Office findById(Long id) {
        return officeRepository.findOne(id);
    }

    //Удаление офиса
    @Override
    public void delete(Office office) {
        officeRepository.delete(office);
    }

}

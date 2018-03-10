package com.i.homework02.office;

import com.i.homework02.organization.Organization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.*;
import java.util.List;

import static org.springframework.data.jpa.domain.Specifications.where;

@Service
@Repository
@Transactional
public class OfficeService {
    @Autowired
    private OfficeRepository officeRepository;

    //4 Переменные
    public static Specification<Office> findByOfficeOrgidNamePhoneIsactive(Long organizationId, String officeName, String officePhone, Boolean officeIsactive) {
        return (r, cq, cb) -> cb.and(
                cb.equal(r.join("organization").get("id"), organizationId),
                cb.equal(r.get("officeName"), officeName),
                cb.equal(r.get("officePhone"), officePhone),
                cb.equal(r.get("officeIsactive"), officeIsactive));
    }


    //3 Переменные
    public static Specification<Office> findByOfficeOrgidNamePhone(Long organizationId, String officeName, String officePhone) {
        return (r, cq, cb) -> cb.and(
                cb.equal(r.join("organization").get("id"), organizationId),
                cb.equal(r.get("officeName"), officeName),
                cb.equal(r.get("officePhone"), officePhone));
    }

    public static Specification<Office> findByOfficeOrgidNameIsactive(Long organizationId, String officeName, Boolean officeIsactive) {
        return (r, cq, cb) -> cb.and(
                cb.equal(r.join("organization").get("id"), organizationId),
                cb.equal(r.get("officeName"), officeName),
                cb.equal(r.get("officeIsactive"), officeIsactive));
    }

    public static Specification<Office> findByOfficeOrgidPhoneIsactive(Long organizationId, String officePhone, Boolean officeIsactive) {
        return (r, cq, cb) -> cb.and(
                cb.equal(r.join("organization").get("id"), organizationId),
                cb.equal(r.get("officePhone"), officePhone),
                cb.equal(r.get("officeIsactive"), officeIsactive));
    }

    //2 Переменные
    public static Specification<Office> findByOfficeOrgidName(Long organizationId, String officeName) {
        return (r, cq, cb) -> cb.and(
                        cb.equal(r.join("organization").get("id"), organizationId),
                        cb.equal(r.get("officeName"), officeName));
    }

    public static Specification<Office> findByOfficeOrgidPhone(Long organizationId, String officePhone) {
        return (r, cq, cb) -> cb.and(
                cb.equal(r.join("organization").get("id"), organizationId),
                cb.equal(r.get("officePhone"), officePhone));
    }

    public static Specification<Office> findByOfficeOrgidIsactive(Long organizationId, Boolean officeIsactive) {
        return (r, cq, cb) -> cb.and(
                cb.equal(r.join("organization").get("id"), organizationId),
                cb.equal(r.get("officeIsactive"), officeIsactive));
    }

    //1 Переменная
    public static Specification<Office> findByOfficeOrgid(Long organizationId) {
        return (r, cq, cb) -> cb.equal(r.get("organization").get("id"), organizationId);
//                cb.equal(r.join("organization").get("id"), organizationId);
    }



    @Transactional(readOnly = true)
    public List<Office> searchOffice(Long organizationId, String officeName, String officePhone, Boolean officeIsactive) {
        if (officeName == null) {
            if (officePhone == null) {
                if (officeIsactive == null) {
                    return officeRepository.
                            findAll(findByOfficeOrgid(organizationId));
                } else {
                    return officeRepository.findAll(findByOfficeOrgidIsactive(organizationId, officeIsactive));
                }
            } else {
                if (officeIsactive == null) {
                    return officeRepository.findAll(findByOfficeOrgidPhone(organizationId, officePhone));
                } else {
                    return officeRepository.findAll(findByOfficeOrgidPhoneIsactive(organizationId, officePhone, officeIsactive));
                }
            }
        } else {
            if (officePhone == null) {
                if (officeIsactive == null) {
                    return officeRepository.findAll(findByOfficeOrgidName(organizationId, officeName));
                } else {
                    return officeRepository.findAll(findByOfficeOrgidNameIsactive(organizationId, officeName, officeIsactive));
                }
            } else {
                if (officeIsactive == null) {
                    return officeRepository.findAll(findByOfficeOrgidNamePhone(organizationId, officeName, officePhone));
                } else {
                    return officeRepository.findAll(findByOfficeOrgidNamePhoneIsactive(organizationId, officeName, officePhone, officeIsactive));
                }
            }
        }
    }

    @Transactional(readOnly=true)
    public Office findById(Long id) {
        return officeRepository.findOne(id);
    }

}


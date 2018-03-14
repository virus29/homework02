package com.i.homework02.organization;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.*;


@Service
@Repository
@Transactional
public class OrganizationServiceImpl implements OrganisationService{

    @Autowired
    private OrganizationRepository organizationRepository;

    @PersistenceContext
    private EntityManager em;
//Обнобление по Имени
    public void updaterOrganizationName(String organizationName) {
        this.em.createQuery(this.em.getCriteriaBuilder().createCriteriaUpdate(Organization.class).set("organizationName", organizationName)).executeUpdate();
    }

    public void updaterOrganizationFullname(String organizationFullname) {
        this.em.createQuery(this.em.getCriteriaBuilder().createCriteriaUpdate(Organization.class).set("organizationFullname", organizationFullname)).executeUpdate();
    }

    public void updaterOrganizationInn(Long organizationInn) {
        this.em.createQuery(this.em.getCriteriaBuilder().createCriteriaUpdate(Organization.class).set("organizationInn", organizationInn)).executeUpdate();
    }

    public void updaterOrganizationKpp(Long organizationKpp) {
        this.em.createQuery(this.em.getCriteriaBuilder().createCriteriaUpdate(Organization.class).set("organizationKpp", organizationKpp)).executeUpdate();
    }

    public void updaterOrganizationAddress(String organizationAddress) {
        this.em.createQuery(this.em.getCriteriaBuilder().createCriteriaUpdate(Organization.class).set("organizationAddress", organizationAddress)).executeUpdate();
    }

    public void updaterOrganizationPhone(String organizationPhone) {
        this.em.createQuery(this.em.getCriteriaBuilder().createCriteriaUpdate(Organization.class).set("organizationName", organizationPhone)).executeUpdate();
    }

    public void updaterOrganizationIsactive(Boolean organizationIsactive) {
        this.em.createQuery(this.em.getCriteriaBuilder().createCriteriaUpdate(Organization.class).set("organizationIsactive", organizationIsactive)).executeUpdate();
    }

    public static Specification<Organization> findByParamNameInnIsactive(String organizationName, Long organizationInn, Boolean organizationIsactive) {
        return (r, cq, cb) -> cb.and(
                cb.equal(r.get("organizationName"), organizationName),
                cb.equal(r.get("organizationInn"), organizationInn),
                cb.equal(r.get("organizationIsactive"), organizationIsactive));
    }

    public static Specification<Organization> findByParamNameIsactive(String organizationName, Boolean organizationIsactive) {
        return (r, cq, cb) -> cb.and(
                cb.equal(r.get("organizationName"), organizationName),
                cb.equal(r.get("organizationIsactive"), organizationIsactive));
    }

    public static Specification<Organization> findByParamNameInn(String organizationName, Long organizationInn) {
        return (r, cq, cb) -> cb.and(
                cb.equal(r.get("organizationName"), organizationName),
                cb.equal(r.get("organizationInn"), organizationInn));
    }

    public static Specification<Organization> findByParamName(String organizationName) {
        return (r, cq, cb) -> cb.and(
                cb.equal(r.get("organizationName"), organizationName));
    }

    //Поиск по нескольким параметрам
    @Override
    @Transactional(readOnly = true)
    public List<Organization> search(String organizationName, Long organizationInn, Boolean organizationIsactive) {
        if (organizationInn == null) {
            if (organizationIsactive == null) {
                return organizationRepository.findAll(findByParamName(organizationName));
            } else {
                return organizationRepository.findAll(findByParamNameIsactive(organizationName, organizationIsactive));
            }
        } else {
            if (organizationIsactive == null) {
                return organizationRepository.findAll(findByParamNameInn(organizationName, organizationInn));
            } else {
                return organizationRepository.findAll(findByParamNameInnIsactive(organizationName, organizationInn, organizationIsactive));
            }
        }
    }

    //Изменение(обновление)
    @Override
    @Transactional
    public void update(Organization organization) {
        Organization updateOrganization = organizationRepository.findOne(organization.getId());
        if (organization.getName() != null)
            updaterOrganizationName(organization.getName());
        if (organization.getFullName() != null)
        updaterOrganizationFullname(organization.getFullName());
        if (organization.getInn() != null)
        updaterOrganizationInn(organization.getInn());
        if (organization.getKpp() != null)
        updaterOrganizationKpp(organization.getKpp());
        if (organization.getAddress() != null)
        updaterOrganizationAddress(organization.getAddress());
        if (organization.getPhone() != null)
        updaterOrganizationPhone(organization.getPhone());
        if (organization.getActive() != null)
        updaterOrganizationIsactive(organization.getActive());
        organizationRepository.save(updateOrganization);
    }

    //Сохранение
    @Override
    @Transactional
    public void save(Organization organization) {
         organizationRepository.save(organization);
    }

    //Поиск по Id
    @Override
    public Organization findById(Long id) {
        Organization org=organizationRepository.findOne(id);
        Long officeId=org.getOffices().get(0).getId();
        org.setOfficeId(officeId);
        return org;
    }

    //Удаление
    @Override
    @Transactional
    public void delete(Organization organization) {
        organizationRepository.delete(organization);
    }
}


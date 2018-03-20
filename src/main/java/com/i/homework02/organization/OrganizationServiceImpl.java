package com.i.homework02.organization;

import com.i.homework02.exeption.CustomOrganizationException;
import com.i.homework02.office.OfficeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import view.OrgIdViewOut;
import view.OrgListViewIn;
import view.OrgListViewOut;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.*;


@Service
@Repository
@Transactional
public class OrganizationServiceImpl implements OrganisationService {

    @Autowired
    private OrganizationRepository organizationRepository;

    //поисковик(старый вариант)

    /**
     * @PersistenceContext private EntityManager em;
     * public static Specification<Organization> findByParamNameInnIsactive(String name, String inn, Boolean isActive) {
     * return (r, cq, cb) -> cb.and(
     * cb.equal(r.get("name"), name),
     * cb.equal(r.get("inn"), inn),
     * cb.equal(r.get("isActive"), isActive));
     * }
     * public static Specification<Organization> findByParamNameIsactive(String name, Boolean isActive) {
     * return (r, cq, cb) -> cb.and(
     * cb.equal(r.get("name"), name),
     * cb.equal(r.get("isActive"), isActive));
     * }
     * public static Specification<Organization> findByParamNameInn(String name, String inn) {
     * return (r, cq, cb) -> cb.and(
     * cb.equal(r.get("name"), name),
     * cb.equal(r.get("inn"), inn));
     * }
     * public static Specification<Organization> findByParamName(String name) {
     * return (r, cq, cb) -> cb.and(
     * cb.equal(r.get("name"), name));
     * }
     * //Поиск по нескольким параметрам
     * @Override public List<Organization> search(String name, String inn, Boolean isActive) {
     * if (inn == null) {
     * if (isActive == null) {
     * return organizationRepository.findAll(findByParamName(name));
     * } else {
     * return organizationRepository.findAll(findByParamNameIsactive(name, isActive));
     * }
     * } else {
     * if (isActive == null) {
     * return organizationRepository.findAll(findByParamNameInn(name, inn));
     * } else {
     * return organizationRepository.findAll(findByParamNameInnIsactive(name, inn, isActive));
     * }
     * }
     * }
     **/


    //Поиск офисов по нескольким параметрам.
    @Override
    public List<OrgListViewOut> search(OrgListViewIn orgListViewIn) {
        Specification<Organization> findOrganizationByParam = new Specification<Organization>() {
            @Override
            public Predicate toPredicate(Root<Organization> r, CriteriaQuery<?> cq, CriteriaBuilder cb) {
                List<Predicate> prs = new ArrayList<>();
                if (orgListViewIn.getName() != null)
                    prs.add(cb.equal(r.get("name"), orgListViewIn.getName()));
                if (orgListViewIn.getInn() != null)
                    prs.add(cb.like(r.get("inn"), "%" + orgListViewIn.getInn() + "%"));
                if (orgListViewIn.getActive() != null)
                    prs.add(cb.like(r.get("isActive"), "%" + orgListViewIn.getActive() + "%"));
                return cb.and(prs.toArray(new Predicate[prs.size()]));
            }
        };
        if (organizationRepository.findAll(findOrganizationByParam).isEmpty()) {
            throw new CustomOrganizationException("По заданным параметрам ничего не найдено");
        }
        List<Organization> ors = organizationRepository.findAll(findOrganizationByParam);
        List<OrgListViewOut> orlvo = new ArrayList<>();
        for (Organization organization : ors
                ) {
            OrgListViewOut orgListViewOut = new OrgListViewOut();
            orgListViewOut.setId(organization.getId());
            orgListViewOut.setName(organization.getName());
            orgListViewOut.setActive(organization.getActive());
            orlvo.add(orgListViewOut);
        }
        return orlvo;
    }

    //Изменение(обновление)
    @Override
    public void update(Organization organization) {
        if (organization.getId() == null) {
            throw new CustomOrganizationException("Введите Id организации для изменения её параметров");
        }
        if (organizationRepository.exists(organization.getId())) {
            Organization updateOrganization = organizationRepository.findOne(organization.getId());
            if (organization.getName() != null)
                updateOrganization.setName(organization.getName());
            if (organization.getFullName() != null)
                updateOrganization.setFullName(organization.getFullName());
            if (organization.getInn() != null)
                updateOrganization.setInn(organization.getInn());
            if (organization.getKpp() != null)
                updateOrganization.setKpp(organization.getKpp());
            if (organization.getAddress() != null)
                updateOrganization.setAddress(organization.getAddress());
            if (organization.getPhone() != null)
                updateOrganization.setPhone(organization.getPhone());
            if (organization.getActive() != null)
                updateOrganization.setActive(organization.getActive());
            organizationRepository.save(updateOrganization);
        } else {
            throw new CustomOrganizationException("Организация с Id: " + organization.getId() + " не найдена!");
        }
    }

    //Сохранение
    @Override
    @Transactional
    public void save(Organization organization) {
        if (organization.getName() == null &
                organization.getFullName() == null &
                organization.getInn() == null &
                organization.getKpp() == null &
                organization.getAddress() == null &
                organization.getPhone() == null &
                organization.getActive() == null
                ) {
            throw new CustomOrganizationException("Поля не заполнены! Для сохранения, необходимо заполнить, хотя бы, одно поле!");
        }
        organizationRepository.save(organization);
    }

    //Поиск по Id
    @Override
    public OrgIdViewOut findById(Long id) {
        if (organizationRepository.exists(id)) {
            Organization organization = organizationRepository.findOne(id);
            OrgIdViewOut orgIdViewOut = new OrgIdViewOut();
            orgIdViewOut.setId(organization.getId());
            orgIdViewOut.setName(organization.getName());
            orgIdViewOut.setFullName(organization.getFullName());
            orgIdViewOut.setInn(organization.getInn());
            orgIdViewOut.setKpp(organization.getKpp());
            orgIdViewOut.setAddress(organization.getAddress());
            orgIdViewOut.setPhone(organization.getPhone());
            orgIdViewOut.setActive(organization.getActive());
            return orgIdViewOut;
        } else {
            throw new CustomOrganizationException("С данным Id, организация не найдена!");
        }
    }

    //Удаление
    @Override
    public void delete(Organization organization) {
        if (organization.getId() == null) {
            throw new CustomOrganizationException("Для удаления, Id организации не должно быть пустым");
        }
        if (organizationRepository.exists(organization.getId())){
            organizationRepository.delete(organization.getId());
        } else {
            throw new CustomOrganizationException("С данным Id, организация не найдена!");
        }
    }
}


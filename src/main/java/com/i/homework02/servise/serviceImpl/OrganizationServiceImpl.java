package com.i.homework02.servise.serviceImpl;

import com.i.homework02.entity.Organization;
import com.i.homework02.exeption.CustomOrganizationException;
import com.i.homework02.repository.OrganizationRepository;
import com.i.homework02.servise.OrganisationService;
import com.i.homework02.view.OrgViewIn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.i.homework02.view.OrgIdViewOut;
import com.i.homework02.view.OrgListViewIn;
import com.i.homework02.view.OrgListViewOut;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;


@Service
@Repository
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


    /**
     * Поиск организации по нескольким параметрам
     * @param orgListViewIn - объект содержащий параметры для поиска
     * @return список организаций подходящие критериям поиска
     */
    @Override
    @Transactional(readOnly = true)
    public List<OrgListViewOut> search(OrgListViewIn orgListViewIn) throws CustomOrganizationException {
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
        List<OrgListViewOut> result = new ArrayList<>();
        for (Organization organization : ors
                ) {
            OrgListViewOut orgListViewOut = new OrgListViewOut();
            orgListViewOut.setId(organization.getId());
            orgListViewOut.setName(organization.getName());
            orgListViewOut.setActive(organization.getActive());
            result.add(orgListViewOut);
        }
        return result;
    }

    /**
     * Изменение(обновление) организации
     * @param orgViewIn - объект содержащий параметры для обновления
     */
    @Transactional
    @Override
    public void update(OrgViewIn orgViewIn) throws CustomOrganizationException {
        if (orgViewIn.getId() == null) {
            throw new CustomOrganizationException("Введите Id организации для изменения её параметров");
        }
        if (organizationRepository.exists(orgViewIn.getId())) {
            Organization updateOrganization = organizationRepository.findOne(orgViewIn.getId());
            if (orgViewIn.getName() != null)
                updateOrganization.setName(orgViewIn.getName());
            if (orgViewIn.getFullName() != null)
                updateOrganization.setFullName(orgViewIn.getFullName());
            if (orgViewIn.getInn() != null)
                updateOrganization.setInn(orgViewIn.getInn());
            if (orgViewIn.getKpp() != null)
                updateOrganization.setKpp(orgViewIn.getKpp());
            if (orgViewIn.getAddress() != null)
                updateOrganization.setAddress(orgViewIn.getAddress());
            if (orgViewIn.getPhone() != null)
                updateOrganization.setPhone(orgViewIn.getPhone());
            if (orgViewIn.getActive() != null)
                updateOrganization.setActive(orgViewIn.getActive());
            organizationRepository.save(updateOrganization);
        } else {
            throw new CustomOrganizationException("Организация с Id: " + orgViewIn.getId() + " не найдена!");
        }
    }

    /**
     * Сохранение организации
     * @param orgViewIn - объект содержащий параметры для сохранения
     */
    @Override
    @Transactional
    public void save(OrgViewIn orgViewIn) throws CustomOrganizationException {
        if (orgViewIn.getName() == null &
                orgViewIn.getFullName() == null &
                orgViewIn.getInn() == null &
                orgViewIn.getKpp() == null &
                orgViewIn.getAddress() == null &
                orgViewIn.getPhone() == null &
                orgViewIn.getActive() == null
                ) {
            throw new CustomOrganizationException("Поля не заполнены! Для сохранения, необходимо заполнить, хотя бы, одно поле!");
        }
        Organization organization = new Organization();
        organization.setName(orgViewIn.getName());
        organization.setFullName(orgViewIn.getFullName());
        organization.setInn(orgViewIn.getInn());
        organization.setKpp(orgViewIn.getKpp());
        organization.setAddress(orgViewIn.getAddress());
        organization.setPhone(orgViewIn.getPhone());
        organization.setActive(orgViewIn.getActive());
        organizationRepository.save(organization);
    }

    /**
     * Поиск по Id организации
     * @param id - Id организации
     * @return - Организация найденная по id
     */
    @Override
    @Transactional(readOnly = true)
    public OrgIdViewOut findById(Long id) throws CustomOrganizationException {
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

    /**
     * Удаление организации
     * @param orgViewIn - объект содержащий id организации
     */
    @Override
    @Transactional
    public void delete(OrgViewIn orgViewIn) throws CustomOrganizationException {
        if (orgViewIn.getId() == null) {
            throw new CustomOrganizationException("Для удаления, Id организации не должно быть пустым");
        }
        if (organizationRepository.exists(orgViewIn.getId())){
            organizationRepository.delete(orgViewIn.getId());
        } else {
            throw new CustomOrganizationException("С данным Id, организация не найдена!");
        }
    }
}


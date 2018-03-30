package com.i.homework02.service.impl;

import com.i.homework02.entity.Organization;
import com.i.homework02.exeption.CustomOrganizationException;
import com.i.homework02.repository.OrganizationRepository;
import com.i.homework02.service.OrganisationService;
import com.i.homework02.view.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.text.ParseException;
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
     * @param organization - объект содержащий параметры для поиска
     * @return список организаций подходящие критериям поиска
     */
    @Override
    @Transactional(readOnly = true)
    public List<OrgListViewResponse> search(Organization organization) throws CustomOrganizationException {
        Specification<Organization> findOrganizationByParam = new Specification<Organization>() {
            @Override
            public Predicate toPredicate(Root<Organization> r, CriteriaQuery<?> cq, CriteriaBuilder cb) {
                List<Predicate> prs = new ArrayList<>();
                if (organization.getName() != null)
                    prs.add(cb.equal(r.get("name"), organization.getName()));
                if (organization.getInn() != null)
                    prs.add(cb.like(r.get("inn"), "%" + organization.getInn() + "%"));
                if (organization.getActive() != null)
                    prs.add(cb.equal(r.get("isActive"), organization.getActive()));
                return cb.and(prs.toArray(new Predicate[prs.size()]));
            }
        };
        if (organizationRepository.findAll(findOrganizationByParam).isEmpty()) {
            throw new CustomOrganizationException("По заданным параметрам ничего не найдено");
        }
        List<Organization> ors = organizationRepository.findAll(findOrganizationByParam);
        List<OrgListViewResponse> result = new ArrayList<>();
        for (Organization organizationN : ors
                ) {
            OrgListViewResponse orgListViewResponse = new OrgListViewResponse();
            orgListViewResponse.setId(organizationN.getId());
            orgListViewResponse.setName(organizationN.getName());
            orgListViewResponse.setActive(organizationN.getActive());
            result.add(orgListViewResponse);
        }
        return result;
    }

    /**
     * Изменение(обновление) организации
     * @param organization - объект содержащий параметры для обновления
     */
    @Transactional
    @Override
    public void update(Organization organization) throws CustomOrganizationException {
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

    /**
     * Сохранение организации
     * @param organization - объект содержащий параметры для сохранения
     */
    @Override
    @Transactional
    public void save(Organization organization) throws CustomOrganizationException {
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

    /**
     * Поиск по Id организации
     * @param id - Id организации
     * @return - Организация найденная по id
     */
    @Override
    @Transactional(readOnly = true)
    public OrgViewResponse findById(Long id) throws CustomOrganizationException {
        if (organizationRepository.exists(id)) {
            Organization organization = organizationRepository.findOne(id);
            OrgViewResponse orgViewResponse = new OrgViewResponse();
            orgViewResponse.setId(organization.getId());
            orgViewResponse.setName(organization.getName());
            orgViewResponse.setFullName(organization.getFullName());
            orgViewResponse.setInn(organization.getInn());
            orgViewResponse.setKpp(organization.getKpp());
            orgViewResponse.setAddress(organization.getAddress());
            orgViewResponse.setPhone(organization.getPhone());
            orgViewResponse.setActive(organization.getActive());
            return orgViewResponse;
        } else {
            throw new CustomOrganizationException("С данным Id, организация не найдена!");
        }
    }

    /**
     * Удаление организации
     * @param organization - объект содержащий id организации
     */
    @Override
    @Transactional
    public void delete(Organization organization) throws CustomOrganizationException {
        if (organization.getId() == null) {
            throw new CustomOrganizationException("Для удаления, Id организации не должно быть пустым");
        }
        if (organizationRepository.exists(organization.getId())){
            organizationRepository.delete(organization.getId());
        } else {
            throw new CustomOrganizationException("С данным Id, организация не найдена!");
        }
    }

    @Autowired
    private ModelMapper modelMapper;

    public Organization convertToEntity(OrgListViewRequest orgListViewRequest) throws ParseException {
        Organization organization=modelMapper.map(orgListViewRequest,Organization.class);
        return organization;
    }

    public Organization convertToEntity(OrgViewRequest orgViewRequest) throws ParseException {
        Organization organization=modelMapper.map(orgViewRequest,Organization.class);
        return organization;
    }


}


package com.i.homework02.service.impl;

import com.i.homework02.entity.Organization;
import com.i.homework02.exeption.CustomOrganizationException;
import com.i.homework02.repository.OrganizationRepository;
import com.i.homework02.service.OrganizationService;
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
public class OrganizationServiceImpl implements OrganizationService {

    @Autowired
    private OrganizationRepository organizationRepository;

    /**
     * Поиск организации по нескольким параметрам
     *
     * @param orgListViewRequest - объект содержащий параметры для поиска
     * @return список организаций подходящие критериям поиска
     */
    @Override
    @Transactional(readOnly = true)
    public List<OrgListViewResponse> search(OrgListViewRequest orgListViewRequest) throws CustomOrganizationException, ParseException {
        Organization organization = convertToEntity(orgListViewRequest);
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
     *
     * @param orgViewRequest - объект содержащий параметры для обновления
     */
    @Transactional
    @Override
    public void update(OrgViewRequest orgViewRequest) throws CustomOrganizationException, ParseException {
        Organization organization = convertToEntity(orgViewRequest);
        if (organizationRepository.exists(organization.getId())) {
            Organization updateOrganization = organizationRepository.findOne(organization.getId());
            updateOrganization.setName(organization.getName());
            updateOrganization.setFullName(organization.getFullName());
            updateOrganization.setInn(organization.getInn());
            updateOrganization.setKpp(organization.getKpp());
            updateOrganization.setAddress(organization.getAddress());
            updateOrganization.setPhone(organization.getPhone());
            updateOrganization.setActive(organization.getActive());
            organizationRepository.save(updateOrganization);
        } else {
            throw new CustomOrganizationException("Организация с Id: " + organization.getId() + " не найдена!");
        }
    }

    /**
     * Сохранение организации
     *
     * @param orgSaveViewRequest - объект содержащий параметры для сохранения
     */
    @Override
    @Transactional
    public void save(OrgSaveViewRequest orgSaveViewRequest) throws CustomOrganizationException, ParseException {
        Organization organization = convertToEntity(orgSaveViewRequest);
        if (organizationRepository.findOrganizationByName(organization.getName()) == null) {
            organizationRepository.save(organization);
        } else {
            throw new CustomOrganizationException("Организация с name: " + organization.getName() + " уже существует введите оригинальное имя");
        }
    }

    /**
     * Поиск по Id организации
     *
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
     *
     * @param orgViewRequest - объект содержащий id организации
     */
    @Override
    @Transactional
    public void delete(OrgViewRequest orgViewRequest) throws CustomOrganizationException, ParseException {
        Organization organization = convertToEntity(orgViewRequest);
        if (organizationRepository.exists(organization.getId())) {
            organizationRepository.delete(organization.getId());
        } else {
            throw new CustomOrganizationException("С данным Id, организация не найдена!");
        }
    }

    @Autowired
    private ModelMapper modelMapper;

    public Organization convertToEntity(OrgSaveViewRequest orgSaveViewRequest) throws ParseException {
        Organization organization = modelMapper.map(orgSaveViewRequest, Organization.class);
        return organization;
    }

    public Organization convertToEntity(OrgListViewRequest orgListViewRequest) throws ParseException {
        Organization organization = modelMapper.map(orgListViewRequest, Organization.class);
        return organization;
    }

    public Organization convertToEntity(OrgViewRequest orgViewRequest) throws ParseException {
        Organization organization = modelMapper.map(orgViewRequest, Organization.class);
        return organization;
    }


}


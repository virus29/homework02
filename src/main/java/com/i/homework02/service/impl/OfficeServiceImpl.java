package com.i.homework02.service.impl;

import com.i.homework02.entity.Office;
import com.i.homework02.entity.Organization;
import com.i.homework02.exeption.CustomOfficeException;
import com.i.homework02.repository.OfficeRepository;
import com.i.homework02.repository.OrganizationRepository;
import com.i.homework02.repository.UserRepository;
import com.i.homework02.service.OfficeService;
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
public class OfficeServiceImpl implements OfficeService {
    @Autowired
    private OfficeRepository officeRepository;

    /**
     * Поиск офиса(ов) по нескольким параметрам
     *
     * @param officeListViewRequest - объект с параметрами поиска
     * @return Список офисов полученый из параметров запроса
     */
    @Override
    @Transactional(readOnly = true)
    public List<OfficeListViewResponse> searchOffice(OfficeListViewRequest officeListViewRequest) throws CustomOfficeException, ParseException {
        Office office = convertToEntity(officeListViewRequest);
        Specification<Office> findOfficeByParam = new Specification<Office>() {
            @Override
            public Predicate toPredicate(Root<Office> r, CriteriaQuery<?> cq, CriteriaBuilder cb) {
                List<Predicate> prs = new ArrayList<>();
                if (office.getOrganization().getId() != null)
                    prs.add(cb.equal(r.get("organization").get("id"), office.getOrganization().getId()));
                if (office.getName() != null)
                    prs.add(cb.like(r.get("name"), "%" + office.getName() + "%"));
                if (office.getPhone() != null)
                    prs.add(cb.equal(r.get("phone"), office.getPhone()));
                if (office.getActive() != null)
                    prs.add(cb.equal(r.get("isActive"), office.getActive()));
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
        List<OfficeListViewResponse> result = new ArrayList<>();
        for (Office officeN : os
                ) {
            OfficeListViewResponse officeListViewResponse = new OfficeListViewResponse();
            officeListViewResponse.setId(officeN.getId());
            officeListViewResponse.setName(officeN.getName());
            officeListViewResponse.setActive(officeN.getActive());
            result.add(officeListViewResponse);
        }
        return result;
    }

    /**
     * Изменение(обновление) параметров офиса
     *
     * @param officeUpdateViewRequest - парметры офиса переданные для удаления
     */
    @Override
    @Transactional
    public void update(OfficeUpdateViewRequest officeUpdateViewRequest) throws CustomOfficeException, ParseException {
        Office office = convertToEntity(officeUpdateViewRequest);
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


    /**
     * Сохранение офиса
     *
     * @param officeSaveViewRequest - объект с параметрами для сохранения
     */
    @Override
    @Transactional
    public void save(OfficeSaveViewRequest officeSaveViewRequest) throws CustomOfficeException, ParseException {
        Office office = convertToEntity(officeSaveViewRequest);
        if (organizationRepository.exists(office.getOrganization().getId())) {
            if (office.getName() == null) {
                throw new CustomOfficeException("Поле name не заполнено! Для сохранения, необходимо заполнить, Название офиса, помимо Id организации!");
            }
            officeRepository.save(office);
        } else {
            throw new CustomOfficeException("Не возможно добавить офис, принадлежащий организациии, с ID: " + office.getOrganization().getId() + ", отсутствующим в базе");
        }
    }


    /**
     * Поиск офиса по Id
     *
     * @param id - id офиса
     * @return - офис найденный по id
     */
    @Override
    @Transactional(readOnly = true)
    public OfficeViewResponse findById(Long id) throws CustomOfficeException {
        if (officeRepository.findOne(id) != null) {
            Office of = officeRepository.findOne(id);
            OfficeViewResponse officeViewResponse = new OfficeViewResponse();
            officeViewResponse.setId(of.getId());
            officeViewResponse.setName(of.getName());
            officeViewResponse.setAddress(of.getAddress());
            officeViewResponse.setPhone(of.getPhone());
            officeViewResponse.setActive(of.getActive());
            return officeViewResponse;
        } else {
            throw new CustomOfficeException("С Id = " + id + ", офисов не найдено! Введите существующий Id.");
        }
    }


    @Autowired
    UserRepository userRepository;

    /**
     * Удаление офиса
     *
     * @param officeDeleteViewRequest объект с параметром id офиса
     */
    @Override
    @Transactional
    public void delete(OfficeDeleteViewRequest officeDeleteViewRequest) throws CustomOfficeException, ParseException {
        Office office = convertToEntity(officeDeleteViewRequest);
        if (officeRepository.findOne(office.getId()) != null) {
            officeRepository.delete(office.getId());
        } else {
            throw new CustomOfficeException("С данным Id, офис не найден!");
        }
    }

    @Autowired
    private ModelMapper modelMapper;

    public Office convertToEntity(OfficeDeleteViewRequest officeDeleteViewRequest) throws ParseException {
        Office office = modelMapper.map(officeDeleteViewRequest, Office.class);
        return office;
    }

    public Office convertToEntity(OfficeListViewRequest officeListViewRequest) throws ParseException {
        Office office = modelMapper.map(officeListViewRequest, Office.class);
        Organization organization = new Organization();
        organization.setId(officeListViewRequest.getOrgId());
        office.setOrganization(organization);
        return office;
    }

    public Office convertToEntity(OfficeSaveViewRequest officeSaveViewRequest) throws ParseException {
        Office office = modelMapper.map(officeSaveViewRequest, Office.class);
        Organization organization = new Organization();
        organization.setId(officeSaveViewRequest.getOrgId());
        office.setOrganization(organization);
        return office;
    }

    public Office convertToEntity(OfficeUpdateViewRequest officeUpdateViewRequest) throws ParseException {
        Office office = modelMapper.map(officeUpdateViewRequest, Office.class);
        return office;
    }


}

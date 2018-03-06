package com.i.homework02.organization;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Repository
@Transactional
public class OrganizationService {

    @Autowired
    private OrganizationRepository organizationRepository;

    @Transactional(readOnly = true)
    public List<Organization> search(String organizationName, int organizationInn, boolean organizationIsactive) {
        return organizationRepository.findBySearchParams(organizationName, organizationInn, organizationIsactive);
    }

    public Organization save(Organization organization) {
        return organizationRepository.save(organization);
    }

    @Transactional(readOnly=true)
    public Organization findById(Long id) {
        return organizationRepository.findOne(id);
    }

}
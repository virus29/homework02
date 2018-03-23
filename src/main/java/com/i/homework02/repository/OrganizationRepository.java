package com.i.homework02.repository;

import com.i.homework02.entity.Organization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface OrganizationRepository extends JpaRepository<Organization, Long>, JpaSpecificationExecutor<Organization> {
    Organization findOrganizationByName(String name);
    Organization findOrganizationByInn(String name);
}
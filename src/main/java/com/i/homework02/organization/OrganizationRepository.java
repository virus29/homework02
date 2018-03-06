package com.i.homework02.organization;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrganizationRepository extends JpaRepository<Organization, Long> {
    @Query("SELECT o.id, o.organizationName, o.organizationIsactive FROM Organization o WHERE " +
            "o.organizationName like concat('%', :organizationName, '%') AND " +
            "o.organizationInn like concat('%', :organizationInn, '%') AND " +
            "o.organizationIsactive = :organizationIsactive")
    List<Organization> findBySearchParams(
            @Param("organizationName") String organizationName,
            @Param("organizationInn") String organizationInn,
            @Param("organizationIsactive") Boolean organizationIsactive
    );
}

//4. api/organization/list
//        In (фильтр):
//        {
//        “name”:”organizationName”, //обязательный параметр
//        “inn”:”organizationInn”,
//        “isActive”:”organizationIsactive”
//        }
//        Out:
//        [
//        {
//        “id”:””,
//        “name”:””,
//        “isActive”:”true”
//        },
//        ...
//        ]
//
//        5. api/organization/{id:.+}
//        method:GET
//        Out:
//        {
//        “id”:””,
//        “name”:””,
//        “fullName”:””,
//        “inn”:””,
//        “kpp”:””,
//        “address”:””,
//        “phone”,””,
//        “isActive”:”true”
//        }
//
//        6. api/organization/update
//        In:
//        {
//        “id”:””,
//        “name”:””,
//        “fullName”:””,
//        “inn”:””,
//        “kpp”:””,
//        “address”:””,
//        “phone”,””,
//        “isActive”:”true”
//        }
//        Out: {“result”:”success”}
//
//        6. api/organization/save
//        In:
//        {
//        “name”:””,
//        “fullName”:””,
//        “inn”:””,
//        “kpp”:””,
//        “address”:””,
//        “phone”,””,
//        “isActive”:”true”
//        }
//        Out: {“result”:”success”}
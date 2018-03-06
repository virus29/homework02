package com.i.homework02.office;

import org.springframework.data.repository.CrudRepository;

public interface OfficeRepository extends CrudRepository<Office, Long> {
//    @Query("SELECT u FROM User u WHERE " +
//            "u.officeId AND " +
//            "u.user_firstname AND " +
//            "u.user_lastName AND " +
//            "u.user_middleName AND " +
//            "u.position AND " +
//            "u.docCode AND " +
//            "u.citizenshipCode")
//    List<User> findBySearchParams(
//            @Param("officeId") int officeId,
//            @Param("user_firstname") String user_firstname,
//            @Param("user_lastName") String user_lastName,
//            @Param("user_middleName") String user_middleName,
//            @Param("position") String position,
//            @Param("docCode") int docCode,
//            @Param("citizenshipCode") int citizenshipCode
//    );
}

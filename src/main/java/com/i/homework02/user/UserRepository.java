package com.i.homework02.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
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

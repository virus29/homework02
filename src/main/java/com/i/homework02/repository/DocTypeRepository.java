package com.i.homework02.repository;

import com.i.homework02.entity.DocType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocTypeRepository extends JpaRepository<DocType, Long> {
    DocType findDocTypeByCode(String code);
}

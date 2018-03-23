package com.i.homework02.controller;

import com.i.homework02.repository.DocTypeRepository;
import com.i.homework02.entity.DocType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class DocTypeController {
    @Autowired
    DocTypeRepository docTypeRepository;

    @PostMapping("/docs")
    List<DocType> documents() {
            return this.docTypeRepository.findAll();
        }
}
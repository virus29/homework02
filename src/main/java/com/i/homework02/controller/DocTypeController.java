package com.i.homework02.controller;

import com.i.homework02.repository.DocTypeRepository;
import com.i.homework02.entity.DocType;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Api(value = "/api/docs", description = "Операции над списком типов и кодов документов")
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)

public class DocTypeController {

    @Autowired
    DocTypeRepository docTypeRepository;

    @GetMapping("/docs")
    @ApiOperation(value = "Получение списка типов и кодов документов")
    List<DocType> documents() {
            return this.docTypeRepository.findAll();
        }
}
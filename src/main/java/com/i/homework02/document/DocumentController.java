package com.i.homework02.document;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DocumentController {
    @Autowired
    DocumentRepository documentRepository;

    @RequestMapping("/api/docs")
    List<Document> documents() {
            return this.documentRepository.findAll();
        }
}


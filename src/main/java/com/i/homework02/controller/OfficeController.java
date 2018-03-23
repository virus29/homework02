package com.i.homework02.controller;

import com.i.homework02.entity.Office;
import com.i.homework02.exeption.CustomOfficeException;
import com.i.homework02.servise.serviceImpl.OfficeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.i.homework02.view.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/api/office", produces = MediaType.APPLICATION_JSON_VALUE)
public class OfficeController {

    @Autowired
    private OfficeServiceImpl officeServiceImpl;

    @PostMapping(value = "/list")
    public
    ResponseEntity searchOffice(@RequestBody @Valid OfficeListViewIn officeListViewIn) throws CustomOfficeException {
        List<OfficeListViewOut> listOffices = officeServiceImpl.searchOffice(officeListViewIn);
        DataView<List<OfficeListViewOut>> dataView =new DataView<>(listOffices);
        return new ResponseEntity<>(dataView, HttpStatus.FOUND);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity findOfficeById(@PathVariable Long id) throws CustomOfficeException {
        OfficeIdViewOut officeIdViewOut = officeServiceImpl.findById(id);
        DataView<OfficeIdViewOut> dataView =new DataView<>(officeIdViewOut);
        return new ResponseEntity<>(dataView, HttpStatus.FOUND);
    }

    @PostMapping(value = "/update")
    public
    ResponseEntity officeUpdate (@RequestBody @Valid OfficeViewIn officeViewIn) throws CustomOfficeException {
        officeServiceImpl.update(officeViewIn);
        return new ResponseEntity<>(new PositiveResponseView(), HttpStatus.OK);
    }

    @PostMapping(value = "/save")
    public
    ResponseEntity officeSave (@RequestBody OfficeSaveViewIn officeSaveViewIn) throws CustomOfficeException {
        officeServiceImpl.save(officeSaveViewIn);
        return new ResponseEntity<>(new PositiveResponseView(), HttpStatus.CREATED);
    }

    @PostMapping(value = "/delete")
    public
    ResponseEntity officeDelete (@RequestBody OfficeViewIn officeViewIn) throws CustomOfficeException {
        officeServiceImpl.delete(officeViewIn);
        return new ResponseEntity<>(new PositiveResponseView(), HttpStatus.OK);
    }
}


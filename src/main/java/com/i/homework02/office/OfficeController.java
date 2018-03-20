package com.i.homework02.office;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import view.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/api/office", produces = MediaType.APPLICATION_JSON_VALUE)
public class OfficeController {

    @Autowired
    private OfficeServiceImpl officeServiceImpl;

    @PostMapping(value = "/list")
    public
    ResponseEntity searchOffice(@RequestBody @Valid OfficeListViewIn officeListViewIn){
        List<OfficeListViewOut> listOffices = officeServiceImpl.searchOffice(officeListViewIn);
        DataView<List<OfficeListViewOut>> dataView =new DataView<>(listOffices);
        return new ResponseEntity<>(dataView, HttpStatus.FOUND);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity findOfficeById(@PathVariable Long id) {
        OfficeIdViewOut officeIdViewOut = officeServiceImpl.findById(id);
        DataView<OfficeIdViewOut> dataView =new DataView<>(officeIdViewOut);
        return new ResponseEntity<>(dataView, HttpStatus.FOUND);
    }

    @PostMapping(value = "/update")
    public
    ResponseEntity officeUpdate (@RequestBody @Valid Office office) {
        officeServiceImpl.update(office);
        return new ResponseEntity<>(new PositiveResponseView(), HttpStatus.OK);
    }

    @PostMapping(value = "/save")
    public
    ResponseEntity officeSave (@RequestBody OfficeSaveView officeSaveView) {
        officeServiceImpl.save(officeSaveView);
        return new ResponseEntity<>(new PositiveResponseView(), HttpStatus.CREATED);
    }

    @PostMapping(value = "/delete")
    public
    ResponseEntity officeDelete (@RequestBody Office office) {
        officeServiceImpl.delete(office);
        return new ResponseEntity<>(new PositiveResponseView(), HttpStatus.OK);
    }
}


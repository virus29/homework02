package com.i.homework02.controller;

import com.i.homework02.entity.Office;
import com.i.homework02.exeption.CustomOfficeException;
import com.i.homework02.service.impl.OfficeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.i.homework02.view.*;

import javax.validation.Valid;
import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping(value = "/api/office", produces = MediaType.APPLICATION_JSON_VALUE)
public class OfficeController {

    @Autowired
    private OfficeServiceImpl officeServiceImpl;

    /**
     * Поиск офиса(ов) по нескольким параметрам
     * @param officeListViewRequest - объект с параметрами поиска
     * @return Список офисов полученый из параметров запроса
     */
    @PostMapping(value = "/list")
    public
    ResponseEntity searchOffice(@RequestBody @Valid OfficeListViewRequest officeListViewRequest) throws CustomOfficeException, ParseException {
        List<OfficeListViewResponse> listOffices = officeServiceImpl.searchOffice(officeListViewRequest);
        DataView<List<OfficeListViewResponse>> dataView =new DataView<>(listOffices);
        return new ResponseEntity<>(dataView, HttpStatus.FOUND);
    }

    /**
     * Поиск офиса по Id
     * @param id - id офиса
     * @return - офис найденный по id
     */
    @GetMapping(path = "/{id}")
    public ResponseEntity findOfficeById(@PathVariable Long id) throws CustomOfficeException {
        OfficeViewResponse officeViewResponse = officeServiceImpl.findById(id);
        DataView<OfficeViewResponse> dataView =new DataView<>(officeViewResponse);
        return new ResponseEntity<>(dataView, HttpStatus.FOUND);
    }

    /**
     * Изменение(обновление) параметров офиса
     * @param officeUpdateViewRequest - парметры офиса переданные для удаления
     */
    @PostMapping(value = "/update")
    public
    ResponseEntity officeUpdate (@RequestBody @Valid OfficeUpdateViewRequest officeUpdateViewRequest) throws CustomOfficeException, ParseException {
        officeServiceImpl.update(officeUpdateViewRequest);
        return new ResponseEntity<>(new PositiveResponseView(), HttpStatus.OK);
    }

    /**
     * Сохранение офиса
     * @param officeSaveViewRequest - объект с параметрами для сохранения
     */
    @PostMapping(value = "/save")
    public
    ResponseEntity officeSave (@RequestBody OfficeSaveViewRequest officeSaveViewRequest) throws CustomOfficeException, ParseException {
        officeServiceImpl.save(officeSaveViewRequest);
        return new ResponseEntity<>(new PositiveResponseView(), HttpStatus.CREATED);
    }

    /**
     * Удаление офиса
     * @param officeDeleteViewRequest объект с параметром id офиса
     */
    @PostMapping(value = "/delete")
    public
    ResponseEntity officeDelete (@RequestBody @Valid OfficeDeleteViewRequest officeDeleteViewRequest) throws CustomOfficeException, ParseException {
        officeServiceImpl.delete(officeDeleteViewRequest);
        return new ResponseEntity<>(new PositiveResponseView(), HttpStatus.OK);
    }
}


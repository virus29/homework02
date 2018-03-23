package com.i.homework02.servise;

import com.i.homework02.entity.Office;
import com.i.homework02.exeption.CustomOfficeException;
import com.i.homework02.view.*;

import java.util.List;

public interface OfficeService {


    /**
     * Поиск по нескольким параметрам
     * @param officeListViewIn
     * @return
     */
    public List<OfficeListViewOut> searchOffice(OfficeListViewIn officeListViewIn) throws CustomOfficeException;

    /**
     * Изменение(обновление)
     * @param officeViewIn
     */
    public void update(OfficeViewIn officeViewIn) throws CustomOfficeException;

    /**
     * Сохранение
     * @param officeSaveViewIn
     */
    public void save(OfficeSaveViewIn officeSaveViewIn) throws CustomOfficeException;

    /**
     * Поиск по Id
     * @param id
     * @return
     */
    public OfficeIdViewOut findById(Long id) throws CustomOfficeException;

    /**
     * Удаление офиса
     * @param officeViewIn
     */
    public void delete(OfficeViewIn officeViewIn) throws CustomOfficeException;
}

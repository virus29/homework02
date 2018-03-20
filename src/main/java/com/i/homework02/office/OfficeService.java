package com.i.homework02.office;

import view.OfficeIdViewOut;
import view.OfficeListViewIn;
import view.OfficeListViewOut;
import view.OfficeSaveView;

import java.util.List;

public interface OfficeService {

    //Поиск по нескольким параметрам
    public List<OfficeListViewOut> searchOffice(OfficeListViewIn officeListViewIn);

    //Изменение(обновление)
    public void update(Office office);

    //Сохранение
    public void save(OfficeSaveView officeSaveView);

    //Поиск по Id
    public OfficeIdViewOut findById(Long id);

    //Удаление
    public void delete(Office office);
}

INSERT INTO Organization (id, version, organization_name, organization_fullName, organization_inn, organization_kpp, organization_address, organization_phone,organization_isActive)
VALUES (1, 1, 'БЕЛЛ ИНТЕГРАТОР','АКЦИОНЕРНОЕ ОБЩЕСТВО "БЕЛЛ ИНТЕГРАТОР"',7714923230,771401001,'125167, ГОРОД МОСКВА, УЛИЦА ПЛАНЕТНАЯ УЛИЦА, ДОМ 11, ПОМЕЩЕНИЕ 9/10 РМ-4', '+7 (495) 980-61-81', TRUE);

INSERT INTO Office (id, version, office_name, office_address, office_phone, office_isActive, organization_id) VALUES (1, 1, 'Московский филиал','115088, г. Москва, 2-й Южнопортовый проезд 18, стр. 2', '+7 (495) 980-61-81', TRUE, 1);
INSERT INTO Office (id, version, office_name, office_address, office_phone, office_isActive, organization_id) VALUES (2, 1, 'Пензенский филиал', '440000, Пенза, ул. Московская 27', '+7 (8412) 988-061', TRUE, 1);

INSERT INTO Country (version, country_code, country_name) VALUES (1,643,'Российская Федерация');
INSERT INTO Country (version, country_code, country_name) VALUES (1,840,'Соединенные Штаты Америки');
INSERT INTO Country (version, country_code, country_name) VALUES (1,276,'Федеративная Республика Германия');
INSERT INTO Country (version, country_code, country_name) VALUES (1,826,'Соединенное Королевство Великобритании и Северной Ирландии');
INSERT INTO Country (version, country_code, country_name) VALUES (1,250,'Французская Республика');

INSERT INTO Document (version, document_code, document_name) VALUES (1,3,'Свидетельство о рождении');
INSERT INTO Document (version, document_code, document_name) VALUES (1,7,'Военный билет');
INSERT INTO Document (version, document_code, document_name) VALUES (1,8,'Временное удостоверение, выданное взамен военного билета');
INSERT INTO Document (version, document_code, document_name) VALUES (1,10,'Паспорт иностранного гражданина');
INSERT INTO Document (version, document_code, document_name) VALUES (1,11,'Свидетельство о рассмотрении ходатайства о признании лица беженцем на территории Российской Федерации по существу');
INSERT INTO Document (version, document_code, document_name) VALUES (1,12,'Вид на жительство в Российской Федерации');
INSERT INTO Document (version, document_code, document_name) VALUES (1,13,'Удостоверение беженца');
INSERT INTO Document (version, document_code, document_name) VALUES (1,15,'Разрешение на временное проживание в Российской Федерации');
INSERT INTO Document (version, document_code, document_name) VALUES (1,18,'Свидетельство о предоставлении временного убежища на территории Российской Федерации');
INSERT INTO Document (version, document_code, document_name) VALUES (1,21,'Паспорт гражданина Российской Федерации');
INSERT INTO Document (version, document_code, document_name) VALUES (1,23,'Свидетельство о рождении, выданное уполномоченным органом иностранного государства');
INSERT INTO Document (version, document_code, document_name) VALUES (1,24,'Удостоверение личности военнослужащего Российской Федерации');
INSERT INTO Document (version, document_code, document_name) VALUES (1,91,'Иные документы');

INSERT INTO User (version, user_firstname, user_secondname, user_middlename, user_position, user_phone, user_doccode, user_docnumber, user_docdate, user_citizenshipcode, user_isidentified, office_id)
          VALUES (1, 'Василий','Иванов', 'Иванович', 'Менеджер', '+7 (495) 980-61-81', 21,'7754 159753', '15.04.2015', 643, TRUE, 1);
INSERT INTO User (version, user_firstname, user_secondname, user_middlename, user_position, user_phone, user_doccode, user_docnumber, user_docdate, user_citizenshipcode, user_isidentified, office_id)
          VALUES (1, 'Алекс','Иванов', 'Иванович', 'Менеджер', '+7 (495) 980-61-81', 21,'7754 159753', '15.04.2015', 643, TRUE, 1);
INSERT INTO User (version, user_firstname, user_secondname, user_middlename, user_position, user_phone, user_doccode, user_docnumber, user_docdate, user_citizenshipcode, user_isidentified, office_id)
          VALUES (1, 'Джош','Иванов', 'Иванович', 'Менеджер', '+7 (495) 980-61-81', 21,'7754 159753', '15.04.2015', 643, TRUE, 1);
INSERT INTO User (version, user_firstname, user_secondname, user_middlename, user_position, user_phone, user_doccode, user_docnumber, user_docdate, user_citizenshipcode, user_isidentified, office_id)
          VALUES (1, 'Федр','Иванов', 'Иванович', 'Менеджер', '+7 (495) 980-61-81', 21,'7754 159753', '15.04.2015', 643, TRUE, 1);
INSERT INTO User (version, user_firstname, user_secondname, user_middlename, user_position, user_phone, user_doccode, user_docnumber, user_docdate, user_citizenshipcode, user_isidentified, office_id)
          VALUES (1, 'Том','Иванов', 'Иванович', 'Менеджер', '+7 (495) 980-61-81', 21,'7754 159753', '15.04.2015', 643, TRUE, 1);


INSERT INTO Account (version, login, password, name, role, isactive, activation_code)
VALUES (1, 'admin','123456','Админ','Admin', FALSE, NULL );
INSERT INTO Account (version, login, password, name, role, isactive, activation_code)
VALUES (1, 'alex','123456','Алекс','User', FALSE, NULL );
INSERT INTO Account (version, login, password, name, role, isactive, activation_code)
VALUES (1, 'vasya','123456','Василий','User', FALSE, NULL );
INSERT INTO Account (version, login, password, name, role, isactive, activation_code)
VALUES (1, 'josh','123456','Джош','User', FALSE, NULL );
INSERT INTO Account (version, login, password, name, role, isactive, activation_code)
VALUES (1, 'fedr','123456','Федр','User', FALSE, NULL );
INSERT INTO Account (version, login, password, name, role, isactive, activation_code)
VALUES (1, 'tom','123456','Том','User', FALSE, NULL );
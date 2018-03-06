CREATE TABLE IF NOT EXISTS Country (
    version    INTEGER NOT NULL,
    country_code   INTEGER PRIMARY KEY,
    country_name VARCHAR(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS Document (
    version    INTEGER NOT NULL,
    document_code   INTEGER PRIMARY KEY,
    document_name VARCHAR(300) NOT NULL
);

CREATE TABLE IF NOT EXISTS Office (
    id         INTEGER  PRIMARY KEY AUTO_INCREMENT,
    version    INTEGER NOT NULL,
    office_name    VARCHAR(50) NOT NULL,
    office_address    VARCHAR(100) NOT NULL,
    office_phone    VARCHAR(100) NOT NULL,
    office_isactive    BOOLEAN,
    organization_id INTEGER NOT NULL
);

CREATE TABLE IF NOT EXISTS Organization (
    id         INTEGER  PRIMARY KEY AUTO_INCREMENT,
    version    INTEGER NOT NULL,
    organization_name    VARCHAR(50) NOT NULL,
    organization_fullname    VARCHAR(50) NOT NULL,
    organization_inn    BIGINT NOT NULL,
    organization_kpp    BIGINT NOT NULL,
    organization_address    VARCHAR(100) NOT NULL,
    organization_phone    VARCHAR(100) NOT NULL,
    organization_isactive    BOOLEAN
);

CREATE TABLE IF NOT EXISTS User (
    id         INTEGER  PRIMARY KEY AUTO_INCREMENT,
    version    INTEGER NOT NULL,
    user_firstname    VARCHAR(50) NOT NULL,
    user_secondname    VARCHAR(50) NOT NULL,
    user_middlename    VARCHAR(50) NOT NULL,
    user_position    VARCHAR(50) NOT NULL,
    user_phone    VARCHAR(50) NOT NULL,
    user_doccode    INTEGER NOT NULL,
    user_docnumber    VARCHAR(50) NOT NULL,
    user_docdate    VARCHAR(50) NOT NULL,
    user_citizenshipcode    INTEGER NOT NULL,
    user_isidentified    BOOLEAN,
    office_id INTEGER
);

CREATE TABLE IF NOT EXISTS Account (
    id         INTEGER  PRIMARY KEY AUTO_INCREMENT,
    version    INTEGER NOT NULL,
    login    VARCHAR(50) NOT NULL,
    password    VARCHAR(50) NOT NULL,
    name    VARCHAR(50) NOT NULL,
    role    VARCHAR(50) NOT NULL,
    isactive BOOLEAN,
    activation_code    VARCHAR(200)
);

CREATE INDEX IX_Office_Organization_Id ON Office (organization_id);
ALTER TABLE Office ADD FOREIGN KEY (organization_id) REFERENCES Organization(id);

CREATE INDEX IX_User_Office_Id ON User (office_id);
ALTER TABLE User ADD FOREIGN KEY (office_id) REFERENCES Office(id);

CREATE INDEX IX_User_Country_Id ON User (user_citizenshipcode);
ALTER TABLE User ADD FOREIGN KEY (user_citizenshipcode) REFERENCES Country(country_code);

CREATE INDEX IX_User_Documents_Id ON User (user_doccode);
ALTER TABLE User ADD FOREIGN KEY (user_doccode) REFERENCES Document(document_code);
DROP TABLE IF EXISTS Account;
DROP TABLE IF EXISTS Doc_type;
DROP TABLE IF EXISTS Country;
DROP TABLE IF EXISTS User;
DROP TABLE IF EXISTS Organization;
DROP TABLE IF EXISTS Office;

CREATE TABLE IF NOT EXISTS Country (
    version    INTEGER NOT NULL,
    code   VARCHAR PRIMARY KEY,
    name VARCHAR NOT NULL
);

CREATE TABLE IF NOT EXISTS Doc_type (
    version    INTEGER NOT NULL,
    code   VARCHAR PRIMARY KEY,
    name VARCHAR NOT NULL
);

CREATE TABLE IF NOT EXISTS Office (
    id         INTEGER  PRIMARY KEY AUTO_INCREMENT,
    version    INTEGER,
    name    VARCHAR,
    address    VARCHAR,
    phone    VARCHAR,
    is_active    BOOLEAN,
    organization_id INTEGER
);

CREATE TABLE IF NOT EXISTS Organization (
    id         INTEGER  PRIMARY KEY AUTO_INCREMENT,
    version    INTEGER,
    name    VARCHAR,
    full_name    VARCHAR,
    inn    VARCHAR,
    kpp    VARCHAR,
    address    VARCHAR,
    phone    VARCHAR,
    is_active    BOOLEAN
);

CREATE TABLE IF NOT EXISTS User (
    id         INTEGER  PRIMARY KEY AUTO_INCREMENT,
    version    INTEGER,
    first_name    VARCHAR,
    second_name    VARCHAR,
    middle_name    VARCHAR,
    position    VARCHAR,
    phone    VARCHAR,
    doc_code    VARCHAR,
    doc_number    VARCHAR,
    doc_date    DATE,
    citizenship_code    VARCHAR,
    is_identified    BOOLEAN,
    office_id INTEGER
);

CREATE TABLE IF NOT EXISTS Account (
    id         INTEGER  PRIMARY KEY AUTO_INCREMENT,
    version    INTEGER NOT NULL,
    login    VARCHAR NOT NULL,
    password    VARCHAR NOT NULL,
    name    VARCHAR,
    role    VARCHAR,
    is_active BOOLEAN,
    activation_code    VARCHAR,
    code VARCHAR
);

CREATE INDEX IX_Office_Organization_Id ON Office (organization_id);
ALTER TABLE Office ADD FOREIGN KEY (organization_id) REFERENCES Organization(id);

CREATE INDEX IX_User_Office_Id ON User (office_id);
ALTER TABLE User ADD FOREIGN KEY (office_id) REFERENCES Office(id);


ALTER TABLE User ADD FOREIGN KEY (citizenship_code) REFERENCES Country(code);


ALTER TABLE User ADD FOREIGN KEY (doc_code) REFERENCES Doc_type(code);
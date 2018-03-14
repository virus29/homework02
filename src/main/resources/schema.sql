CREATE TABLE IF NOT EXISTS Country (
    version    INTEGER NOT NULL,
    code   VARCHAR(100) PRIMARY KEY,
    name VARCHAR(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS Doc_type (
    version    INTEGER NOT NULL,
    code   VARCHAR(100) PRIMARY KEY,
    name VARCHAR(300) NOT NULL
);

CREATE TABLE IF NOT EXISTS Office (
    id         INTEGER  PRIMARY KEY AUTO_INCREMENT,
    version    INTEGER,
    name    VARCHAR(50),
    address    VARCHAR(100),
    phone    VARCHAR(100),
    is_active    BOOLEAN,
    organization_id INTEGER
);

CREATE TABLE IF NOT EXISTS Organization (
    id         INTEGER  PRIMARY KEY AUTO_INCREMENT,
    version    INTEGER,
    name    VARCHAR(50) NOT NULL,
    full_name    VARCHAR(50),
    inn    BIGINT,
    kpp    BIGINT,
    address    VARCHAR(100),
    phone    VARCHAR(100),
    is_active    BOOLEAN
);

CREATE TABLE IF NOT EXISTS User (
    id         INTEGER  PRIMARY KEY AUTO_INCREMENT,
    version    INTEGER,
    first_name    VARCHAR(50) NOT NULL,
    second_name    VARCHAR(50),
    middle_name    VARCHAR(50),
    position    VARCHAR(50),
    phone    VARCHAR(50),
    doc_code    VARCHAR(100),
    doc_number    VARCHAR(50),
    doc_date    DATE,
    citizenship_code    VARCHAR(100),
    is_identified    BOOLEAN,
    office_id INTEGER
);

CREATE TABLE IF NOT EXISTS Account (
    id         INTEGER  PRIMARY KEY AUTO_INCREMENT,
    version    INTEGER,
    login    VARCHAR(50) NOT NULL,
    password    VARCHAR(50) NOT NULL,
    name    VARCHAR(50) NOT NULL,
    role    VARCHAR(50),
    is_active BOOLEAN,
    activation_code    VARCHAR(200)
);

CREATE INDEX IX_Office_Organization_Id ON Office (organization_id);
ALTER TABLE Office ADD FOREIGN KEY (organization_id) REFERENCES Organization(id);

CREATE INDEX IX_User_Office_Id ON User (office_id);
ALTER TABLE User ADD FOREIGN KEY (office_id) REFERENCES Office(id);


ALTER TABLE User ADD FOREIGN KEY (citizenship_code) REFERENCES Country(code);


ALTER TABLE User ADD FOREIGN KEY (doc_code) REFERENCES Doc_type(code);

CREATE DATABASE CRUDCarros;

USE CRUDCarros;

CREATE TABLE modelo(
    id        INT     PRIMARY KEY     AUTO_INCREMENT,
    nome            VARCHAR(45)
);

CREATE TABLE concessionaria(
    id       INT         PRIMARY KEY     AUTO_INCREMENT,
    cnpj                    VARCHAR(45) NOT NULL,
    nome                    VARCHAR(45) NOT NULL,
);

CREATE TABLE marca(
    id            INT         PRIMARY KEY AUTO_INCREMENT,
    nome                VARCHAR(45) NOT NULL,
    pais                VARCHAR(45) NOT NULL
);

CREATE TABLE carro(
    id                INT         PRIMARY KEY AUTO_INCREMENT,
    idModelo_fk             INT,
    idMarca_fk              INT,
    idConcessionaria_fk     INT,
    cor                     VARCHAR(45) NOT NULL,
    potencia                DOUBLE      NOT NULL,
    portas                  INT         NOT NULL,
    ano                     INT         NOT NULL,
    placa                   VARCHAR(100),
    nome                    VARCHAR(45),
    FOREIGN KEY(idModelo_fk) REFERENCES modelo(id),
    FOREIGN KEY(idMarca_fk) REFERENCES marca(id),
    FOREIGN KEY(idConcessionaria_fk) REFERENCES concessionaria(id)
);



CREATE TABLE Concessionaria (
   idConcessionaria     INT             PRIMARY KEY AUTO_INCREMENT,
   cnpj                 VARCHAR(45)     NOT NULL    UNIQUE,
   nome                 VARCHAR(45)     NOT NULL
);

CREATE TABLE Modelo (
    idModelo        INT         PRIMARY KEY AUTO_INCREMENT,
    Nome            VARCHAR(45) NOT NULL
);

CREATE TABLE Marca (
    idMarca         INT             PRIMARY KEY AUTO_INCREMENT,
    Nome            VARCHAR(45)     NOT NULL,
    Pais            VARCHAR(45)     NOT NULL
);

CREATE TABLE Carro (
    idCarro                 INT             PRIMARY KEY AUTO_INCREMENT,
    idModelo_fk             INT             NOT NULL,
    idMarca_fk              INT             NOT NULL,
    idConcessionaria_fk     INT             NOT NULL,
    cor                     VARCHAR(45)     NOT NULL,
    potencia                DOUBLE          NOT NULL,
    portas                  INT             NOT NULL,
    ano                     INT             NOT NULL,
    placa                   VARCHAR(45)
    FOREIGN KEY (idModelo_fk) REFERENCES Modelo (idModelo),
    FOREIGN KEY (idMarca_fk) REFERENCES Marca (idMarca),
    FOREIGN KEY (idConcessionaria_fk) REFERENCES Concessionaria (idConcessionaria)

);
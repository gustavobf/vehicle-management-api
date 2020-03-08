-- Criacao de tabelas e indices.
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
    placa                   VARCHAR(45),
    nome                    VARCHAR(45),    NOT NULL
    FOREIGN KEY (idModelo_fk) REFERENCES Modelo (idModelo),
    FOREIGN KEY (idMarca_fk) REFERENCES Marca (idMarca),
    FOREIGN KEY (idConcessionaria_fk) REFERENCES Concessionaria (idConcessionaria)

);

-- Inserindo dados.
INSERT INTO Carro VALUES    (NULL, 7, 5, 2, 'Branco' , 1400, 4, 2012, 'KPA-5689', 'Palio Weekend'),
                            (NULL, 7, 5, 2, 'Branco' , 1000, 4, 2019, 'HZA-9748', 'Onix'), 
                            (NULL, 6, 6, 1, 'Azul'   , 1600, 4, 2015, 'GHF-1564', 'Cobalt'),
                            (NULL, 4, 6, 3, 'Preto'  , 2000, 2, 2013, 'YKD-9485', 'GTR'),
                            (NULL, 3, 6, 4, 'Cinza'  , 1500, 4, 2012, 'DQF-1325', 'Supra'),
                            (NULL, 2, 6, 3, 'Amarelo', 1800, 2, 2018, 'GJN-9125', 'Celta');




-- Criando Views
CREATE VIEW detalhesCarro AS SELECT CA.idCarro AS ID, M.Nome AS Modelo, MA.Nome AS Marca,  C.Nome AS Concessionaria, CA.Cor, CA.potencia AS Cavalos, CA.Nome, CA.Portas, CA.Ano
	FROM Carro CA
    LEFT JOIN Modelo M ON idModelo_fk = idModelo
    LEFT JOIN Marca MA ON idMarca_fk = idMarca
    LEFT JOIN Concessionaria C ON idConcessionaria_fk = idConcessionaria;

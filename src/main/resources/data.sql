-- Brands
INSERT INTO brand (country, name)
VALUES ('Greece', 'Test Brand Inc.'),
       ('Italy', 'Italian Cars Ltd.'),
       ('Germany', 'German Motors GmbH');

-- Models
INSERT INTO model (name)
VALUES ('Sedan'),
       ('SUV'),
       ('Convertible');

-- Dealerships
INSERT INTO dealership (cnpj, name)
VALUES ('159753', 'Test Dealership Zero'),
       ('456789', 'Italian Motors'),
       ('987654', 'Euro Motors'),
       ('123456', 'German Auto House');

-- Cars
INSERT INTO car (manufacturing, color, name, plate, door, power, model_id, brand_id, dealership_id)
VALUES (2012, 'White', 'Sedan A1', 'AAA-1111', 4, 120, 1, 1, 1),
       (2015, 'Red', 'Italian SUV 500', 'BBB-2222', 5, 150, 2, 2, 2),
       (2018, 'Blue', 'Euro Sedan X1', 'CCC-3333', 4, 130, 1, 1, 3),
       (2019, 'Black', 'Test Sedan XL', 'DDD-4444', 4, 140, 1, 1, 1),
       (2020, 'Silver', 'Test Sedan LX', 'EEE-5555', 4, 160, 1, 1, 1),
       (2017, 'Gray', 'Test SUV A1', 'FFF-6666', 5, 180, 2, 1, 1),
       (2019, 'White', 'Test SUV B2', 'GGG-7777', 5, 200, 3, 1, 1),
       (2021, 'Yellow', 'German Convertible Z1', 'HHH-8888', 2, 220, 3, 3, 3);

-- Users
INSERT INTO app_user (username, password, email, active)
VALUES ('user', '$2a$10$ZlakZVAe9JkLFILmqo6f5.6yZXtWqcFfO8BEd4B/Hv5rD8Bwy7Eeq', 'email@email.com', true),
       ('admin', '$2a$10$ZlakZVAe9JkLFILmqo6f5.6yZXtWqcFfO8BEd4B/Hv5rD8Bwy7Eeq', 'email@email.com', true);

-- Roles
INSERT INTO role (name)
VALUES ('ROLE_USER'),
       ('ROLE_ADMIN');

-- User roles
INSERT INTO user_role (user_id, role_id)
VALUES (1, 1);
INSERT INTO user_role (user_id, role_id)
VALUES (2, 1);
INSERT INTO user_role (user_id, role_id)
VALUES (2, 2);

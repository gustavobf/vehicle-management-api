-- Brands
INSERT INTO brand VALUES 
(0, 'Greece', 'Test Brand Inc.'),
(1, 'Italy', 'Italian Cars Ltd.'),
(2, 'Germany', 'German Motors GmbH');

-- Models
INSERT INTO model VALUES 
(0, 'Sedan'),
(1, 'SUV'),
(2, 'Convertible');

-- Dealerships
INSERT INTO dealership VALUES 
(0, '159753', 'Test Dealership Zero'),
(1, '456789', 'Italian Motors'),
(2, '987654', 'Euro Motors'),
(3, '123456', 'German Auto House');

-- Cars
INSERT INTO car (id, manufacturing, color, name, plate, door, power, model_id, brand_id, dealership_id) VALUES
(0, 2012, 'White', 'Sedan A1', 'AAA-1111', 4, 120, 0, 0, 0),
(1, 2015, 'Red', 'Italian SUV 500', 'BBB-2222', 5, 150, 1, 1, 1),
(2, 2018, 'Blue', 'Euro Sedan X1', 'CCC-3333', 4, 130, 0, 0, 2),
(3, 2019, 'Black', 'Test Sedan XL', 'DDD-4444', 4, 140, 0, 0, 0),
(4, 2020, 'Silver', 'Test Sedan LX', 'EEE-5555', 4, 160, 0, 0, 0),
(5, 2017, 'Gray', 'Test SUV A1', 'FFF-6666', 5, 180, 1, 0, 0),
(6, 2019, 'White', 'Test SUV B2', 'GGG-7777', 5, 200, 2, 0, 0),
(7, 2021, 'Yellow', 'German Convertible Z1', 'HHH-8888', 2, 220, 2, 2, 3);

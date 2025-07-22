-- Brands
INSERT INTO brand (country, name)
VALUES ('Greece', 'Test Brand Inc.'),
       ('Italy', 'Italian Cars Ltd.'),
       ('Germany', 'German Motors GmbH'),
       ('Japan', 'Nippon Auto Co.'),
       ('USA', 'American Wheels');

-- Models
INSERT INTO model (name)
VALUES ('Sedan'),
       ('SUV'),
       ('Convertible'),
       ('Hatchback'),
       ('Pickup Truck');

-- Dealerships
INSERT INTO dealership (cnpj, name)
VALUES ('159753', 'Test Dealership Zero'),
       ('456789', 'Italian Motors'),
       ('987654', 'Euro Motors'),
       ('123456', 'German Auto House'),
       ('852963', 'Nippon Cars'),
       ('741852', 'US Auto Plaza');

-- Cars
INSERT INTO car (manufacturing, color, name, plate, door, power, model_id, brand_id, dealership_id)
VALUES (2012, 'White', 'Sedan A1', 'AAA-1111', 4, 120, 1, 1, 1),
       (2015, 'Red', 'Italian SUV 500', 'BBB-2222', 5, 150, 2, 2, 2),
       (2018, 'Blue', 'Euro Sedan X1', 'CCC-3333', 4, 130, 1, 1, 3),
       (2019, 'Black', 'Test Sedan XL', 'DDD-4444', 4, 140, 1, 1, 1),
       (2020, 'Silver', 'Test Sedan LX', 'EEE-5555', 4, 160, 1, 1, 1),
       (2017, 'Gray', 'Test SUV A1', 'FFF-6666', 5, 180, 2, 1, 1),
       (2019, 'White', 'Test SUV B2', 'GGG-7777', 5, 200, 3, 1, 1),
       (2021, 'Yellow', 'German Convertible Z1', 'HHH-8888', 2, 220, 3, 3, 3),
       (2022, 'Green', 'Nippon Hatchback S', 'III-9999', 4, 110, 4, 4, 5),
       (2020, 'Blue', 'US Pickup Xtreme', 'JJJ-0000', 2, 350, 5, 5, 6);

-- Users
INSERT INTO app_user (username, password, email, active)
VALUES ('user_john', '$2a$10$ZlakZVAe9JkLFILmqo6f5.6yZXtWqcFfO8BEd4B/Hv5rD8Bwy7Eeq', 'john@email.com', true),
       ('admin_jane', '$2a$10$ZlakZVAe9JkLFILmqo6f5.6yZXtWqcFfO8BEd4B/Hv5rD8Bwy7Eeq', 'jane@email.com', true),
       ('mike_smith', '$2a$10$ZlakZVAe9JkLFILmqo6f5.6yZXtWqcFfO8BEd4B/Hv5rD8Bwy7Eeq', 'mike.smith@email.com', true),
       ('anna_lee', '$2a$10$ZlakZVAe9JkLFILmqo6f5.6yZXtWqcFfO8BEd4B/Hv5rD8Bwy7Eeq', 'anna.lee@email.com', true);

-- Roles
INSERT INTO role (name)
VALUES ('ROLE_USER'),
       ('ROLE_ADMIN');

-- User Roles
INSERT INTO user_role (user_id, role_id)
VALUES (1, 1),
       (2, 1),
       (2, 2),
       (3, 1),
       (4, 1);

-- Ads
INSERT INTO ad (car_id, user_id, price, description, active)
VALUES (1, 1, 48000.00, 'Car in excellent condition, only 30,000 km driven.', true),
       (2, 3, 65000.00, 'Italian SUV, one owner, full service history.', true),
       (8, 4, 85000.00, 'German convertible, perfect for summer trips!', true),
       (9, 1, 23000.00, 'Eco-friendly hatchback, low mileage.', true),
       (10, 3, 40000.00, 'Powerful pickup truck, great for work and leisure.', true);

-- Orders
INSERT INTO app_order (ad_id, buyer_id, order_date, status, total_price)
VALUES (1, 2, NOW(), 'PENDING', 47000.00),
       (2, 4, NOW(), 'COMPLETED', 65000.00),
       (3, 1, NOW(), 'CANCELLED', 85000.00),
       (5, 4, NOW(), 'PENDING', 40000.00);

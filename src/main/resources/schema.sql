CREATE TABLE IF NOT EXISTS brand
(
    id
    SERIAL
    PRIMARY
    KEY,
    country
    VARCHAR
(
    100
),
    name VARCHAR
(
    255
) UNIQUE
    );

CREATE TABLE IF NOT EXISTS model
(
    id
    SERIAL
    PRIMARY
    KEY,
    name
    VARCHAR
(
    100
) UNIQUE
    );

CREATE TABLE IF NOT EXISTS dealership
(
    id
    SERIAL
    PRIMARY
    KEY,
    cnpj
    VARCHAR
(
    20
) UNIQUE,
    name VARCHAR
(
    255
)
    );

CREATE TABLE IF NOT EXISTS car
(
    id
    SERIAL
    PRIMARY
    KEY,
    manufacturing
    INTEGER,
    color
    VARCHAR
(
    50
),
    name VARCHAR
(
    255
),
    plate VARCHAR
(
    20
) UNIQUE,
    door INTEGER,
    power INTEGER,
    model_id INTEGER REFERENCES model
(
    id
),
    brand_id INTEGER REFERENCES brand
(
    id
),
    dealership_id INTEGER REFERENCES dealership
(
    id
)
    );

CREATE TABLE IF NOT EXISTS app_user
(
    id
    SERIAL
    PRIMARY
    KEY,
    username
    VARCHAR
(
    100
) UNIQUE,
    password VARCHAR
(
    255
),
    email VARCHAR
(
    255
) UNIQUE,
    active BOOLEAN
    );

CREATE TABLE IF NOT EXISTS role
(
    id
    SERIAL
    PRIMARY
    KEY,
    name
    VARCHAR
(
    100
) UNIQUE
    );

CREATE TABLE IF NOT EXISTS user_role
(
    user_id
    INTEGER
    REFERENCES
    app_user
(
    id
),
    role_id INTEGER REFERENCES role
(
    id
),
    PRIMARY KEY
(
    user_id,
    role_id
)
    );

CREATE TABLE IF NOT EXISTS ad
(
    id
    SERIAL
    PRIMARY
    KEY,
    car_id
    INTEGER
    REFERENCES
    car
(
    id
),
    user_id INTEGER REFERENCES app_user
(
    id
),
    price NUMERIC
(
    12,
    2
),
    description TEXT,
    active BOOLEAN
    );

CREATE TABLE IF NOT EXISTS app_order
(
    id
    SERIAL
    PRIMARY
    KEY,
    ad_id
    INTEGER
    REFERENCES
    ad
(
    id
),
    buyer_id INTEGER REFERENCES app_user
(
    id
),
    order_date TIMESTAMP,
    status VARCHAR
(
    20
),
    total_price NUMERIC
(
    12,
    2
)
    );

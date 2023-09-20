CREATE TABLE IF NOT EXISTS users
(
    id       serial primary key,
    name     varchar(50)        not null,
    username varchar(50) unique not null,
    email    varchar(50) unique not null,
    password varchar(50)        not null
);

CREATE TABLE IF NOT EXISTS category
(
    id          serial primary key,
    name        varchar(50) unique not null,
    description varchar(50)        not null
);

CREATE TABLE IF NOT EXISTS brand
(
    id          serial primary key,
    name        varchar(50) unique not null,
    website     varchar(50)        not null,
    description varchar(50)        not null
);

CREATE TABLE IF NOT EXISTS product
(
    id          serial primary key,
    name        varchar(50) not null,
    create_date date        not null,
    category_id int references category (id),
    brand_id    int references brand (id)
);

CREATE TABLE IF NOT EXISTS shareholder
(
    id           serial primary key,
    name         varchar(50)        not null,
    phoneNumber  varchar(50)        not null,
    nationalCode varchar(50) unique not null
);

CREATE TABLE IF NOT EXISTS shareholder_brand
(
    shareholder_id int references shareholder (id),
    brand_id       int references brand (id),
    primary key (shareholder_id, brand_id)
);

Alter sequence brand_id_seq restart with 1;
Alter sequence users_id_seq restart with 1;
Alter sequence product_id_seq restart with 1;
Alter sequence category_id_seq restart with 1;
Alter sequence shareholder_id_seq restart with 1;
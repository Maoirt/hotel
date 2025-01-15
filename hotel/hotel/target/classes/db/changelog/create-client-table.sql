--liquibase formatted sql
--changeset Daniil Yatsiny:1
CREATE TABLE client
(
    id uuid NOT NULL DEFAULT gen_random_uuid(),
    first_name varchar(50) NOT NULL,
    last_name varchar(50) NOT NULL,
    mid_name varchar(50) NOT NULL,
    passport varchar(50) NOT NULL UNIQUE,
    check_in TIMESTAMP,
    eviction TIMESTAMP,
    room_number INT,
    CONSTRAINT client_id_pk PRIMARY KEY (id),
    CONSTRAINT fk_room FOREIGN KEY (room_number) REFERENCES room(number)
);

--rollback drop table client;
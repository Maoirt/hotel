--liquibase formatted sql
--changeset Daniil Yatsiny:2

CREATE TYPE room_status AS ENUM ('AVAILABLE', 'OCCUPIED');

CREATE TABLE room
(
    number int NOT NULL,
    floor int NOT NULL,
    amount int NOT NULL,
    status varchar(50) NOT NULL,
    CONSTRAINT room_id_pk PRIMARY KEY (number)
);

--rollback drop table room;
--rollback drop type room_status;
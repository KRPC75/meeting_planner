DROP TABLE IF EXISTS rooms;
DROP TABLE IF EXISTS plannings;

CREATE TABLE rooms
(
    `name`     VARCHAR(50) PRIMARY KEY,
    capacity   INT NOT NULL,
    equipments VARCHAR(250)
);

CREATE TABLE plannings
(
    id          BIGINT      NOT NULL PRIMARY KEY AUTO_INCREMENT,
    hour        INT         NOT NULL,
    date        DATE        NOT NULL,
    maintenance BOOLEAN     NOT NULL,
    room_name   VARCHAR(50) NOT NULL
);

INSERT INTO rooms (`name`, capacity, equipments)
VALUES ('E1001', 23, null),
       ('E1002', 10, 'ECRAN'),
       ('E1003', 8, 'PIEUVRE'),
       ('E1004', 4, 'TABLEAU'),
       ('E2001', 4, null),
       ('E2002', 15, 'ECRAN,WEBCAM'),
       ('E2003', 7, null),
       ('E2004', 9, 'TABLEAU'),
       ('E3001', 13, 'ECRAN,WEBCAM,PIEUVRE'),
       ('E3002', 8, null),
       ('E3003', 9, 'ECRAN,PIEUVRE'),
       ('E3004', 4, null);
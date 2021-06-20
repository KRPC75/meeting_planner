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
CREATE TABLE users
(
    login    VARCHAR(50) PRIMARY KEY,
    name     VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL
);

INSERT INTO users (login, name, password)
VALUES ('login_jack', 'Jack', '123'),
       ('login_bob', 'Bob', '456');


CREATE TABLE roles
(
    id   BIGSERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL
);

INSERT INTO roles (name)
VALUES ('ROLE_USER'),
       ('ROLE_MANAGER'),
       ('ROLE_ADMIN');

CREATE TABLE users_roles
(
    user_login VARCHAR NOT NULL REFERENCES users (login),
    role_id    BIGINT  NOT NULL REFERENCES roles (id),
    primary key (user_login, role_id)
);

INSERT INTO users_roles (user_login, role_id)
VALUES ('login_jack', 1),
       ('login_jack', 2),
       ('login_bob', 2),
       ('login_bob', 3);
CREATE TABLE roles
(
    role_id   INT AUTO_INCREMENT PRIMARY KEY,
    role_name ENUM ('ROLE_SUPER_ADMIN', 'ROLE_CUSTOMER', 'ROLE_DRIVER') NOT NULL
);

INSERT INTO roles
VALUES (1, 'ROLE_SUPER_ADMIN'),
       (2, 'ROLE_CUSTOMER'),
       (3, 'ROLE_DRIVER');

CREATE TABLE users
(
    user_id       INT AUTO_INCREMENT PRIMARY KEY,
    role_id       INT                 NOT NULL,
    first_name    VARCHAR(50)         NOT NULL,
    last_name     VARCHAR(50)         NOT NULL,
    phone_no      VARCHAR(10) UNIQUE  NOT NULL,
    email_id      VARCHAR(254) UNIQUE NOT NULL,
    password      VARCHAR(20)         NOT NULL,
    is_active     BOOLEAN             NOT NULL DEFAULT FALSE,
    is_blocked    BOOLEAN                      DEFAULT FALSE,
    total_ratings INT                          DEFAULT 0,
    rating_count  INT                          DEFAULT 0,
    display_id    VARCHAR(10) UNIQUE,
    created_at    DATETIME                     DEFAULT CURRENT_TIMESTAMP,
    updated_at    DATETIME                     DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (role_id) REFERENCES roles (role_id)
);


INSERT INTO users (role_id, first_name, last_name, phone_no, email_id, password, is_active, display_id)
VALUES (1, 'Krushit', 'Babariya', '7777777777', 'ksb@admin.in', 'sadmin@123', TRUE, 'SUPERADMIN');

CREATE TABLE drivers
(
    driver_id            INT AUTO_INCREMENT PRIMARY KEY,
    user_id              INT UNIQUE                                             NOT NULL,
    licence_number       VARCHAR(15) UNIQUE,
    is_document_verified BOOLEAN                                                         DEFAULT FALSE,
    licence_photo        VARCHAR(255),
    is_available         BOOLEAN,
    verification_status  ENUM ('PENDING', 'REJECTED', 'VERIFIED', 'INCOMPLETE') NOT NULL DEFAULT 'INCOMPLETE',
    comment              VARCHAR(254),
    created_at           DATETIME                                                        DEFAULT CURRENT_TIMESTAMP,
    updated_at           DATETIME                                                        DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users (user_id),
    CONSTRAINT unique_driver_license UNIQUE (licence_number)
);

CREATE TABLE user_favorites
(
    user_id          INT NOT NULL,
    favorite_user_id INT NOT NULL,
    PRIMARY KEY (user_id, favorite_user_id),
    FOREIGN KEY (user_id) REFERENCES users (user_id),
    FOREIGN KEY (favorite_user_id) REFERENCES users (user_id)
);


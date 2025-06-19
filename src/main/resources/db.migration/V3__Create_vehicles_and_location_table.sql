CREATE TABLE vehicle_Service
(
    service_id            INT AUTO_INCREMENT PRIMARY KEY,
    service_name          VARCHAR(50) UNIQUE      NOT NULL,
    base_fare             DECIMAL(10, 2)          NOT NULL,
    per_km_rate           DECIMAL(10, 2)          NOT NULL,
    vehicle_type          ENUM ('2W', '3W', '4W') NOT NULL,
    max_passengers        INT                     NOT NULL,
    commission_percentage DECIMAL(3, 1)           NOT NULL
);

CREATE TABLE brand_Models
(
    brand_model_id INT AUTO_INCREMENT PRIMARY KEY,
    service_id     INT         NOT NULL,
    brand_name     VARCHAR(20) NOT NULL,
    model          VARCHAR(20) NOT NULL,
    min_year       INT         NOT NULL,
    FOREIGN KEY (service_id) REFERENCES Vehicle_Service (service_id) ON DELETE CASCADE
);

CREATE TABLE vehicles
(
    vehicle_id          INT AUTO_INCREMENT PRIMARY KEY,
    driver_id           INT UNIQUE,
    brand_model_id      INT                                             NOT NULL,
    registration_number VARCHAR(20) UNIQUE                              NOT NULL,
    year                INT                                             NOT NULL,
    fuel_type           ENUM ('PETROL', 'DIESEL', 'ELECTRIC', 'HYBRID') NOT NULL,
    transmission        ENUM ('MANUAL', 'AUTOMATIC')                    NOT NULL,
    ground_clearance    double,
    wheel_base          double,
    FOREIGN KEY (driver_id) REFERENCES users (user_id) ON DELETE CASCADE,
    FOREIGN KEY (brand_model_id) REFERENCES brand_models (brand_model_id) ON DELETE CASCADE
);

CREATE TABLE locations
(
    location_id INT PRIMARY KEY AUTO_INCREMENT,
    name        VARCHAR(20) UNIQUE NOT NULL,
    is_active   BOOLEAN DEFAULT TRUE
);
CREATE TABLE ride_requests
(
    ride_request_id      INT PRIMARY KEY AUTO_INCREMENT,
    ride_request_status  ENUM('PENDING', 'ACCEPTED', 'CANCELLED', 'REJECTED')  NOT NULL,
    pick_up_location_id  INT                                                   NOT NULL,
    drop_off_location_id INT                                                   NOT NULL,
    vehicle_service_id   INT                                                   NOT NULL,
    user_id              INT                                                   NOT NULL,
    ride_date            DATE                                                  NOT NULL,
    pick_up_time         TIME                                                  NOT NULL,
    created_at           DATETIME                                              NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at           DATETIME                                                       DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

    FOREIGN KEY (pick_up_location_id) REFERENCES locations (location_id),
    FOREIGN KEY (drop_off_location_id) REFERENCES locations (location_id),
    FOREIGN KEY (user_id) REFERENCES users (user_id),
    FOREIGN KEY (vehicle_service_id) REFERENCES vehicle_service (service_id),

    CONSTRAINT chk_different_locations CHECK (pick_up_location_id <> drop_off_location_id)
);
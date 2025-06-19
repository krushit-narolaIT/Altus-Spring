CREATE TABLE commission_slabs
(
    commission_id         INT PRIMARY KEY AUTO_INCREMENT,
    from_km               DOUBLE        NOT NULL,
    to_km                 DOUBLE        NOT NULL,
    commission_percentage DECIMAL(3, 1) NOT NULL
);

INSERT INTO commission_slabs (from_km, to_km, commission_percentage)
VALUES (0.00, 3.00, 25.0),
       (3.01, 10.00, 20.0),
       (10.01, 20.00, 15.0),
       (20.01, 50.00, 12.0),
       (50.01, 500.00, 10.0);
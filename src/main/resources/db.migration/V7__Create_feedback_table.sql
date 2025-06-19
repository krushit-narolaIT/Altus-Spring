CREATE TABLE feedback
(
    feedback_id  INT AUTO_INCREMENT PRIMARY KEY,
    from_user_id INT NOT NULL,
    to_user_id   INT NOT NULL,
    ride_id      INT NOT NULL,
    rating       INT NOT NULL,
    comment      TEXT,
    created_at   DATETIME DEFAULT CURRENT_TIMESTAMP,

    FOREIGN KEY (from_user_id) REFERENCES users (user_id),
    FOREIGN KEY (to_user_id) REFERENCES users (user_id),
    FOREIGN KEY (ride_id) REFERENCES rides (ride_id) ON DELETE CASCADE,

    CONSTRAINT chk_rating CHECK (rating BETWEEN 1 AND 5)
);

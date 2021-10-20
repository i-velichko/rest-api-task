CREATE TABLE tag
(
    id   BIGINT      NOT NULL AUTO_INCREMENT,
    name VARCHAR(30) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE GIFT_CERTIFICATE
(
    id               BIGINT       NOT NULL AUTO_INCREMENT,
    name             VARCHAR(100) NOT NULL,
    price            DECIMAL(5, 2),
    create_date      TIMESTAMP DEFAULT CURRENT_TIMESTAMP(),
    last_update_date TIMESTAMP ,
    duration         INT,
    description      VARCHAR(200),
    PRIMARY KEY (id)
);


CREATE TABLE certificates_tags
(
    tag_id         INT NOT NULL,
    certificate_id INT NOT NULL,
    PRIMARY KEY (tag_id, certificate_id),
    FOREIGN KEY (tag_id) REFERENCES tag (id) ON DELETE CASCADE,
    FOREIGN KEY (certificate_id) REFERENCES gift_certificate (id) ON DELETE CASCADE
);

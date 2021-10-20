INSERT INTO gift_certificate (name, description, price, create_date, last_update_date, duration)
VALUES ('certificate 1', 'description 1', 1.1, '2021-10-08 11:11:11', '2021-01-01 01:22:11', 1),
       ('certificate 2', 'description 2', 2.2, '2021-10-08 11:11:11', '2021-01-01 01:22:11', 2),
       ('certificate 3', 'description 3', 3.3, '2021-10-08 11:11:11', '2021-01-01 01:22:11', 3);

INSERT INTO tag (name)
VALUES ('i'),
       ('want'),
       ('work'),
       ('epam');

INSERT INTO certificates_tags (certificate_id, tag_id)
VALUES (1, 4),
       (1, 3),
       (2, 2),
       (3, 4);


insert into _user(id, uuid, version, email, password, role_name, username)
VALUES (1, '8e9d57c7-e7ba-4cca-ad6a-d5b2736d29ca', 0, 'user@mail.com', '$2a$10$HSg0J2YNBLJqCqWdxWddReS19MSd.D10shiOeqrQr206wKJxM7dpS', 'USER', 'user'),
       (2, '20ada788-232c-454f-aa13-c654c75cac96', 0, 'user2@mail.com', '$2a$10$u.cbzMzmcItVbIqX1eq7quFBcql9HQn3Q7eWsrENyaBfcWsO6idPG', 'USER', 'user2'),
       (3, '50d80aba-bcd7-4d10-964e-454c4b0c9f3e', 0, 'user3@mail.com', '$2a$10$kt5hcbJDfbXYYrBLKzW.B.8orsqeEoKV/Votr/H4WqNE7ZQh8QoxG', 'USER', 'user3'),
       (4, 'be416234-101b-46dd-81eb-612a78264082', 0, 'admin@mail.com', '$2a$10$pvQ2lnHKrdjT4IWY6MK8Eup/wXdUF7AgU0nLNWzHj/JRb0dN8P1lS', 'ADMIN', 'adminUser');

insert into auction(auction_type, auction_id, uuid, version, auction_end_time,
                    auction_start_time, description, item_category, item_status,
                    starting_price, current_price, is_limited, is_premium, user_id)
VALUES ('bidding', 1, 1, 1, DATEADD(dd, 7, current_timestamp), CURRENT_TIMESTAMP, 'A Game of Thrones book',
        'BOOK', 'USED', 40, 40, 0, null, 1),
       ('buy_now', 2, 2, 1, DATEADD(dd, 10, current_timestamp), CURRENT_TIMESTAMP, 'Mens jeans size L',
        'CLOTHES', 'NEW', 59.99, null, null, 1, 2),
       ('bidding', 3, 3, 1, DATEADD(dd, 12, current_timestamp), CURRENT_TIMESTAMP, 'iPhone 5s',
        'ELECTRONICS', 'USED', 140, 40, 1, null, 1),
       ('bidding', 4, 4, 1, DATEADD(dd, 15, current_timestamp), CURRENT_TIMESTAMP, 'wooden cupboard',
        'FURNITURE', 'USED', 225, 225, 1, null, 2),
       ('buy_now', 5, 5, 1, DATEADD(dd, 10, current_timestamp), CURRENT_TIMESTAMP, '20kg dumbells',
        'SPORT', 'NEW', 79.99, null, null, 0, 1),
       ('buy_now', 6, 6, 1, DATEADD(dd, 22, current_timestamp), CURRENT_TIMESTAMP, 'Little women book',
        'CLOTHES', 'USED', 25, null, null, 0, 3);

insert into bid(id, uuid, version, bid_price, bid_time, auction_id, user_id)
VALUES (1, 1, 1, 50, CURRENT_TIMESTAMP, 1, 2),
       (2, 2, 1, 60, CURRENT_TIMESTAMP, 1, 3),
       (3, 3, 1, 70, CURRENT_TIMESTAMP, 4, 2),
       (4, 4, 1, 300, CURRENT_TIMESTAMP, 4, 3),
       (5, 5, 1, 450, CURRENT_TIMESTAMP, 4, 2);

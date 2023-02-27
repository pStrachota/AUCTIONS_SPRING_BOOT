

insert into _user(user_id, uuid, version, email, password, role_name, username)
VALUES (1, '8e9d57c7-e7ba-4cca-ad6a-d5b2736d29ca', 0, 'user@mail.com', '$2a$10$HSg0J2YNBLJqCqWdxWddReS19MSd.D10shiOeqrQr206wKJxM7dpS', 'USER', 'user'),
       (2, '20ada788-232c-454f-aa13-c654c75cac96', 0, 'user2@mail.com', '$2a$10$u.cbzMzmcItVbIqX1eq7quFBcql9HQn3Q7eWsrENyaBfcWsO6idPG', 'USER', 'user2'),
       (3, '50d80aba-bcd7-4d10-964e-454c4b0c9f3e', 0, 'user3@mail.com', '$2a$10$kt5hcbJDfbXYYrBLKzW.B.8orsqeEoKV/Votr/H4WqNE7ZQh8QoxG', 'USER', 'user3'),
       (4, 'be416234-101b-46dd-81eb-612a78264082', 0, 'admin@mail.com', '$2a$10$pvQ2lnHKrdjT4IWY6MK8Eup/wXdUF7AgU0nLNWzHj/JRb0dN8P1lS', 'ADMIN', 'adminUser');

insert into auction(auction_type, auction_id, uuid, version, auction_end_time,
                    auction_start_time, description, item_category, item_status,
                    starting_price, current_price, is_limited, is_premium, user_id)
VALUES ('bidding', 1, '97b6f2d7-8d6b-4e1e-9c3e-45b4f62b5897', 0, DATEADD(dd, 7, current_timestamp), CURRENT_TIMESTAMP, 'A Game of Thrones book',
        'BOOK', 'USED', 40, 40, 0, null, 1),
       ('buy_now', 2, 'b7a8e8a4-7e47-4a83-bdae-8ed675998102', 0, DATEADD(dd, 10, current_timestamp), CURRENT_TIMESTAMP, 'Mens jeans size L',
        'CLOTHES', 'NEW', 59.99, null, null, 1, 2),
       ('bidding', 3, 'e7310d20-21d3-4c36-a918-2b259f0e0f47', 0, DATEADD(dd, 12, current_timestamp), CURRENT_TIMESTAMP, 'iPhone 5s',
        'ELECTRONICS', 'USED', 140, 40, 1, null, 1),
       ('bidding', 4, '31e47f1a-7f8e-4172-a0f9-0e389991f1bb', 0, DATEADD(dd, 15, current_timestamp), CURRENT_TIMESTAMP, 'wooden cupboard',
        'FURNITURE', 'USED', 225, 225, 1, null, 2),
       ('buy_now', 5, 'd9ccca5e-9605-4b3a-b9eb-b9c878a5eb5c', 0, DATEADD(dd, 10, current_timestamp), CURRENT_TIMESTAMP, '20kg dumbells',
        'SPORT', 'NEW', 79.99, null, null, 0, 1),
       ('buy_now', 6, '846d5a44-2a14-4eb2-8b8a-9e69c18efbeb', 0, DATEADD(dd, 22, current_timestamp), CURRENT_TIMESTAMP, 'Little women book',
        'CLOTHES', 'USED', 25, null, null, 0, 3);

insert into bid(bid_id, uuid, version, bid_price, bid_time, auction_id, user_id)
VALUES (1, '5f28400d-812c-43dd-80e9-83e5667b2769', 0, 50, CURRENT_TIMESTAMP, 1, 2),
       (2, 'a6e54f6e-8f7d-48c1-b7b4-2d03db7a9dc6', 0, 60, CURRENT_TIMESTAMP, 1, 3),
       (3, 'c040ad4c-4c4b-4e48-97b7-cf2b8d9d9eb4', 0, 70, CURRENT_TIMESTAMP, 4, 2),
       (4, '7eb0f3c4-4d4e-4aa1-a438-52d8e15f9a2d', 0, 300, CURRENT_TIMESTAMP, 4, 3),
       (5, '246b00a4-7a54-4d3c-9e19-4e4c2c5f5b9d', 0, 450, CURRENT_TIMESTAMP, 4, 2);

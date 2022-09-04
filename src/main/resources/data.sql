INSERT INTO Auction (email, description, starting_price, current_price, auction_type, item_Status, item_Category,
                     auction_start_time, auction_End_Time)
VALUES ('first@mail.com', 'A Game of Thrones book', '40', '40', 'BIDDING', 'USED', 'BOOK', CURRENT_TIMESTAMP,
        CURRENT_TIMESTAMP + INTERVAL '7' DAY);

INSERT INTO Auction (email, description, starting_price, current_price, auction_type, item_Status, item_Category,
                     auction_start_time, auction_End_Time)
VALUES ('second@mail.com', 'Mens jeans size L', '59.99', '59.99', 'BUY_NOW', 'NEW', 'CLOTHES', CURRENT_TIMESTAMP,
        CURRENT_TIMESTAMP + INTERVAL '14' DAY);

INSERT INTO Auction (email, description, starting_price, current_price, auction_type, item_Status, item_Category,
                     auction_start_time, auction_End_Time)
VALUES ('third@mail.com', 'iPhone 5s', '140', '140', 'BIDDING', 'USED', 'ELECTRONICS', CURRENT_TIMESTAMP,
        CURRENT_TIMESTAMP + INTERVAL '12' DAY);

INSERT INTO Auction (email, description, starting_price, current_price, auction_type, item_Status, item_Category,
                     auction_start_time, auction_End_Time)
VALUES ('fourth@mail.com', 'wooden cupboard', '225', '225', 'BIDDING', 'USED', 'FURNITURE', CURRENT_TIMESTAMP,
        CURRENT_TIMESTAMP + INTERVAL '10' DAY);

INSERT INTO Auction (email, description, starting_price, current_price, auction_type, item_Status, item_Category,
                     auction_start_time, auction_End_Time)
VALUES ('fifth@mail.com', '20kg dumbells', '79.99', '79.99', 'BUY_NOW', 'NEW', 'SPORT', CURRENT_TIMESTAMP,
        CURRENT_TIMESTAMP + INTERVAL '14' DAY);

INSERT INTO Auction (email, description, starting_price, current_price, auction_type, item_Status, item_Category,
                     auction_start_time, auction_End_Time)
VALUES ('sixth@mail.com', 'Little women book', '25', '25', 'BUY_NOW', 'USED', 'BOOK', CURRENT_TIMESTAMP,
        CURRENT_TIMESTAMP + INTERVAL '24' DAY);

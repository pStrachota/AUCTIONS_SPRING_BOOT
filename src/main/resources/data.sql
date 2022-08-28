INSERT INTO Auction (email, description, starting_price, current_price, auction_type, item_Status, item_Category,
                     auction_start_time, auction_End_Time)
VALUES ('first@mail.com', 'Game of Thrones first book', '20', '20', 'BIDDING', 'NEW', 'BOOK', CURRENT_TIMESTAMP,
        CURRENT_TIMESTAMP + INTERVAL '1' DAY);

INSERT INTO Auction (email, description, starting_price, current_price, auction_type, item_Status, item_Category,
                     auction_start_time, auction_End_Time)
VALUES ('second@mail.com', 'Mens jeans size L', '40.5', '40.5', 'BUY_NOW', 'USED', 'CLOTHES', CURRENT_TIMESTAMP,
        CURRENT_TIMESTAMP + INTERVAL '7' DAY);

INSERT INTO Auction (email, description, starting_price, current_price, auction_type, item_Status, item_Category,
                     auction_start_time, auction_End_Time)
VALUES ('third@mail.com', 'iPhone 5s', '140', '140', 'BIDDING', 'USED', 'ELECTRONICS', CURRENT_TIMESTAMP,
        CURRENT_TIMESTAMP + INTERVAL '13' DAY);

INSERT INTO Auction (email, description, starting_price, current_price, auction_type, item_Status, item_Category,
                     auction_start_time, auction_End_Time)
VALUES ('fourth@mail.com', 'cupboard', '20', '20', 'BIDDING', 'NEW', 'FURNITURE', CURRENT_TIMESTAMP,
        CURRENT_TIMESTAMP + INTERVAL '14' DAY);

INSERT INTO Auction (email, description, starting_price, current_price, auction_type, item_Status, item_Category,
                     auction_start_time, auction_End_Time)
VALUES ('fifth@mail.com', '20kg dumbell', '20', '20', 'BUY_NOW', 'NEW', 'OTHER', CURRENT_TIMESTAMP,
        CURRENT_TIMESTAMP + INTERVAL '27' DAY);

INSERT INTO Auction (email, description, starting_price, current_price, auction_type, item_Status, item_Category,
                     auction_start_time, auction_End_Time)
VALUES ('sixth@mail.com', 'Little woman', '20', '20', 'BUY_NOW', 'USED', 'BOOK', CURRENT_TIMESTAMP,
        CURRENT_TIMESTAMP + INTERVAL '19' DAY);

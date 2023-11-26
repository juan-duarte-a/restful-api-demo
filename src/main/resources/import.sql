INSERT INTO product (name, price) VALUES ('Vizio 50-inch Class V-Series 4K UHD LED Smart TV', 248.99);
INSERT INTO product (name, price) VALUES ('Amazon Fire TV 50" 4-Series 4K UHD smart TV', 290.55);
INSERT INTO product (name, price) VALUES ('Sony Bravia 55-inch 4K X90K TV', 798.0);
INSERT INTO product (name, price) VALUES ('Samsung 98-Inch Class QLED 4K Q80C Series Smart TV', 4998);
INSERT INTO product (name, price) VALUES ('Apple AirPods (2nd Generation)', 80.99);
INSERT INTO product (name, price) VALUES ('Hkerr Wireless Earbuds', 10);
INSERT INTO product (name, price) VALUES ('Tozo T6 True Wireless Earbuds', 21.5);
INSERT INTO product (name, price) VALUES ('Sony WH-XB910N Noise Cancelling Headphones', 118);
INSERT INTO product (name, price) VALUES ('Apple AirPods Pro (2nd Generation)', 190);
INSERT INTO product (name, price) VALUES ('Apple 2020 MacBook Air Laptop', 750.99);

INSERT INTO client (name, phone_number, email) VALUES ('John Doe', '+11 345 576 2345', 'john.doe@email.com');
INSERT INTO client (name, phone_number, email) VALUES ('Karen Smith', '+43 782 1033 9117', 'karen321sm@orgmail.com');

INSERT INTO "'order'" (date, client_id, total_price) VALUES ('2023-11-23', 1, 841);
INSERT INTO "'order'" (date, client_id, total_price) VALUES ('2023-10-24', 2, 2362.53);

INSERT INTO order_product (order_id, product_id, quantity) VALUES (1, 3, 1);
INSERT INTO order_product (order_id, product_id, quantity) VALUES (1, 7, 2);
INSERT INTO order_product (order_id, product_id, quantity) VALUES (2, 10, 2);
INSERT INTO order_product (order_id, product_id, quantity) VALUES (2, 9, 3);
INSERT INTO order_product (order_id, product_id, quantity) VALUES (2, 2, 1);
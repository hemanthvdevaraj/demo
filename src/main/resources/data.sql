INSERT INTO userinfo (id,email,phone) VALUES (1,'user1','9899899891');
INSERT INTO userinfo (id,email,phone) VALUES (2,'user2','9899899892');

INSERT INTO product (id,name,brand,price) VALUES (1,'alpha bounce','adidas', 5000);
INSERT INTO product (id,name,brand,price) VALUES (2,'vapor fly','nike', 15000);

INSERT INTO inventory (id,product_id,quantity) VALUES (1,1,10);
INSERT INTO inventory (id,product_id,quantity) VALUES (2,2,20);

--INSERT INTO cart (id,product_id,user_id) VALUES (1,1,1);
--INSERT INTO cart (id,product_id,user_id) VALUES (2,2,1);
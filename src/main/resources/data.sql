INSERT INTO USERS (NAME, EMAIL, PASSWORD)
VALUES ('John', 'user@yandex.ru', '{noop}password'),
       ('Kate', 'admin@yandex.ru', '{noop}admin'),
       ('Victor', 'victor@yandex.ru', '{noop}victor');

INSERT INTO USER_ROLE (ROLE, USER_ID)
VALUES ('USER', 1),
       ('ADMIN', 2),
       ('USER', 2),
       ('USER', 3);

INSERT INTO RESTAURANT (name, registered)
VALUES ('Fast food M', '2023-06-01'),
       ('Bingo Dish', '2023-04-30'),
       ('Foody', now());

INSERT INTO DISH (name, price, RESTAURANT_ID, menu_date)
VALUES ('Tomato soup', 2000, 2, '2023-06-20'),
       ('Caesar salad', 2300, 2, '2023-06-20'),
       ('Chicken curry', 3500, 2, '2023-06-20'),
       ('Fish soup', 1800, 2, '2023-06-21'),
       ('Green salad', 1500, 2, '2023-06-21'),
       ('Beef', 3000, 2, '2023-06-21'),
       ('Beef burger', 3100, 2, now()),
       ('Chocolate cake', 1600, 2, now()),
       ('Cheese soup', 1500, 1, '2023-06-20'),
       ('Cheeseburger', 2100, 1, '2023-06-20'),
       ('Donuts with coffee', 1700, 1, '2023-06-21'),
       ('Fish and chips', 2400, 1, '2023-06-21'),
       ('Borsch', 2300, 3, '2023-06-20'),
       ('Russian salad', 1900, 3, '2023-06-20'),
       ('Pickled mushrooms', 120, 3, '2023-06-20'),
       ('Fish soup', 2000, 3, '2023-06-21'),
       ('Beef with potatoes', 2600, 3, '2023-06-21');

INSERT INTO VOTE (USER_ID, voting_date, voting_time, RESTAURANT_ID)
VALUES (1, '2023-06-20', '10:00', 2),
       (1, '2023-06-21', '15:00', 3),
       (3, '2023-06-20', '11:50', 1),
       (3, '2023-06-21', '12:00', 1),
       (1, now(), now(), 2);




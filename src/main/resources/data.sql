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
VALUES ('Tomato soup', 20.0, 2, '2023-06-20'),
       ('Caesar salad', 23.0, 2, '2023-06-20'),
       ('Chicken curry', 35.0, 2, '2023-06-20'),
       ('Fish soup', 18.0, 2, '2023-06-21'),
       ('Green salad', 15.0, 2, '2023-06-21'),
       ('Beef', 30.0, 2, '2023-06-21'),
       ('Beef burger', 31.0, 2, now()),
       ('Chocolate cake', 16.0, 2, now()),
       ('Cheese soup', 15.0, 1, '2023-06-20'),
       ('Cheeseburger', 21.0, 1, '2023-06-20'),
       ('Donuts with coffee', 17.0, 1, '2023-06-21'),
       ('Fish and chips', 24.0, 1, '2023-06-21'),
       ('Borsch', 23.0, 3, '2023-06-20'),
       ('Russian salad', 19.0, 3, '2023-06-20'),
       ('Pickled mushrooms', 12.0, 3, '2023-06-20'),
       ('Fish soup', 20.0, 3, '2023-06-21'),
       ('Beef with potatoes', 26.0, 3, '2023-06-21');

INSERT INTO VOTE (USER_ID, voting_date, voting_time, RESTAURANT_ID)
VALUES (1, '2023-06-20', '10:00', 2),
       (1, '2023-06-21', '15:00', 3),
       (3, '2023-06-20', '11:50', 1),
       (3, '2023-06-21', '12:00', 1),
       (1, now(), now(), 2);




DELETE FROM user_roles;
DELETE FROM users;
DELETE FROM meals;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password) VALUES
  ('User', 'user@yandex.ru', 'password'),
  ('Admin', 'admin@gmail.com', 'admin');

INSERT INTO user_roles (role, user_id) VALUES
  ('ROLE_USER', 100000),
  ('ROLE_ADMIN', 100001);
--             new Meal(LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500),
--             new Meal(LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000),
--             new Meal(LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500),
--             new Meal(LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000),
--             new Meal(LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500),
--             new Meal(LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510)

INSERT INTO MEALS(datetime,description,calories,userId) VALUES
('1999-01-08 10:05:06', 'Завтрак', 500,100000),
('1999-01-08 13:05:06', 'Обед', 1000,100000),
('1999-01-08 19:05:06', 'Ужин', 600,100000),
('1999-01-09 10:05:06', 'Завтрак', 400,100000),
('1999-01-09 13:05:06', 'Обед', 1000,100000),
('1999-01-09 19:05:06', 'Ужин', 500,100000),
('2012-01-08 10:05:06', 'Завтрак', 500,100001),
('2012-01-08 13:05:06', 'Обед', 1000,100001),
('2012-01-08 19:05:06', 'Ужин', 600,100001),
('2012-01-09 10:05:06', 'Завтрак', 400,100001),
('2012-01-09 13:05:06', 'Обед', 1000,100001),
('2012-01-09 19:05:06', 'Ужин', 500,100001);


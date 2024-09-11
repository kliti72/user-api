INSERT INTO users (name, surname, password, register_date, last_access) VALUES
('Alice', 'Rossi', 'password1', '2023-09-01', '2023-09-10'),
('Bob', 'Bianchi', 'password2', '2023-08-25', '2023-09-11'),
('Charlie', 'Verdi', 'password3', '2023-09-02', '2023-09-11'),
('Diana', 'Neri', 'password4', '2023-09-03', '2023-09-10'),
('Eva', 'Blu', 'password5', '2023-09-04', '2023-09-11');

INSERT INTO roles (name, assign_role_by, assigned_by_user_id) VALUES
('ADMIN', 'Sistema', 1),
('USER', 'Sistema', 1),
('MODERATOR', 'Sistema', 2),
('EDITOR', 'Sistema', 3),
('VIEWER', 'Sistema', 4);

INSERT INTO users_role (users_id, roles_id) VALUES
(1, 1),
(2, 2),
(3, 3),
(4, 4),
(5, 5),
(2, 1),
(3, 2),
(4, 3),
(5, 2);


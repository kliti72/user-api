INSERT INTO roles (name, assign_role_by) VALUES
('ADMIN', 'Sistema'),
('USER', 'Sistema');

INSERT INTO users (name, surname, password, register_date, last_access, roles_id) VALUES
('Alice', 'Rossi', 'password1', '2023-09-01', '2023-09-10', 1),
('Bob', 'Bianchi', 'password2', '2023-08-25', '2023-09-11', 2),
('Charlie', 'Verdi', 'password3', '2023-09-02', '2023-09-11', 2),
('Diana', 'Neri', 'password4', '2023-09-03', '2023-09-10', 1),
('Eva', 'Blu', 'password5', '2023-09-04', '2023-09-11', 1);

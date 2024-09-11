INSERT INTO users (name, surname, password, register_date, last_access, role_id) VALUES
('Alice', 'Rossi', 'password1', '2023-09-01', '2023-09-10', 1),
('Bob', 'Bianchi', 'password2', '2023-08-25', '2023-09-11', 2),
('Charlie', 'Verdi', 'password3', '2023-09-02', '2023-09-11', 2),
('Diana', 'Neri', 'password4', '2023-09-03', '2023-09-10', 1),
('Eva', 'Blu', 'password5', '2023-09-04', '2023-09-11', 1);

INSERT INTO roles (name, assign_role_by, assigned_by_user_id) VALUES
('ADMIN', 'Sistema', 1),
('USER', 'Sistema', 1),



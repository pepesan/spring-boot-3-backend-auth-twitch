-- Insertar roles de ejemplo
INSERT INTO auth_roles (name) VALUES ('ROLE_ADMIN');
INSERT INTO auth_roles (name) VALUES ('ROLE_USER');

-- Insertar usuarios de ejemplo
INSERT INTO auth_users (username, email, password, active) VALUES ('administrador', 'admin@example.com', 'administrador', false);
INSERT INTO auth_users (username, email, password, active) VALUES ('usuario', 'usuario1@example.com', 'usuario', false);

-- Asignar roles a usuarios en la tabla de relación
INSERT INTO auth_user_roles (user_id, role_id) VALUES (1, 1); -- Asignar rol de administrador al usuario 1
INSERT INTO auth_user_roles (user_id, role_id) VALUES (2, 2); -- Asignar rol de usuario normal al usuario 2
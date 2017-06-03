-- Deleting all tables
-- DROP TABLE identity_address;
-- DROP TABLE address;
-- DROP TABLE identities;
-- DROP TABLE role_permission;
-- DROP TABLE permission;
-- DROP TABLE users;
-- DROP TABLE role;

-- add admin users
INSERT INTO  identities (firstname, lastname, email) VALUES ('Gervaise', 'ALINA','gervaisita@gmail.com');

-- insert roles
INSERT INTO  role (role_id, title) VALUES (1, 'ADMIN'),(2, 'USER');

-- add admin users
INSERT INTO  users (username, password,  identity_IDENTITY_ID, USER_ROLE) values('admin', 'admin', 1 , 1 );



-- insert permissions
INSERT INTO  permission (permission_id, description) VALUES (1, 'Manage users');

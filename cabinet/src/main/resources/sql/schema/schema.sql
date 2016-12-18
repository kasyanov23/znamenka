CREATE SCHEMA clients;

CREATE TABLE clients.users
(
  username   VARCHAR(50) PRIMARY KEY NOT NULL,
  password   VARCHAR(150)            NOT NULL,
  client_id BIGINT,
  name       VARCHAR(30),
  CONSTRAINT users_client_id_fkey FOREIGN KEY (client_id) REFERENCES common.clients (client_id)
);

CREATE TABLE clients.user_roles
(
  username VARCHAR(50) NOT NULL,
  role_id  BIGINT      NOT NULL DEFAULT 4,
  CONSTRAINT user_to_roles2 FOREIGN KEY (username) REFERENCES clients.users (username),
  CONSTRAINT user_to_roles1 FOREIGN KEY (role_id) REFERENCES common.roles (role_id)
);
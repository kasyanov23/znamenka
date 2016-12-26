BEGIN TRANSACTION;
CREATE SCHEMA common;
CREATE TABLE common.discounts
(
  discount_id     BIGINT PRIMARY KEY NOT NULL,
  discount_amount BIGINT             NOT NULL,
  CHECK (discount_amount > 0)
);

CREATE TABLE common.clients
(
  client_id  BIGSERIAL PRIMARY KEY NOT NULL,
  surname    VARCHAR(100)          NOT NULL,
  phone      CHAR(11)              NOT NULL UNIQUE,
  email      VARCHAR(100)          NULL,
  birth_date DATE,
  name       VARCHAR(100)          NOT NULL,
  male       BOOLEAN               NOT NULL,
  comment    TEXT,
  car_number VARCHAR(50)
);
CREATE INDEX clients_phone_ind
  ON common.clients USING BTREE (phone);

CREATE TABLE common.trainers
(
  trainer_id   BIGINT PRIMARY KEY NOT NULL,
  trainer_name VARCHAR(100)       NOT NULL
);

CREATE TABLE common.training_status
(
  status_id   BIGINT PRIMARY KEY NOT NULL,
  status_name VARCHAR(50)        NOT NULL
);

CREATE TABLE common.abon_type (
  id   INT PRIMARY KEY NOT NULL,
  type VARCHAR(30)     NOT NULL
);

CREATE TABLE common.products
(
  product_id     BIGINT                 NOT NULL PRIMARY KEY,
  product_name   CHARACTER VARYING(100) NOT NULL,
  price          DOUBLE PRECISION       NOT NULL,
  training_count INT,
  expire_days    INT,
  abon_type      INT REFERENCES common.abon_type (id),
  is_abon        BOOLEAN                NOT NULL DEFAULT FALSE,
  CHECK ((is_abon = TRUE AND training_count IS NOT NULL AND training_count > 0 AND abon_type IS NOT NULL AND
          (expire_days IS NULL OR products.expire_days > 0)) OR
         (is_abon = FALSE AND training_count IS NULL AND abon_type IS NULL)),
  CHECK (price > 0)
);
CREATE INDEX products_is_abon_ind
  ON common.products USING BTREE (is_abon);

CREATE TABLE common.roles
(
  role_id   BIGINT PRIMARY KEY NOT NULL,
  role_name VARCHAR(50)
);

CREATE TABLE common.clients_history (
  client_id   BIGINT       NOT NULL,
  surname     VARCHAR(100) NOT NULL,
  phone       VARCHAR(11)  NOT NULL,
  email       VARCHAR(100),
  birth_date  DATE,
  name        VARCHAR(100) NOT NULL,
  comment     TEXT,
  male        BOOLEAN,
  car_number  VARCHAR(10),
  change_date TIMESTAMP    NOT NULL,
  PRIMARY KEY (client_id, change_date)
);


CREATE TABLE common.duty_plan_type
(
  duty_plan_type_id BIGINT NOT NULL PRIMARY KEY,
  duty_plan_name    VARCHAR(50) NOT NULL
);

CREATE TABLE common.sms_logs (
  id      BIGSERIAL PRIMARY KEY NOT NULL,
  phone   CHAR(10)              NOT NULL,
  text    TEXT                  NOT NULL,
  status  TEXT                  NOT NULL,
  created TIMESTAMP             NOT NULL DEFAULT current_timestamp
);

CREATE OR REPLACE FUNCTION common.upd_client()
  RETURNS TRIGGER AS '
BEGIN
  INSERT INTO common.clients_history(client_id, surname, phone, email, birth_date, name, comment, male, car_number, change_date)
  VALUES (old.client_id, old.surname, old.phone, old.email, old.birth_date, old.name, old.comment, old.male, old.car_number, CURRENT_TIMESTAMP);
  RETURN new;
END;'
LANGUAGE plpgsql;

CREATE TRIGGER tr_upd_client
AFTER UPDATE OR DELETE ON common.clients
FOR EACH ROW EXECUTE PROCEDURE common.upd_client();

END TRANSACTION;
COMMIT;

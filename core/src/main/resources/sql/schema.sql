BEGIN TRANSACTION;
CREATE TABLE discounts
(
  discount_id     BIGINT PRIMARY KEY NOT NULL,
  discount_amount BIGINT             NOT NULL,
  CHECK (discount_amount > 0)
);
CREATE TABLE clients
(
  client_id  BIGSERIAL PRIMARY KEY NOT NULL,
  surname    VARCHAR(100)          NOT NULL,
  phone      CHAR(11)              NOT NULL UNIQUE,
  email      VARCHAR(100)          NULL,
  birth_date DATE,
  name       VARCHAR(100)          NOT NULL,
  male       BOOLEAN               NOT NULL,
  comment    TEXT,
  car_number VARCHAR(9)
);
CREATE INDEX clients_phone_ind
  ON clients USING BTREE (phone);

CREATE TABLE training_status
(
  status_id   BIGINT PRIMARY KEY NOT NULL,
  status_name VARCHAR(50)        NOT NULL
);
CREATE TABLE trainers
(
  trainer_id   BIGINT PRIMARY KEY NOT NULL,
  trainer_name VARCHAR(100)       NOT NULL
);

CREATE TABLE abon_type (
  id   INT PRIMARY KEY NOT NULL,
  type VARCHAR(30)     NOT NULL
);

CREATE TABLE products
(
  product_id     BIGINT                 NOT NULL PRIMARY KEY,
  product_name   CHARACTER VARYING(100) NOT NULL,
  price          DOUBLE PRECISION       NOT NULL,
  training_count INT,
  expire_days    INT,
  abon_type      INT REFERENCES abon_type (id),
  is_abon        BOOLEAN                NOT NULL DEFAULT FALSE,
  CHECK ((is_abon = TRUE AND training_count IS NOT NULL AND training_count > 0 AND abon_type IS NOT NULL AND
          (expire_days IS NULL OR products.expire_days > 0)) OR
         (is_abon = FALSE AND training_count IS NULL AND abon_type IS NULL)),
  CHECK (price > 0)
);
CREATE INDEX products_is_abon_ind
  ON products USING BTREE (is_abon);

CREATE TABLE purchase
(
  purchase_id   BIGSERIAL PRIMARY KEY NOT NULL,
  is_provided   BOOLEAN DEFAULT FALSE,
  client_id     BIGINT                NOT NULL,
  purchase_date DATE                  NOT NULL,
  product_id    BIGINT                NOT NULL,
  trainer_id    BIGINT                NOT NULL,
  discount_id   BIGINT,
  CONSTRAINT purchase_clients_client_id_fk FOREIGN KEY (client_id) REFERENCES clients (client_id),
  CONSTRAINT purchase_trainers_trainer_id_fk FOREIGN KEY (trainer_id) REFERENCES trainers (trainer_id),
  CONSTRAINT purchase_discount_discount_id_fk FOREIGN KEY (discount_id) REFERENCES discounts (discount_id),
  CONSTRAINT purchase_product_product_id_fk FOREIGN KEY (product_id) REFERENCES products (product_id)
);
CREATE INDEX purchase_client_id_ind
  ON purchase USING BTREE (client_id);

CREATE TABLE users
(
  username   VARCHAR(50) PRIMARY KEY NOT NULL,
  password   VARCHAR(150)            NOT NULL,
  trainer_id BIGINT,
  name       VARCHAR(30),
  CONSTRAINT users_trainer_id_fkey FOREIGN KEY (trainer_id) REFERENCES trainers (trainer_id)
);
CREATE TABLE roles
(
  role_id   BIGINT PRIMARY KEY NOT NULL,
  role_name VARCHAR(50)
);
CREATE TABLE user_roles
(
  username VARCHAR(50) NOT NULL,
  role_id  BIGINT      NOT NULL,
  CONSTRAINT user_to_roles2 FOREIGN KEY (username) REFERENCES users (username),
  CONSTRAINT user_to_roles1 FOREIGN KEY (role_id) REFERENCES roles (role_id)
);
CREATE TABLE payments
(
  payment_id     BIGSERIAL PRIMARY KEY NOT NULL,
  payment_amount BIGINT                NOT NULL,
  payment_date   TIMESTAMP             NOT NULL,
  purchase_id    BIGINT,
  CONSTRAINT payments_purchase_purchase_id_fk FOREIGN KEY (purchase_id) REFERENCES purchase (purchase_id),
  CHECK (payment_amount > 0)
);
CREATE INDEX payments_purchase_id_ind
  ON payments USING BTREE (purchase_id);

CREATE TABLE trainings
(
  training_id   BIGSERIAL PRIMARY KEY NOT NULL,
  client_id     BIGINT                NOT NULL,
  trainer_id    BIGINT                NOT NULL,
  purchase_id   BIGINT,
  start         TIMESTAMP             NOT NULL,
  status_id     BIGINT                NOT NULL,
  comment       TEXT,
  pass_for_auto BOOLEAN,
  CONSTRAINT trainings_ibfk_1 FOREIGN KEY (client_id) REFERENCES clients (client_id),
  CONSTRAINT trainings_trainers_trainer_id_fk FOREIGN KEY (trainer_id) REFERENCES trainers (trainer_id),
  CONSTRAINT trainings_purchase_purchase_id_fk FOREIGN KEY (purchase_id) REFERENCES purchase (purchase_id),
  CONSTRAINT trainings_status_id_fkey FOREIGN KEY (status_id) REFERENCES training_status (status_id)
);

CREATE INDEX training_client_id_ind ON trainings USING BTREE (client_id);
CREATE INDEX training_trainer_id_ind ON trainings USING BTREE (trainer_id);

CREATE TABLE user_action_log
(
  id          BIGSERIAL PRIMARY KEY NOT NULL,
  currenttime TIMESTAMP             NOT NULL,
  username    VARCHAR(30)           NOT NULL,
  action      TEXT                  NOT NULL,
  success     BOOLEAN               NOT NULL,
  message     TEXT
);

CREATE TABLE clients_abonements (
  id             BIGSERIAL PRIMARY KEY NOT NULL,
  product_id     BIGINT                NOT NULL REFERENCES products (product_id),
  client_id      BIGINT                NOT NULL REFERENCES clients (client_id),
  purchase_id    BIGINT                NOT NULL REFERENCES purchase (purchase_id),
  training_count INT                   NOT NULL,
  CHECK (training_count >= 0)
);
CREATE INDEX clients_abonements_field_ind ON clients_abonements USING BTREE (product_id, client_id, purchase_id);
CREATE INDEX clients_abonements_client_id_ind ON clients_abonements USING BTREE (client_id);

CREATE TABLE clients_history (
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

CREATE TABLE duty_plan_type
(
  duty_plan_type_id BIGINT NOT NULL PRIMARY KEY,
  duty_plan_name    VARCHAR(50) NOT NULL
);

CREATE TABLE duty_schedule
(
  duty_id        BIGSERIAL PRIMARY KEY NOT NULL,
  trainer_id     BIGINT                NOT NULL REFERENCES trainers (trainer_id),
  planned_start  TIMESTAMP             NOT NULL,
  planned_end    TIMESTAMP             NOT NULL,
  fact_start     TIMESTAMP            ,
  fact_end       TIMESTAMP             ,
  duty_plan_type BIGINT                NOT NULL REFERENCES duty_plan_type (duty_plan_type_id),
  CHECK (planned_start < planned_end),
  CHECK (fact_start < fact_end)
);
CREATE INDEX duty_schedule_trainer_ind ON duty_schedule USING BTREE (trainer_id);
END TRANSACTION;
COMMIT;

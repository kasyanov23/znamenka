DROP TABLE IF EXISTS Jf_trainings;
DROP TABLE IF EXISTS JF_Clients;
DROP TABLE IF EXISTS JF_payments;
DROP TABLE IF EXISTS JF_Trainers;
DROP TABLE IF EXISTS JF_products;
DROP TABLE IF EXISTS JF_purchase;
DROP TABLE IF EXISTS JF_discounts;
DROP INDEX IF EXISTS JF_purchase_fk3;
DROP INDEX IF EXISTS JF_payments_fk0;


CREATE TABLE JF_Trainers (
  trainer_id   BIGINT GENERATED BY DEFAULT AS IDENTITY (
  START WITH 1 )            NOT NULL PRIMARY KEY,
  trainer_name VARCHAR(100) NOT NULL
);

CREATE TABLE JF_clients
(
  client_id  BIGINT GENERATED BY DEFAULT AS IDENTITY (
  START WITH 1 ) PRIMARY KEY NOT NULL,
  surname    VARCHAR(100)    NOT NULL,
  phone      INT             NOT NULL,
  email      VARCHAR(100)    NOT NULL,
  birth_date DATE,
  name       VARCHAR(100)    NOT NULL
);

CREATE TABLE JF_products
(
  product_id   BIGINT GENERATED BY DEFAULT AS IDENTITY (
  START WITH 1 ) PRIMARY KEY NOT NULL,
  product_name VARCHAR(100)  NOT NULL,
  expire_days  INT           NOT NULL,
  price        DOUBLE        NOT NULL
);


CREATE TABLE JF_discounts
(
  discount_id     BIGINT GENERATED BY DEFAULT AS IDENTITY (
  START WITH 1 ) PRIMARY KEY NOT NULL,
  discount_amount BIGINT     NOT NULL
);

CREATE TABLE JF_purchase
(
  purchase_id   BIGINT GENERATED BY DEFAULT AS IDENTITY (
  START WITH 1 )  PRIMARY KEY NOT NULL,
  is_provided   TINYINT       NOT NULL,
  client_id     BIGINT        NOT NULL,
  purchase_date DATE          NOT NULL,
  product_id    BIGINT        NOT NULL,
  trainer_id    BIGINT        NOT NULL,
  expired       TINYINT       NOT NULL,
  discount_id   BIGINT,
  CONSTRAINT JF_purchase_fk3 FOREIGN KEY (discount_id) REFERENCES JF_discounts (discount_id)
);
CREATE INDEX JF_purchase_fk3
  ON JF_purchase (discount_id);

CREATE TABLE JF_payments
(
  payment_id     BIGINT GENERATED BY DEFAULT AS IDENTITY (
  START WITH 1 )  PRIMARY KEY NOT NULL,
  payment_amount BIGINT       NOT NULL,
  payment_date   DATETIME     NOT NULL,
  purchase_id    BIGINT,
  CONSTRAINT JF_payments_fk0 FOREIGN KEY (purchase_id) REFERENCES JF_purchase (purchase_id)
);
CREATE INDEX JF_payments_fk0
  ON JF_payments (purchase_id);

CREATE TABLE JF_trainings
(
  training_id   BIGINT GENERATED BY DEFAULT AS IDENTITY (
  START WITH 1 ) PRIMARY KEY NOT NULL,
  training_plan BIGINT,
  client_id     BIGINT       NOT NULL,
  trainer_id    BIGINT       NOT NULL,
  purchase_id   BIGINT,
  CONSTRAINT JF_trainings_ibfk_1 FOREIGN KEY (client_id) REFERENCES JF_clients (client_id)
);

CREATE TABLE JF_training_plan
(
  id_training_plan   BIGINT PRIMARY KEY NOT NULL,
  repetitions_amount INT                NOT NULL,
  exercise_id        BIGINT             NOT NULL
);

CREATE TABLE JF_exercises
(
  exercise_id BIGINT GENERATED BY DEFAULT AS IDENTITY (
  START WITH 1 )  PRIMARY KEY NOT NULL,
  description VARCHAR(100)    NOT NULL,
  name        VARCHAR(100)    NOT NULL
);

CREATE TABLE JF_duty_plan_type
(
  duty_plan_type_id BIGINT NOT NULL,
  duty_plan_name    BIGINT NOT NULL
);

CREATE TABLE JF_duty_shedule
(
  duty_id        BIGINT GENERATED BY DEFAULT AS IDENTITY (
  START WITH 1 ) PRIMARY KEY NOT NULL,
  trainer_id     BIGINT      NOT NULL,
  datetime_start DATETIME    NOT NULL,
  datetime_end   DATETIME    NOT NULL,
  duty_plan_type TINYINT     NOT NULL
);


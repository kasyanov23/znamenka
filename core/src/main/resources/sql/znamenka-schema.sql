BEGIN TRANSACTION;
CREATE SCHEMA znamenka;
CREATE TABLE znamenka.purchase
(
  purchase_id   BIGSERIAL PRIMARY KEY NOT NULL,
  is_provided   BOOLEAN DEFAULT FALSE,
  client_id     BIGINT                NOT NULL,
  purchase_date DATE                  NOT NULL,
  product_id    BIGINT                NOT NULL,
  trainer_id    BIGINT                NOT NULL,
  discount_id   BIGINT,
  CONSTRAINT purchase_clients_client_id_fk FOREIGN KEY (client_id) REFERENCES common.clients (client_id),
  CONSTRAINT purchase_trainers_trainer_id_fk FOREIGN KEY (trainer_id) REFERENCES common.trainers (trainer_id),
  CONSTRAINT purchase_discount_discount_id_fk FOREIGN KEY (discount_id) REFERENCES common.discounts (discount_id),
  CONSTRAINT purchase_product_product_id_fk FOREIGN KEY (product_id) REFERENCES common.products (product_id)
);
CREATE INDEX purchase_client_id_ind
  ON znamenka.purchase USING BTREE (client_id);

CREATE TABLE znamenka.users
(
  username   VARCHAR(50) PRIMARY KEY NOT NULL,
  password   VARCHAR(150)            NOT NULL,
  trainer_id BIGINT,
  name       VARCHAR(30),
  CONSTRAINT users_trainer_id_fkey FOREIGN KEY (trainer_id) REFERENCES common.trainers (trainer_id)
);

CREATE TABLE znamenka.user_roles
(
  username VARCHAR(50) NOT NULL,
  role_id  BIGINT      NOT NULL,
  CONSTRAINT user_to_roles2 FOREIGN KEY (username) REFERENCES znamenka.users (username),
  CONSTRAINT user_to_roles1 FOREIGN KEY (role_id) REFERENCES common.roles (role_id)
);

CREATE TABLE znamenka.payments
(
  payment_id     BIGSERIAL PRIMARY KEY NOT NULL,
  payment_amount BIGINT                NOT NULL,
  payment_date   TIMESTAMP             NOT NULL,
  purchase_id    BIGINT,
  CONSTRAINT payments_purchase_purchase_id_fk FOREIGN KEY (purchase_id) REFERENCES znamenka.purchase (purchase_id),
  CHECK (payment_amount > 0)
);
CREATE INDEX payments_purchase_id_ind
  ON znamenka.payments USING BTREE (purchase_id);

CREATE TABLE znamenka.trainings
(
  training_id   BIGSERIAL PRIMARY KEY NOT NULL,
  client_id     BIGINT                NOT NULL,
  trainer_id    BIGINT                NOT NULL,
  purchase_id   BIGINT,
  start         TIMESTAMP             NOT NULL,
  status_id     BIGINT                NOT NULL,
  comment       TEXT,
  pass_for_auto BOOLEAN,
  CONSTRAINT trainings_ibfk_1 FOREIGN KEY (client_id) REFERENCES common.clients (client_id),
  CONSTRAINT trainings_trainers_trainer_id_fk FOREIGN KEY (trainer_id) REFERENCES common.trainers (trainer_id),
  CONSTRAINT trainings_purchase_purchase_id_fk FOREIGN KEY (purchase_id) REFERENCES znamenka.purchase (purchase_id),
  CONSTRAINT trainings_status_id_fkey FOREIGN KEY (status_id) REFERENCES common.training_status (status_id)
);

CREATE INDEX training_client_id_ind ON znamenka.trainings USING BTREE (client_id);
CREATE INDEX training_trainer_id_ind ON znamenka.trainings USING BTREE (trainer_id);

CREATE TABLE znamenka.user_action_log
(
  id          BIGSERIAL PRIMARY KEY NOT NULL,
  currenttime TIMESTAMP             NOT NULL,
  username    VARCHAR(30)           NOT NULL,
  action      TEXT                  NOT NULL,
  success     BOOLEAN               NOT NULL,
  message     TEXT
);

CREATE TABLE znamenka.clients_abonements (
  id             BIGSERIAL PRIMARY KEY NOT NULL,
  product_id     BIGINT                NOT NULL REFERENCES common.products (product_id),
  client_id      BIGINT                NOT NULL REFERENCES common.clients (client_id),
  purchase_id    BIGINT                NOT NULL REFERENCES znamenka.purchase (purchase_id),
  training_count INT                   NOT NULL,
  CHECK (training_count >= 0)
);
CREATE INDEX clients_abonements_field_ind ON znamenka.clients_abonements USING BTREE (product_id, client_id, purchase_id);
CREATE INDEX clients_abonements_client_id_ind ON znamenka.clients_abonements USING BTREE (client_id);

CREATE TABLE znamenka.duty_schedule
(
  duty_id        BIGSERIAL PRIMARY KEY NOT NULL,
  trainer_id     BIGINT                NOT NULL REFERENCES common.trainers (trainer_id),
  planned_start  TIMESTAMP             NOT NULL,
  planned_end    TIMESTAMP             NOT NULL,
  fact_start     TIMESTAMP            ,
  fact_end       TIMESTAMP             ,
  duty_plan_type BIGINT                NOT NULL REFERENCES common.duty_plan_type (duty_plan_type_id),
  CHECK (planned_start < planned_end),
  CHECK (fact_start < fact_end)
);
CREATE INDEX duty_schedule_trainer_ind ON znamenka.duty_schedule USING BTREE (trainer_id);

CREATE OR REPLACE FUNCTION znamenka.ins_abon_for_client_after_purchase()
  RETURNS TRIGGER AS '
DECLARE t_count INT;
BEGIN
  IF (SELECT is_abon
      FROM common.products a
      WHERE a.product_id = new.product_id)
  THEN t_count := (SELECT training_count
                   FROM common.products a
                   WHERE a.product_id = new.product_id);
    INSERT INTO znamenka.clients_abonements (product_id, client_id, training_count, purchase_id)
    VALUES (new.product_id, new.client_id, t_count, new.purchase_id);
  END IF;
  RETURN new;
END;'
LANGUAGE plpgsql;

CREATE TRIGGER tr_ins_abon_for_client
AFTER INSERT ON znamenka.purchase
FOR EACH ROW EXECUTE PROCEDURE znamenka.ins_abon_for_client_after_purchase();

CREATE OR REPLACE FUNCTION znamenka.decrement_training_for_client_abon()
  RETURNS TRIGGER AS '
DECLARE prod_id BIGINT;
        ca_id   BIGINT;
        t_count INT;
BEGIN
  prod_id := (SELECT min(product_id)
              FROM znamenka.purchase p
              WHERE p.purchase_id = old.purchase_id);
  ca_id:= (SELECT min(id)
           FROM znamenka.clients_abonements ca
           WHERE ca.client_id = old.client_id AND ca.product_id = prod_id AND ca.purchase_id = new.purchase_id);
  t_count:=(SELECT training_count
            FROM znamenka.clients_abonements
            WHERE id = ca_id);
  IF new.status_id IN (2, 4) AND t_count > 0 AND old.status_id IN (1, 3)
  THEN
    UPDATE znamenka.clients_abonements
    SET training_count = training_count - 1
    WHERE id = ca_id;
  END IF;
  IF old.status_id IN (2, 4)
  THEN RETURN old;
  END IF;
  RETURN new;
END;'
LANGUAGE plpgsql;

CREATE TRIGGER tr_dec_abon_for_client
AFTER UPDATE ON znamenka.trainings
FOR EACH ROW EXECUTE PROCEDURE znamenka.decrement_training_for_client_abon();

END TRANSACTION;
COMMIT;

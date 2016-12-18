BEGIN TRANSACTION;

INSERT INTO znamenka.purchase (is_provided, client_id, purchase_date, product_id, trainer_id, discount_id)
VALUES (NULL, 31, '2016-10-16', 1, 2, 1);
INSERT INTO znamenka.purchase (is_provided, client_id, purchase_date, product_id, trainer_id, discount_id)
VALUES (NULL, 31, '2016-10-16', 10, 1, 1);
INSERT INTO znamenka.purchase (is_provided, client_id, purchase_date, product_id, trainer_id, discount_id)
VALUES (NULL, 31, '2016-10-16', 8, 2, NULL);
INSERT INTO znamenka.purchase (is_provided, client_id, purchase_date, product_id, trainer_id, discount_id)
VALUES (NULL, 17, '2016-10-17', 13, 3, NULL);
INSERT INTO znamenka.purchase (is_provided, client_id, purchase_date, product_id, trainer_id, discount_id)
VALUES (NULL, 23, '2016-10-17', 13, 3, NULL);
INSERT INTO znamenka.purchase (is_provided, client_id, purchase_date, product_id, trainer_id, discount_id)
VALUES (NULL, 30, '2016-10-17', 11, 3, NULL);
INSERT INTO znamenka.purchase (is_provided, client_id, purchase_date, product_id, trainer_id, discount_id)
VALUES (NULL, 27, '2016-10-17', 11, 3, NULL);
INSERT INTO znamenka.purchase (is_provided, client_id, purchase_date, product_id, trainer_id, discount_id)
VALUES (NULL, 37, '2016-10-17', 11, 3, NULL);
INSERT INTO znamenka.purchase (is_provided, client_id, purchase_date, product_id, trainer_id, discount_id)
VALUES (NULL, 38, '2016-10-17', 10, 2, NULL);
INSERT INTO znamenka.purchase (is_provided, client_id, purchase_date, product_id, trainer_id, discount_id)
VALUES (NULL, 20, '2016-10-17', 13, 3, NULL);
INSERT INTO znamenka.purchase (is_provided, client_id, purchase_date, product_id, trainer_id, discount_id)
VALUES (NULL, 16, '2016-10-19', 11, 3, NULL);
INSERT INTO znamenka.purchase (is_provided, client_id, purchase_date, product_id, trainer_id, discount_id)
VALUES (NULL, 41, '2016-10-19', 11, 3, NULL);
INSERT INTO znamenka.purchase (is_provided, client_id, purchase_date, product_id, trainer_id, discount_id)
VALUES (NULL, 45, '2016-11-02', 11, 3, NULL);
INSERT INTO znamenka.purchase (is_provided, client_id, purchase_date, product_id, trainer_id, discount_id)
VALUES (NULL, 46, '2016-11-02', 11, 3, NULL);
INSERT INTO znamenka.purchase (is_provided, client_id, purchase_date, product_id, trainer_id, discount_id)
VALUES (NULL, 24, '2016-11-02', 11, 3, 1);
INSERT INTO znamenka.purchase (is_provided, client_id, purchase_date, product_id, trainer_id, discount_id)
VALUES (NULL, 27, '2016-11-02', 1, 2, 1);
INSERT INTO znamenka.purchase (is_provided, client_id, purchase_date, product_id, trainer_id, discount_id)
VALUES (NULL, 21, '2016-11-02', 13, 3, NULL);
INSERT INTO znamenka.purchase (is_provided, client_id, purchase_date, product_id, trainer_id, discount_id)
VALUES (NULL, 49, '2016-11-02', 1, 2, 3);
INSERT INTO znamenka.purchase (is_provided, client_id, purchase_date, product_id, trainer_id, discount_id)
VALUES (NULL, 26, '2016-11-02', 13, 3, NULL);
INSERT INTO znamenka.purchase (is_provided, client_id, purchase_date, product_id, trainer_id, discount_id)
VALUES (NULL, 44, '2016-11-02', 1, 3, NULL);
INSERT INTO znamenka.purchase (is_provided, client_id, purchase_date, product_id, trainer_id, discount_id)
VALUES (NULL, 40, '2016-11-02', 13, 3, NULL);
INSERT INTO znamenka.purchase (is_provided, client_id, purchase_date, product_id, trainer_id, discount_id)
VALUES (NULL, 51, '2016-11-02', 10, 3, NULL);

INSERT INTO znamenka.clients_abonements (product_id, client_id, purchase_id, training_count) VALUES (1, 31, 1, 8);
INSERT INTO znamenka.clients_abonements (product_id, client_id, purchase_id, training_count) VALUES (10, 31, 2, 1);
INSERT INTO znamenka.clients_abonements (product_id, client_id, purchase_id, training_count) VALUES (8, 31, 3, 48);
INSERT INTO znamenka.clients_abonements (product_id, client_id, purchase_id, training_count) VALUES (13, 17, 4, 50);
INSERT INTO znamenka.clients_abonements (product_id, client_id, purchase_id, training_count) VALUES (13, 23, 5, 50);
INSERT INTO znamenka.clients_abonements (product_id, client_id, purchase_id, training_count) VALUES (11, 30, 6, 10);
INSERT INTO znamenka.clients_abonements (product_id, client_id, purchase_id, training_count) VALUES (11, 37, 8, 10);
INSERT INTO znamenka.clients_abonements (product_id, client_id, purchase_id, training_count) VALUES (10, 38, 9, 1);
INSERT INTO znamenka.clients_abonements (product_id, client_id, purchase_id, training_count) VALUES (13, 20, 10, 50);
INSERT INTO znamenka.clients_abonements (product_id, client_id, purchase_id, training_count) VALUES (11, 16, 11, 10);
INSERT INTO znamenka.clients_abonements (product_id, client_id, purchase_id, training_count) VALUES (11, 27, 7, 9);
INSERT INTO znamenka.clients_abonements (product_id, client_id, purchase_id, training_count) VALUES (11, 41, 12, 10);
INSERT INTO znamenka.clients_abonements (product_id, client_id, purchase_id, training_count) VALUES (11, 45, 13, 10);
INSERT INTO znamenka.clients_abonements (product_id, client_id, purchase_id, training_count) VALUES (11, 46, 14, 10);
INSERT INTO znamenka.clients_abonements (product_id, client_id, purchase_id, training_count) VALUES (11, 24, 15, 10);
INSERT INTO znamenka.clients_abonements (product_id, client_id, purchase_id, training_count) VALUES (1, 27, 16, 8);
INSERT INTO znamenka.clients_abonements (product_id, client_id, purchase_id, training_count) VALUES (13, 21, 17, 50);
INSERT INTO znamenka.clients_abonements (product_id, client_id, purchase_id, training_count) VALUES (1, 49, 18, 8);
INSERT INTO znamenka.clients_abonements (product_id, client_id, purchase_id, training_count) VALUES (13, 26, 19, 50);
INSERT INTO znamenka.clients_abonements (product_id, client_id, purchase_id, training_count) VALUES (1, 44, 20, 8);
INSERT INTO znamenka.clients_abonements (product_id, client_id, purchase_id, training_count) VALUES (13, 40, 21, 50);
INSERT INTO znamenka.clients_abonements (product_id, client_id, purchase_id, training_count) VALUES (10, 51, 22, 1);

INSERT INTO znamenka.payments (payment_amount, payment_date, purchase_id) VALUES (123, '2016-10-16 18:05:11.938000', 1);
INSERT INTO znamenka.payments (payment_amount, payment_date, purchase_id)
VALUES (3122, '2016-10-19 16:27:41.754000', 12);

INSERT INTO znamenka.trainings (client_id, trainer_id, purchase_id, start, status_id, comment, pass_for_auto)
VALUES (31, 1, 1, '2016-10-05 08:30:00.000000', 4, 'ффв', NULL);
INSERT INTO znamenka.trainings (client_id, trainer_id, purchase_id, start, status_id, comment, pass_for_auto)
VALUES (31, 2, 1, '2016-10-05 10:00:00.000000', 4, '', NULL);
INSERT INTO znamenka.trainings (client_id, trainer_id, purchase_id, start, status_id, comment, pass_for_auto)
VALUES (31, 2, 2, '2016-10-05 12:00:00.000000', 4, '', NULL);
INSERT INTO znamenka.trainings (client_id, trainer_id, purchase_id, start, status_id, comment, pass_for_auto)
VALUES (31, 2, 2, '2016-10-05 01:00:00.000000', 2, '', FALSE);
INSERT INTO znamenka.trainings (client_id, trainer_id, purchase_id, start, status_id, comment, pass_for_auto)
VALUES (31, 2, 2, '2016-10-05 01:00:00.000000', 2, '', FALSE);
INSERT INTO znamenka.trainings (client_id, trainer_id, purchase_id, start, status_id, comment, pass_for_auto)
VALUES (31, 2, 2, '2016-10-10 07:00:00.000000', 4, 'dsas', TRUE);
INSERT INTO znamenka.trainings (client_id, trainer_id, purchase_id, start, status_id, comment, pass_for_auto)
VALUES (31, 1, 2, '2016-10-11 07:00:00.000000', 1, '', FALSE);
INSERT INTO znamenka.trainings (client_id, trainer_id, purchase_id, start, status_id, comment, pass_for_auto)
VALUES (31, 2, 2, '2016-10-12 07:00:00.000000', 2, '', FALSE);
INSERT INTO znamenka.trainings (client_id, trainer_id, purchase_id, start, status_id, comment, pass_for_auto)
VALUES (31, 2, 2, '2016-10-13 07:00:00.000000', 2, '', FALSE);
INSERT INTO znamenka.trainings (client_id, trainer_id, purchase_id, start, status_id, comment, pass_for_auto)
VALUES (23, 2, 5, '2016-10-18 09:00:00.000000', 1, '', TRUE);
INSERT INTO znamenka.trainings (client_id, trainer_id, purchase_id, start, status_id, comment, pass_for_auto)
VALUES (30, 2, 6, '2016-10-18 10:00:00.000000', 1, '', TRUE);
INSERT INTO znamenka.trainings (client_id, trainer_id, purchase_id, start, status_id, comment, pass_for_auto)
VALUES (27, 2, 7, '2016-10-18 01:00:00.000000', 1, '', NULL);
INSERT INTO znamenka.trainings (client_id, trainer_id, purchase_id, start, status_id, comment, pass_for_auto)
VALUES (27, 2, 7, '2016-10-18 01:00:00.000000', 1, '', NULL);
INSERT INTO znamenka.trainings (client_id, trainer_id, purchase_id, start, status_id, comment, pass_for_auto)
VALUES (27, 2, 7, '2016-10-18 01:00:00.000000', 1, '', NULL);
INSERT INTO znamenka.trainings (client_id, trainer_id, purchase_id, start, status_id, comment, pass_for_auto)
VALUES (37, 2, 8, '2016-10-18 03:00:00.000000', 1, '', TRUE);
INSERT INTO znamenka.trainings (client_id, trainer_id, purchase_id, start, status_id, comment, pass_for_auto)
VALUES (27, 2, 7, '2016-10-18 01:00:00.000000', 1, '', NULL);
INSERT INTO znamenka.trainings (client_id, trainer_id, purchase_id, start, status_id, comment, pass_for_auto)
VALUES (27, 2, 7, '2016-10-19 10:30:00.000000', 1, '', NULL);
INSERT INTO znamenka.trainings (client_id, trainer_id, purchase_id, start, status_id, comment, pass_for_auto)
VALUES (27, 2, 7, '2016-10-18 12:30:00.000000', 1, '', NULL);
INSERT INTO znamenka.trainings (client_id, trainer_id, purchase_id, start, status_id, comment, pass_for_auto)
VALUES (27, 2, 7, '2016-10-18 01:00:00.000000', 1, '', NULL);
INSERT INTO znamenka.trainings (client_id, trainer_id, purchase_id, start, status_id, comment, pass_for_auto)
VALUES (27, 2, 7, '2016-10-20 01:00:00.000000', 1, '', NULL);
INSERT INTO znamenka.trainings (client_id, trainer_id, purchase_id, start, status_id, comment, pass_for_auto)
VALUES (27, 2, 7, '2016-10-20 06:00:00.000000', 1, '', NULL);
INSERT INTO znamenka.trainings (client_id, trainer_id, purchase_id, start, status_id, comment, pass_for_auto)
VALUES (27, 1, 7, '2016-10-19 02:30:00.000000', 1, '', NULL);
INSERT INTO znamenka.trainings (client_id, trainer_id, purchase_id, start, status_id, comment, pass_for_auto)
VALUES (16, 3, 11, '2016-10-20 14:30:00.000000', 1, '', TRUE);
INSERT INTO znamenka.trainings (client_id, trainer_id, purchase_id, start, status_id, comment, pass_for_auto)
VALUES (16, 3, 11, '2016-10-19 15:00:00.000000', 1, '', FALSE);
INSERT INTO znamenka.trainings (client_id, trainer_id, purchase_id, start, status_id, comment, pass_for_auto)
VALUES (27, 3, 7, '2016-10-18 10:30:00.000000', 2, '', FALSE);
INSERT INTO znamenka.trainings (client_id, trainer_id, purchase_id, start, status_id, comment, pass_for_auto)
VALUES (31, 3, 1, '2016-10-18 14:00:00.000000', 3, 'заболел', TRUE);
INSERT INTO znamenka.trainings (client_id, trainer_id, purchase_id, start, status_id, comment, pass_for_auto)
VALUES (30, 3, 6, '2016-11-04 11:00:00.000000', 1, '', TRUE);
INSERT INTO znamenka.trainings (client_id, trainer_id, purchase_id, start, status_id, comment, pass_for_auto)
VALUES (24, 3, 15, '2016-11-04 12:00:00.000000', 1, '', FALSE);
INSERT INTO znamenka.trainings (client_id, trainer_id, purchase_id, start, status_id, comment, pass_for_auto)
VALUES (45, 2, 13, '2016-11-05 10:00:00.000000', 1, '', FALSE);
INSERT INTO znamenka.trainings (client_id, trainer_id, purchase_id, start, status_id, comment, pass_for_auto)
VALUES (23, 2, 5, '2016-11-05 12:00:00.000000', 1, '', TRUE);
INSERT INTO znamenka.trainings (client_id, trainer_id, purchase_id, start, status_id, comment, pass_for_auto)
VALUES (27, 2, 16, '2016-11-05 13:00:00.000000', 1, '', FALSE);
INSERT INTO znamenka.trainings (client_id, trainer_id, purchase_id, start, status_id, comment, pass_for_auto)
VALUES (20, 2, 10, '2016-11-05 15:00:00.000000', 1, '', FALSE);
INSERT INTO znamenka.trainings (client_id, trainer_id, purchase_id, start, status_id, comment, pass_for_auto)
VALUES (49, 2, 18, '2016-11-05 14:00:00.000000', 1, '', FALSE);
INSERT INTO znamenka.trainings (client_id, trainer_id, purchase_id, start, status_id, comment, pass_for_auto)
VALUES (17, 3, 4, '2016-11-02 07:30:00.000000', 1, '', FALSE);
INSERT INTO znamenka.trainings (client_id, trainer_id, purchase_id, start, status_id, comment, pass_for_auto)
VALUES (46, 3, 14, '2016-11-02 09:00:00.000000', 1, '', FALSE);
INSERT INTO znamenka.trainings (client_id, trainer_id, purchase_id, start, status_id, comment, pass_for_auto)
VALUES (30, 3, 6, '2016-11-02 11:00:00.000000', 1, '', FALSE);
INSERT INTO znamenka.trainings (client_id, trainer_id, purchase_id, start, status_id, comment, pass_for_auto)
VALUES (45, 3, 13, '2016-11-02 07:30:00.000000', 1, '', FALSE);
INSERT INTO znamenka.trainings (client_id, trainer_id, purchase_id, start, status_id, comment, pass_for_auto)
VALUES (21, 3, 17, '2016-11-02 12:00:00.000000', 1, '', FALSE);
INSERT INTO znamenka.trainings (client_id, trainer_id, purchase_id, start, status_id, comment, pass_for_auto)
VALUES (24, 3, 15, '2016-11-02 13:00:00.000000', 1, '', FALSE);
INSERT INTO znamenka.trainings (client_id, trainer_id, purchase_id, start, status_id, comment, pass_for_auto)
VALUES (46, 3, 14, '2016-11-07 09:00:00.000000', 1, '', FALSE);
INSERT INTO znamenka.trainings (client_id, trainer_id, purchase_id, start, status_id, comment, pass_for_auto)
VALUES (26, 3, 19, '2016-11-07 10:30:00.000000', 1, '', FALSE);
INSERT INTO znamenka.trainings (client_id, trainer_id, purchase_id, start, status_id, comment, pass_for_auto)
VALUES (44, 3, 20, '2016-11-07 12:00:00.000000', 1, '', FALSE);
INSERT INTO znamenka.trainings (client_id, trainer_id, purchase_id, start, status_id, comment, pass_for_auto)
VALUES (21, 3, 17, '2016-11-07 17:30:00.000000', 1, '', FALSE);
INSERT INTO znamenka.trainings (client_id, trainer_id, purchase_id, start, status_id, comment, pass_for_auto)
VALUES (21, 2, 17, '2016-11-08 17:30:00.000000', 1, '', FALSE);
INSERT INTO znamenka.trainings (client_id, trainer_id, purchase_id, start, status_id, comment, pass_for_auto)
VALUES (21, 3, 17, '2016-11-09 17:30:00.000000', 1, '', FALSE);
INSERT INTO znamenka.trainings (client_id, trainer_id, purchase_id, start, status_id, comment, pass_for_auto)
VALUES (21, 2, 17, '2016-11-10 17:30:00.000000', 1, '', FALSE);
INSERT INTO znamenka.trainings (client_id, trainer_id, purchase_id, start, status_id, comment, pass_for_auto)
VALUES (21, 3, 17, '2016-11-11 17:30:00.000000', 1, '', FALSE);
INSERT INTO znamenka.trainings (client_id, trainer_id, purchase_id, start, status_id, comment, pass_for_auto)
VALUES (46, 3, 14, '2016-11-09 09:00:00.000000', 1, '', FALSE);
INSERT INTO znamenka.trainings (client_id, trainer_id, purchase_id, start, status_id, comment, pass_for_auto)
VALUES (44, 2, 20, '2016-11-11 12:00:00.000000', 1, '', FALSE);
INSERT INTO znamenka.trainings (client_id, trainer_id, purchase_id, start, status_id, comment, pass_for_auto)
VALUES (26, 3, 19, '2016-11-11 11:00:00.000000', 1, '', FALSE);
INSERT INTO znamenka.trainings (client_id, trainer_id, purchase_id, start, status_id, comment, pass_for_auto)
VALUES (46, 3, 14, '2016-11-11 09:00:00.000000', 1, '', FALSE);
INSERT INTO znamenka.trainings (client_id, trainer_id, purchase_id, start, status_id, comment, pass_for_auto)
VALUES (51, 2, 22, '2016-11-08 18:30:00.000000', 1, '', FALSE);
INSERT INTO znamenka.trainings (client_id, trainer_id, purchase_id, start, status_id, comment, pass_for_auto)
VALUES (51, 2, 22, '2016-11-10 18:30:00.000000', 1, '', FALSE);
INSERT INTO znamenka.trainings (client_id, trainer_id, purchase_id, start, status_id, comment, pass_for_auto)
VALUES (40, 3, 21, '2016-11-09 12:00:00.000000', 1, '', FALSE);

INSERT INTO znamenka.users (username, password, trainer_id, name)
VALUES ('aberg', '332c2201b01f6100e28695add10aa9af05b0d2f7d169c6248c110d42f4f7257109990321b61d0c69', NULL, 'Анастасия');
INSERT INTO znamenka.users (username, password, trainer_id, name) VALUES
  ('skasyanov', '7e021a618b355677398c4005c7a4e4027c1b3a82e56ae5c1f522464be20365ef4fa3e2a40d802cf0', NULL, 'Сергей');
INSERT INTO znamenka.users (username, password, trainer_id, name)
VALUES ('vsalnikova', 'e43338c3b9c8b329c42fd78c04d0a133a9322b7cb2a6644fcf000be70346ba77f7147254e2fbad75', 3, NULL);
INSERT INTO znamenka.users (username, password, trainer_id, name)
VALUES ('dgrossman', 'a9b14f5110cc6d2ccee14f31d2cca0e1b95834e43484d9f2ed79b0660f4628c569b1286b5edbac0a', 2, NULL);
INSERT INTO znamenka.users (username, password, trainer_id, name)
VALUES ('ibichenkova', '9d343b362559e8beb53b8b0797a986a8c9cb3522f81c9b20c017fbdcc00d6e1e311ea0e8283e15e8', 1, NULL);

INSERT INTO znamenka.user_roles (username, role_id) VALUES ('dgrossman', 1);
INSERT INTO znamenka.user_roles (username, role_id) VALUES ('vsalnikova', 1);
INSERT INTO znamenka.user_roles (username, role_id) VALUES ('ibichenkova', 1);
INSERT INTO znamenka.user_roles (username, role_id) VALUES ('aberg', 3);
INSERT INTO znamenka.user_roles (username, role_id) VALUES ('skasyanov', 3);


END TRANSACTION;
COMMIT;

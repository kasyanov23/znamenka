BEGIN TRANSACTION;

INSERT INTO common.studio VALUES (1, 'Znamenka', null);

INSERT INTO common.abon_type (id, type) VALUES (1, 'ЭМС');
INSERT INTO common.abon_type (id, type) VALUES (2, 'TRX');

INSERT INTO common.clients (client_id, surname, phone, email, birth_date, name, male, comment, car_number) VALUES (16, 'Берг', '79151555543', 'a.berg@znamenka.team', '2016-05-09', 'Анастасия', true, '', null);
INSERT INTO common.clients (client_id, surname, phone, email, birth_date, name, male, comment, car_number) VALUES (17, 'Гоголь', '79255026915', 'mogolek@gmail.com', '1976-08-02', 'Максим', true, 'Мерседес 500', null);
INSERT INTO common.clients (client_id, surname, phone, email, birth_date, name, male, comment, car_number) VALUES (18, 'Палагина', '79262143841', 'kpalagina@mail.ru', '1977-08-24', 'Екатерина', false, 'БМВ 040', null);
INSERT INTO common.clients (client_id, surname, phone, email, birth_date, name, male, comment, car_number) VALUES (19, 'Волошина', '79162397068', 'eav7068@yandex.ru', '1978-07-29', 'Елена', false, 'Мерседес 562', null);
INSERT INTO common.clients (client_id, surname, phone, email, birth_date, name, male, comment, car_number) VALUES (20, 'Эдуард', '79091597813', 'Shcerbakov@mail.ru', '1981-07-16', 'Щербаков', true, 'Хендай 184
почта фэйк', null);
INSERT INTO common.clients (client_id, surname, phone, email, birth_date, name, male, comment, car_number) VALUES (21, 'Лисица', '79853053025', 'ja-lisenok@mail.ru', '1979-06-17', 'Марина', false, 'Паспорт серия 4607 №948251
выдан отделом УФМС России по Московской области в Чеховском районе; адрес:  Москва, Мичуринский проспект, 29-1-87.', null);
INSERT INTO common.clients (client_id, surname, phone, email, birth_date, name, male, comment, car_number) VALUES (22, 'Соколов', '79688244084', 'sokolov@gmail.com', '1985-06-05', 'Алексей', true, 'почта фэйк', null);
INSERT INTO common.clients (client_id, surname, phone, email, birth_date, name, male, comment, car_number) VALUES (23, 'Полескова', '79266778225', 'poleskova@gmail.com', '1984-04-25', 'Людмила', false, 'Мерседес 007
почта фэйк', null);
INSERT INTO common.clients (client_id, surname, phone, email, birth_date, name, male, comment, car_number) VALUES (24, 'Турчанинов', '79151130113', 'turello@rambler.ru', '2016-05-09', 'Сергей', true, '', null);
INSERT INTO common.clients (client_id, surname, phone, email, birth_date, name, male, comment, car_number) VALUES (25, 'Подлипская', '79253804369', 'podlipskaya@inbox.ru', '1976-08-31', 'Татьяна', false, 'Мерседес 443
выдан: ОУФМС России по гор. Москве по р-ну Басманный
адрес: Яковоапостольский переулок, 11-13, стр. 1.
', null);
INSERT INTO common.clients (client_id, surname, phone, email, birth_date, name, male, comment, car_number) VALUES (26, 'Ионова', '79169608975', 'napishina@yandex.ru', '1981-09-11', 'Светлана', false, 'Инфинити 686', null);
INSERT INTO common.clients (client_id, surname, phone, email, birth_date, name, male, comment, car_number) VALUES (28, 'Уткин', '79850625123', 'utkinev2014@gmail.com', '2016-05-09', 'Евгений', true, 'тестовый юзер', 'А101АА77');
INSERT INTO common.clients (client_id, surname, phone, email, birth_date, name, male, comment, car_number) VALUES (29, 'Касьяновф', '79850625575', 'asd@asd', '1990-02-23', 'Сергейй', true, 'комментарфывий', '');
INSERT INTO common.clients (client_id, surname, phone, email, birth_date, name, male, comment, car_number) VALUES (30, 'Кашина', '79687037878', 'kashina.olga@gmail.com', '1974-04-15', 'Ольга', false, '', 'БМВ 858');
INSERT INTO common.clients (client_id, surname, phone, email, birth_date, name, male, comment, car_number) VALUES (31, 'иван', '79151576715', '', '2016-09-28', 'тестовый', true, 'фывasd', 'ФЫВ');
INSERT INTO common.clients (client_id, surname, phone, email, birth_date, name, male, comment, car_number) VALUES (36, 'Мусиенко', '79255435634', 's.mysienko@gmail.ru', '1985-06-06', 'Сергей', true, '', '');
INSERT INTO common.clients (client_id, surname, phone, email, birth_date, name, male, comment, car_number) VALUES (37, 'Баниж', '79684555010', 'olegbanige@gmail.com', '1969-11-06', 'Олег', true, '', 'ПОРШЕ 862');
INSERT INTO common.clients (client_id, surname, phone, email, birth_date, name, male, comment, car_number) VALUES (38, 'Чертихина', '79037227545', 'test@mail.com', null, 'Лейла', false, '', '');
INSERT INTO common.clients (client_id, surname, phone, email, birth_date, name, male, comment, car_number) VALUES (39, 'Макарова', '79251234567', 'test@mail.com', null, 'Алена', false, '', '');
INSERT INTO common.clients (client_id, surname, phone, email, birth_date, name, male, comment, car_number) VALUES (27, 'Насыров', '79857688125', '20002000@rambler.ru', '2016-05-09', 'Роман', true, '', null);
INSERT INTO common.clients (client_id, surname, phone, email, birth_date, name, male, comment, car_number) VALUES (40, 'Чернова', '79037983800', '7983800@gmail.com', '1976-12-18', 'Елена', false, '', 'БМВ 447');
INSERT INTO common.clients (client_id, surname, phone, email, birth_date, name, male, comment, car_number) VALUES (41, 'тестВторой', '79151576123', '', null, 'тестВторой', true, '', '');
INSERT INTO common.clients (client_id, surname, phone, email, birth_date, name, male, comment, car_number) VALUES (42, 'Петрова', '79091112233', 'petrovagadya@mail.ru', '2001-01-01', 'Гадя', false, '', '');
INSERT INTO common.clients (client_id, surname, phone, email, birth_date, name, male, comment, car_number) VALUES (43, 'иваннннн', '79151576717', 'asdasd@asd.ru', '2016-05-18', 'тестовый', true, 'фывыфв', 'РЕНДЖ РОВЕР 2743');
INSERT INTO common.clients (client_id, surname, phone, email, birth_date, name, male, comment, car_number) VALUES (44, 'Павлова', '79852225640', 'annapavlova13@mail.ru', '1968-08-13', 'Анна', false, '', 'РЕНДЖ РОВЕР 027');
INSERT INTO common.clients (client_id, surname, phone, email, birth_date, name, male, comment, car_number) VALUES (45, 'Салтыкова', '79031525079', 'annesal7@gmail.com', '1978-10-09', 'Анна', false, '', '');
INSERT INTO common.clients (client_id, surname, phone, email, birth_date, name, male, comment, car_number) VALUES (46, 'Дербенева', '79262113246', 'test@mail.ru', '1971-05-25', 'Елена', false, '', 'ФОРД 675');
INSERT INTO common.clients (client_id, surname, phone, email, birth_date, name, male, comment, car_number) VALUES (49, 'Шанаева', '71234567890', 'test@mail.ru', '2001-01-01', 'Ирина', false, '', '');
INSERT INTO common.clients (client_id, surname, phone, email, birth_date, name, male, comment, car_number) VALUES (51, 'Черныш', '79261487107', 'test@mail.ru', '1981-03-20', 'Полина', false, '', '');

INSERT INTO common.discounts (discount_id, discount_amount) VALUES (1, 10);
INSERT INTO common.discounts (discount_id, discount_amount) VALUES (2, 15);
INSERT INTO common.discounts (discount_id, discount_amount) VALUES (3, 20);
INSERT INTO common.discounts (discount_id, discount_amount) VALUES (4, 40);
INSERT INTO common.discounts (discount_id, discount_amount) VALUES (5, 100);

INSERT INTO common.duty_plan_type (duty_plan_type_id, duty_plan_name) VALUES (1, 'Дежурство');
INSERT INTO common.duty_plan_type (duty_plan_type_id, duty_plan_name) VALUES (2, 'Готов выйти под клиента');
INSERT INTO common.duty_plan_type (duty_plan_type_id, duty_plan_name) VALUES (3, 'Готов выйти на замену');

INSERT INTO common.products (product_id, product_name, price, training_count, expire_days, abon_type, is_abon) VALUES (1, 'Абонемент(ЭМС) 8', 28000, 8, 360, 1, true);
INSERT INTO common.products (product_id, product_name, price, training_count, expire_days, abon_type, is_abon) VALUES (2, 'Абонемент(ЭМС) 16', 51850, 16, 360, 1, true);
INSERT INTO common.products (product_id, product_name, price, training_count, expire_days, abon_type, is_abon) VALUES (3, 'Абонемент(ЭМС) 24', 71280, 24, 360, 1, true);
INSERT INTO common.products (product_id, product_name, price, training_count, expire_days, abon_type, is_abon) VALUES (4, 'Абонемент(ЭМС) 48', 129000, 24, 360, 1, true);
INSERT INTO common.products (product_id, product_name, price, training_count, expire_days, abon_type, is_abon) VALUES (5, 'Абонемент-парный(ЭМС) 8', 44800, 8, 360, 1, true);
INSERT INTO common.products (product_id, product_name, price, training_count, expire_days, abon_type, is_abon) VALUES (6, 'Абонемент-парный(ЭМС) 16', 82800, 16, 360, 1, true);
INSERT INTO common.products (product_id, product_name, price, training_count, expire_days, abon_type, is_abon) VALUES (7, 'Абонемент-парный(ЭМС) 24', 114000, 24, 360, 1, true);
INSERT INTO common.products (product_id, product_name, price, training_count, expire_days, abon_type, is_abon) VALUES (8, 'Абонемент-парный(ЭМС) 48', 258000, 48, 360, 1, true);
INSERT INTO common.products (product_id, product_name, price, training_count, expire_days, abon_type, is_abon) VALUES (9, 'Пробная тренировка', 1700, 1, 360, 1, true);
INSERT INTO common.products (product_id, product_name, price, training_count, expire_days, abon_type, is_abon) VALUES (10, 'Разовая тренировка', 3600, 1, 360, 1, true);
INSERT INTO common.products (product_id, product_name, price, training_count, expire_days, abon_type, is_abon) VALUES (11, 'УСТАРЕВШИЙ Абонемент(ЭМС) 10', 26900, 10, 360, 1, true);
INSERT INTO common.products (product_id, product_name, price, training_count, expire_days, abon_type, is_abon) VALUES (12, 'УСТАРЕВШИЙ Абонемент(ЭМС) 20', 45900, 20, 360, 1, true);
INSERT INTO common.products (product_id, product_name, price, training_count, expire_days, abon_type, is_abon) VALUES (13, 'УСТАРЕВШИЙ Абонемент(ЭМС) 50', 89900, 50, 360, 1, true);
INSERT INTO common.products (product_id, product_name, price, training_count, expire_days, abon_type, is_abon) VALUES (14, 'Протеиновый коктель с бананом', 350, null, null, null, false);

INSERT INTO common.roles (role_id, role_name) VALUES (1, 'ROLE_TRAINER');
INSERT INTO common.roles (role_id, role_name) VALUES (2, 'ROLE_CALL');
INSERT INTO common.roles (role_id, role_name) VALUES (3, 'ROLE_MANAGER');

INSERT INTO common.trainers (trainer_id, trainer_name) VALUES (1, 'ibichenkova');
INSERT INTO common.trainers (trainer_id, trainer_name) VALUES (2, 'dgrossman');
INSERT INTO common.trainers (trainer_id, trainer_name) VALUES (3, 'vsalnikova');

INSERT INTO common.training_status (status_id, status_name) VALUES (1, 'Запланирована');
INSERT INTO common.training_status (status_id, status_name) VALUES (2, 'Проведена');
INSERT INTO common.training_status (status_id, status_name) VALUES (3, 'Отменена (без списания)');
INSERT INTO common.training_status (status_id, status_name) VALUES (4, 'Отменена (с списанием)');

END TRANSACTION;
COMMIT;

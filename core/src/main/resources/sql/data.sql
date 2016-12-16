INSERT INTO public.abon_type (id, type) VALUES (1, 'ЭМС');
INSERT INTO public.abon_type (id, type) VALUES (2, 'TRX');
INSERT INTO public.clients (surname, phone, email, birth_date, name, male, comment, car_number) VALUES ('Берг', '79151555543', 'a.berg@znamenka.team', '2016-05-09', 'Анастасия', true, '', null);
INSERT INTO public.clients (surname, phone, email, birth_date, name, male, comment, car_number) VALUES ('Гоголь', '79255026915', 'mogolek@gmail.com', '1976-08-02', 'Максим', true, 'Мерседес 500', null);
INSERT INTO public.clients (surname, phone, email, birth_date, name, male, comment, car_number) VALUES ('Палагина', '79262143841', 'kpalagina@mail.ru', '1977-08-24', 'Екатерина', false, 'БМВ 040', null);
INSERT INTO public.clients (surname, phone, email, birth_date, name, male, comment, car_number) VALUES ('Волошина', '79162397068', 'eav7068@yandex.ru', '1978-07-29', 'Елена', false, 'Мерседес 562', null);
INSERT INTO public.clients (surname, phone, email, birth_date, name, male, comment, car_number) VALUES ('Эдуард', '79091597813', 'Shcerbakov@mail.ru', '1981-07-16', 'Щербаков', true, 'Хендай 184
почта фэйк', null);
INSERT INTO public.clients (surname, phone, email, birth_date, name, male, comment, car_number) VALUES ('Лисица', '79853053025', 'ja-lisenok@mail.ru', '1979-06-17', 'Марина', false, 'Паспорт серия 4607 №948251
выдан отделом УФМС России по Московской области в Чеховском районе; адрес:  Москва, Мичуринский проспект, 29-1-87.', null);
INSERT INTO public.clients (surname, phone, email, birth_date, name, male, comment, car_number) VALUES ('Соколов', '79688244084', 'sokolov@gmail.com', '1985-06-05', 'Алексей', true, 'почта фэйк', null);
INSERT INTO public.clients (surname, phone, email, birth_date, name, male, comment, car_number) VALUES ('Полескова', '79266778225', 'poleskova@gmail.com', '1984-04-25', 'Людмила', false, 'Мерседес 007
почта фэйк', null);
INSERT INTO public.clients (surname, phone, email, birth_date, name, male, comment, car_number) VALUES ('Турчанинов', '79151130113', 'turello@rambler.ru', '2016-05-09', 'Сергей', true, '', null);
INSERT INTO public.clients (surname, phone, email, birth_date, name, male, comment, car_number) VALUES ('Подлипская', '79253804369', 'podlipskaya@inbox.ru', '1976-08-31', 'Татьяна', false, 'Мерседес 443
Паспорт 4514 №721155
выдан: ОУФМС России по гор. Москве по р-ну Басманный
адрес: Яковоапостольский переулок, 11-13, стр. 1.
', null);
INSERT INTO public.clients (surname, phone, email, birth_date, name, male, comment, car_number) VALUES ('Ионова', '79169608975', 'napishina@yandex.ru', '1981-09-11', 'Светлана', false, 'Инфинити 686', null);
INSERT INTO public.clients (surname, phone, email, birth_date, name, male, comment, car_number) VALUES ('клиент', '71111111111', 'alsd@ad.ri', '2016-05-09', 'Пробный', true, '', null);
INSERT INTO public.clients (surname, phone, email, birth_date, name, male, comment, car_number) VALUES ('Уткин', '79850625123', 'utkinev2014@gmail.com', '2016-05-09', 'Евгений', true, 'тестовый юзер', 'А101АА77');
INSERT INTO public.clients (surname, phone, email, birth_date, name, male, comment, car_number) VALUES ('Касьяновф', '79850625575', 'asd@asd', '1990-02-23', 'Сергейй', true, 'комментарфывий', '');
INSERT INTO public.clients (surname, phone, email, birth_date, name, male, comment, car_number) VALUES ('Кашина', '79687037878', 'kashina.olga@gmail.com', '1974-04-15', 'Ольга', false, '', 'БМВ 858');

INSERT INTO public.trainers (trainer_id, trainer_name) VALUES (1, 'ibichenkova');
INSERT INTO public.trainers (trainer_id, trainer_name) VALUES (2, 'dgrossman');
INSERT INTO public.trainers (trainer_id, trainer_name) VALUES (3, 'vsalnikova');

INSERT INTO public.discounts (discount_id, discount_amount) VALUES (1, 10);
INSERT INTO public.discounts (discount_id, discount_amount) VALUES (2, 15);
INSERT INTO public.discounts (discount_id, discount_amount) VALUES (3, 20);
INSERT INTO public.discounts (discount_id, discount_amount) VALUES (4, 40);
INSERT INTO public.discounts (discount_id, discount_amount) VALUES (5, 100);

INSERT INTO public.products (product_id, product_name, price, training_count, expire_days, abon_type, is_abon) VALUES (1, 'Абонемент(ЭМС) 8', 28000, 8, 360, 1, true);
INSERT INTO public.products (product_id, product_name, price, training_count, expire_days, abon_type, is_abon) VALUES (2, 'Абонемент(ЭМС) 16', 51850, 16, 360, 1, true);
INSERT INTO public.products (product_id, product_name, price, training_count, expire_days, abon_type, is_abon) VALUES (3, 'Абонемент(ЭМС) 24', 71280, 24, 360, 1, true);
INSERT INTO public.products (product_id, product_name, price, training_count, expire_days, abon_type, is_abon) VALUES (4, 'Абонемент(ЭМС) 48', 129000, 24, 360, 1, true);
INSERT INTO public.products (product_id, product_name, price, training_count, expire_days, abon_type, is_abon) VALUES (5, 'Абонемент-парный(ЭМС) 8', 44800, 8, 360, 1, true);
INSERT INTO public.products (product_id, product_name, price, training_count, expire_days, abon_type, is_abon) VALUES (6, 'Абонемент-парный(ЭМС) 16', 82800, 16, 360, 1, true);
INSERT INTO public.products (product_id, product_name, price, training_count, expire_days, abon_type, is_abon) VALUES (7, 'Абонемент-парный(ЭМС) 24', 114000, 24, 360, 1, true);
INSERT INTO public.products (product_id, product_name, price, training_count, expire_days, abon_type, is_abon) VALUES (8, 'Абонемент-парный(ЭМС) 48', 258000, 48, 360, 1, true);
INSERT INTO public.products (product_id, product_name, price, training_count, expire_days, abon_type, is_abon) VALUES (9, 'Пробная тренировка', 1700, 1, 360, 1, true);
INSERT INTO public.products (product_id, product_name, price, training_count, expire_days, abon_type, is_abon) VALUES (10, 'Разовая тренировка', 3600, 1, 360, 1, true);
INSERT INTO public.products (product_id, product_name, price, training_count, expire_days, abon_type, is_abon) VALUES (11, 'УСТАРЕВШИЙ Абонемент(ЭМС) 10', 26900, 10, 360, 1, true);
INSERT INTO public.products (product_id, product_name, price, training_count, expire_days, abon_type, is_abon) VALUES (12, 'УСТАРЕВШИЙ Абонемент(ЭМС) 20', 45900, 20, 360, 1, true);
INSERT INTO public.products (product_id, product_name, price, training_count, expire_days, abon_type, is_abon) VALUES (13, 'УСТАРЕВШИЙ Абонемент(ЭМС) 50', 89900, 50, 360, 1, true);
INSERT INTO public.products (product_id, product_name, price, training_count, expire_days, abon_type, is_abon) VALUES (14, 'Протеиновый коктель с бананом', 350, null, null, null, false);

INSERT INTO public.roles (role_id, role_name) VALUES (1, 'ROLE_TRAINER');
INSERT INTO public.roles (role_id, role_name) VALUES (2, 'ROLE_CALL');
INSERT INTO public.roles (role_id, role_name) VALUES (3, 'ROLE_MANAGER');

INSERT INTO public.users (username, password, trainer_id, name) VALUES ('aberg', '332c2201b01f6100e28695add10aa9af05b0d2f7d169c6248c110d42f4f7257109990321b61d0c69', null, 'Анастасия');
INSERT INTO public.users (username, password, trainer_id, name) VALUES ('skasyanov', '7e021a618b355677398c4005c7a4e4027c1b3a82e56ae5c1f522464be20365ef4fa3e2a40d802cf0', null, 'Сергей');
INSERT INTO public.users (username, password, trainer_id, name) VALUES ('vsalnikova', 'e43338c3b9c8b329c42fd78c04d0a133a9322b7cb2a6644fcf000be70346ba77f7147254e2fbad75', 3, null);
INSERT INTO public.users (username, password, trainer_id, name) VALUES ('dgrossman', 'a9b14f5110cc6d2ccee14f31d2cca0e1b95834e43484d9f2ed79b0660f4628c569b1286b5edbac0a', 2, null);
INSERT INTO public.users (username, password, trainer_id, name) VALUES ('ibichenkova', '9d343b362559e8beb53b8b0797a986a8c9cb3522f81c9b20c017fbdcc00d6e1e311ea0e8283e15e8', 1, null);

INSERT INTO public.user_roles (username, role_id) VALUES ('dgrossman', 1);
INSERT INTO public.user_roles (username, role_id) VALUES ('vsalnikova', 1);
INSERT INTO public.user_roles (username, role_id) VALUES ('ibichenkova', 1);
INSERT INTO public.user_roles (username, role_id) VALUES ('aberg', 3);
INSERT INTO public.user_roles (username, role_id) VALUES ('skasyanov', 3);

INSERT INTO public.training_status (status_id, status_name) VALUES (1, 'Запланирована');
INSERT INTO public.training_status (status_id, status_name) VALUES (2, 'Проведена');
INSERT INTO public.training_status (status_id, status_name) VALUES (3, 'Отменена (без списания)');
INSERT INTO public.training_status (status_id, status_name) VALUES (4, 'Отменена (с списанием)');


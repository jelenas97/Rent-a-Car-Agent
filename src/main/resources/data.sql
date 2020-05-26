insert into fuel_type(name, active)
values ('Petrol', 1);
insert into fuel_type(name, active)
values ('Gas', 1);
insert into fuel_type(name, active)
values ('Diesel', 1);


insert into car_brand(name, active)
values ('Lamborghini', 1);
insert into car_brand(name, active)
values ('Special', 1);
insert into car_brand(name, active)
values ('Brand 3', 1);

insert into car_model(name, active, car_brand_id)
values ('FKP 37', 1, 1);
insert into car_model(name, active, car_brand_id)
values ('Bat 39', 1, 2);
insert into car_model(name, active, car_brand_id)
values ('Model 3', 1, 3);

insert into car_class(name, active)
values ('Sport car', 1);
insert into car_class(name, active)
values ('Batman car', 1);
insert into car_class(name, active)
values ('Class 3', 1);


insert into transmission_type(name, active)
values ('Manual', 1);
insert into transmission_type(name, active)
values ('Automatic', 1);
insert into transmission_type(name, active)
values ('Semi-Automatic', 1);


insert into authority (name)
values ('ROLE_CLIENT');
insert into authority (name)
values ('ROLE_AGENT');
insert into authority (name)
values ('ROLE_COMPANY');
insert into authority (name)
values ('ROLE_ADMIN');

insert into users(type, password, username, email, status)
values ('CLIENT', '$2a$10$yNKbCm5ETrM/bNnJMcmIxe/95qU6vpqnxn2/i9pAv5PZsrPbnJLvK', 'petar', 'pera@gmail.com', 'ACTIVE');
insert into users(type, password, username, email, status)
values ('CLIENT', '$2a$10$yNKbCm5ETrM/bNnJMcmIxe/95qU6vpqnxn2/i9pAv5PZsrPbnJLvK', 'nikola', 'nikola@gmail.com',
        'ACTIVE');
insert into users(type, password, username, email, status)
values ('AGENT', '$2a$10$yNKbCm5ETrM/bNnJMcmIxe/95qU6vpqnxn2/i9pAv5PZsrPbnJLvK', 'agent', 'agent@gmail.com', 'ACTIVE');
insert into users(type, password, username, email, status)
values ('AGENT', '$2a$10$yNKbCm5ETrM/bNnJMcmIxe/95qU6vpqnxn2/i9pAv5PZsrPbnJLvK', 'agent2', 'agent2@gmail.com',
        'ACTIVE');
insert into users(type, password, username, email, status)
values ('AGENT', '$2a$10$yNKbCm5ETrM/bNnJMcmIxe/95qU6vpqnxn2/i9pAv5PZsrPbnJLvK', 'agent3', 'agent3@gmail.com',
        'ACTIVE');
insert into users(type, password, username, email, status)
values ('ADMIN', '$2a$10$yNKbCm5ETrM/bNnJMcmIxe/95qU6vpqnxn2/i9pAv5PZsrPbnJLvK', 'admin', 'admin@gmail.com', 'ACTIVE');
insert into users(type, password, username, email, status)
values ('ADMIN', '$2a$10$yNKbCm5ETrM/bNnJMcmIxe/95qU6vpqnxn2/i9pAv5PZsrPbnJLvK', 'admin2', 'admin2@gmail.com',
        'ACTIVE');

INSERT into user_authority
set user_id      = 1,
    authority_id = 1;
INSERT into user_authority
set user_id      = 2,
    authority_id = 1;
INSERT into user_authority
set user_id      = 3,
    authority_id = 2;
INSERT into user_authority
set user_id      = 4,
    authority_id = 2;
INSERT into user_authority
set user_id      = 6,
    authority_id = 4;

insert into price_list(price_per_day, price_per_km, agent_id)
values (1000, 20, 3);
insert into price_list(price_per_day, price_per_km, agent_id)
values (2000, 50, 3);


insert into car(available_tracking, image, kid_seats, mileage, name, rate, advertisement_id, car_brand_id, car_class_id,
                transmission_type_id, car_model_id)
values (1, 'https://media.nu.nl/m/zsbx1zkaavd4_sqr256.jpg/lamborghini-onthult-zijn-eerste-hybride-sportwagen-sian.jpg',
        2, 1000, 'Sián', 5.0, 1, 1, 1, 3, 1);
insert into advertisement(cdw, discount, place, kilometres_limit, car_id, owner_id, price_list_id, start_date, end_date)
values (1, 20, 'Novi Sad', 5000, 1, 3, 1, '2020-05-03', '2020-10-03');

insert into car(available_tracking, image, kid_seats, mileage, name, rate, advertisement_id, car_brand_id, car_class_id,
                transmission_type_id, car_model_id)
values (1, 'https://liquipedia.net/commons/images/7/74/RL_%2789_Batmobile.jpg', 0, 10000, 'Batmobile', 10.0, 2, 2, 2, 2,
        2);
insert into advertisement(cdw, discount, place, kilometres_limit, car_id, owner_id, price_list_id, start_date, end_date)
values (0, 10, 'Novi Sad', 5000, 2, 4, 2, '2020-05-03', '2020-10-03');
--
insert into car(available_tracking, image, kid_seats, mileage, name, rate, advertisement_id, car_brand_id, car_class_id,
                transmission_type_id, car_model_id)
values (1, 'https://www.extremetech.com/wp-content/uploads/2019/12/SONATA-hero-option1-764A5360-edit.jpg', 0, 10000,
        'NOVO', 10.0, 3, 1, 1, 1,
        1);
insert into advertisement(cdw, discount, place, kilometres_limit, car_id, owner_id, price_list_id, start_date, end_date)
values (0, 20, 'Novi Sad', 5000, 3, 1, 1, '2020-05-03', '2020-10-03');
--
insert into car(available_tracking, image, kid_seats, mileage, name, rate, advertisement_id, car_brand_id, car_class_id,
                transmission_type_id, car_model_id)
values (0, 'https://nfsmods.xyz/userdata/Marco3156/2019-Bugatti-Divo-V1-1080.jpg', 3, 54000, 'BB-Gier', 9.0, 4, 3, 3, 3,
        3);
insert into advertisement(cdw, discount, place, kilometres_limit, car_id, owner_id, price_list_id, start_date, end_date)
values (0, 10, 'Novi Sad', 500000, 4, 3, 1, '2020-05-03', '2020-10-03');


insert into car(available_tracking, image, kid_seats, mileage, name, rate, advertisement_id, car_brand_id, car_class_id,
                transmission_type_id, car_model_id)
values (1, 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR3JhOMtglnMN1slCHyXoWA4O_0EzNYVQnpaDk9eLSLNx9Q4WQ&s',
        0, 5040, 'Harper', 5.0, 5, 1, 1, 3, 1);
insert into advertisement(cdw, discount,place, kilometres_limit, car_id, owner_id, price_list_id,start_date,end_date)
values (1, 20, 'Novi Sad', 5000, 5, 3, 1, '2020-05-03', '2020-10-03');



insert into car(available_tracking, image, kid_seats, mileage, name, rate, advertisement_id, car_brand_id, car_class_id,
                transmission_type_id, car_model_id)
values (1, 'https://pbs.twimg.com/profile_images/588433651144196096/nCXD0GOf_400x400.jpg',
        0, 5040, 'Honys', 5.0, 6, 1, 1, 3, 1);
insert into advertisement(cdw, discount, place, kilometres_limit, car_id, owner_id, price_list_id, start_date, end_date)
values (1, 20, 'Novi Sad', 5000, 6, 3, 1, '2020-05-03', '2020-10-03');

insert into car(available_tracking, image, kid_seats, mileage, name, rate, advertisement_id, car_brand_id, car_class_id,
                transmission_type_id, car_model_id)
values (1, 'https://pbs.twimg.com/profile_images/588433651144196096/nCXD0GOf_400x400.jpg',
        0, 5040, 'Ruops', 5.0, 7, 1, 1, 3, 1);
insert into advertisement(cdw, discount, place, kilometres_limit, car_id, owner_id, price_list_id, start_date, end_date)
values (1, 20, 'Novi Sad', 5000, 7, 3, 1, '2020-05-03', '2020-10-03');



insert into car_fuel_type(car_id, fuel_type_id) values (1,3);
insert into car_fuel_type(car_id, fuel_type_id) values (2,3);
insert into car_fuel_type(car_id, fuel_type_id)
values (3, 3);
insert into car_fuel_type(car_id, fuel_type_id)
values (4, 3);
insert into car_fuel_type(car_id, fuel_type_id)
values (3, 2);
insert into car_fuel_type(car_id, fuel_type_id)
values (4, 2);
insert into car_fuel_type(car_id, fuel_type_id)
values (5, 3);
insert into car_fuel_type(car_id, fuel_type_id)
values (6, 3);
insert into car_fuel_type(car_id, fuel_type_id)
values (7, 3);
insert into car_fuel_type(car_id, fuel_type_id)
values (5, 2);
insert into car_fuel_type(car_id, fuel_type_id)
values (6, 1);
insert into comment(content, date, advertisement_id, user_id, status) values ('Really good car!','2020-05-03',1,1,'UNPROCESSED');
insert into comment(content, date, advertisement_id, user_id, status) values ('Awesome car!','2020-05-04',1,1,'UNPROCESSED');
insert into comment(content, date, advertisement_id, user_id, status) values ('STUPID CAR!!!!','2020-05-05',1,2,'UNPROCESSED');

# insert into rent_request(advertisement_id, sender_id, end_date_time, rent_request_status, start_date_time)
# values (1, 8, '2020-05-03 05:05:05', 'PENDING', '2021-05-15 04:04:04');
# insert into rent_request(advertisement_id, sender_id, end_date_time, rent_request_status, start_date_time)
# values (2, 8, '2020-05-12 05:05:05', 'RESERVED', '2020-05-22 04:04:04');
# insert into rent_request(advertisement_id, sender_id, end_date_time, rent_request_status, start_date_time)
# values (3, 8, '2021-05-05 05:05:05', 'PAID', '2021-04-04 04:04:04');


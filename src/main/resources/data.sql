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

insert into users(type, password, username, email, status, first_name, last_name, address)
values ('CLIENT', '$2a$10$yNKbCm5ETrM/bNnJMcmIxe/95qU6vpqnxn2/i9pAv5PZsrPbnJLvK', 'petar', 'pera@gmail.com', 'ACTIVE', 'Petar', 'Petrovic', 'Balzakova 15');
insert into users(type, password, username, email, status, first_name, last_name, address)
values ('CLIENT', '$2a$10$yNKbCm5ETrM/bNnJMcmIxe/95qU6vpqnxn2/i9pAv5PZsrPbnJLvK', 'nikola', 'nikola@gmail.com',
        'ACTIVE', 'Nikola', 'Nikolic', 'Balzakova 70');
insert into users(type, password, username, email, status, first_name, last_name, address, business_registration_number)
values ('AGENT', '$2a$10$yNKbCm5ETrM/bNnJMcmIxe/95qU6vpqnxn2/i9pAv5PZsrPbnJLvK', 'agent', 'agent@gmail.com', 'ACTIVE', 'Agent', 'Agentski', 'Balzakova 30', '555');
insert into users(type, password, username, email, status, first_name, last_name, address, business_registration_number)
values ('AGENT', '$2a$10$yNKbCm5ETrM/bNnJMcmIxe/95qU6vpqnxn2/i9pAv5PZsrPbnJLvK', 'agent2', 'agent2@gmail.com',
        'ACTIVE', 'Nenad', 'Nenadovic', 'Balzakova 15', '161');
insert into users(type, password, username, email, status, first_name, last_name, address, business_registration_number)
values ('AGENT', '$2a$10$yNKbCm5ETrM/bNnJMcmIxe/95qU6vpqnxn2/i9pAv5PZsrPbnJLvK', 'agent3', 'agent3@gmail.com',
        'ACTIVE', 'Marko', 'Markovic', 'Balzakova 15', '873');
insert into users(type, password, username, email, status, first_name, last_name, address)
values ('ADMIN', '$2a$10$yNKbCm5ETrM/bNnJMcmIxe/95qU6vpqnxn2/i9pAv5PZsrPbnJLvK', 'admin', 'admin@gmail.com', 'ACTIVE', 'Bojan', 'Bojanic', 'Balzakova 15');
insert into users(type, password, username, email, status, first_name, last_name, address)
values ('ADMIN', '$2a$10$yNKbCm5ETrM/bNnJMcmIxe/95qU6vpqnxn2/i9pAv5PZsrPbnJLvK', 'admin2', 'admin2@gmail.com',
        'ACTIVE', 'Damir', 'Damirovic', 'Balzakova 45');

insert into user_authority(user_id, authority_id)
values(1, 1);
insert into user_authority(user_id, authority_id)
values(2, 1);
insert into user_authority(user_id, authority_id)
values(3, 2);
insert into user_authority(user_id, authority_id)
values(4, 2);
insert into user_authority(user_id, authority_id)
values (6, 4);

insert into price_list(price_per_day, price_per_km, cdw, creator_id)
values (1000, 20, 100, 3);
insert into price_list(price_per_day, price_per_km, cdw, creator_id)
values (2000, 50, 200, 3);


insert into car(micro_id, available_tracking, kid_seats, mileage, name, rate, car_brand_id, car_class_id,
                transmission_type_id, car_model_id)
values (1, 1, 2, 1000, 'Sián', 5.0, 1, 1, 3, 1);
insert into advertisement(micro_id, cdw, discount, place, kilometres_limit, car_id, owner_id, price_list_id, start_date,
                          end_date)
values (1, 1, 20, 'Novi Sad', 5000, 1, 3, 1, '2020-05-03', '2020-10-03');

insert into car(micro_id, available_tracking, kid_seats, mileage, name, rate, car_brand_id, car_class_id,
                transmission_type_id, car_model_id)
values (2, 1, 0, 10000, 'Batmobile', 10.0, 2, 2, 2,
        2);
insert into advertisement(micro_id, cdw, discount, place, kilometres_limit, car_id, owner_id, price_list_id, start_date,
                          end_date)
values (2, 0, 10, 'Novi Sad', 5000, 2, 4, 2, '2020-05-03', '2020-10-03');
--
insert into car(micro_id, available_tracking, kid_seats, mileage, name, rate, car_brand_id, car_class_id,
                transmission_type_id, car_model_id)
values (3, 1, 0, 10000,
        'NOVO', 10.0, 1, 1, 1,
        1);
insert into advertisement(micro_id, cdw, discount, place, kilometres_limit, car_id, owner_id, price_list_id, start_date,
                          end_date)
values (3, 0, 20, 'Novi Sad', 5000, 3, 1, 1, '2020-05-03', '2020-10-03');
--

insert into car_fuel_type(car_id, fuel_type_id)
values (1, 3);
insert into car_fuel_type(car_id, fuel_type_id) values (2,3);
insert into car_fuel_type(car_id, fuel_type_id)
values (3, 3);

insert into comment(content, date, advertisement_id, rent_request_id, user_id, status) values ('Really good car!','2020-06-13',3,3,1,'APPROVED');
#insert into comment(content, date, advertisement_id, rent_request_id, user_id, status) values ('Awesome car!','2020-05-04',1, ,1,'UNPROCESSED');
#insert into comment(content, date, advertisement_id, rent_request_id, user_id, status) values ('STUPID CAR!!!!','2020-05-05',1, ,2,'UNPROCESSED');

insert into rent_request(advertisement_id, sender_id, end_date_time, rent_request_status, start_date_time)
values (1, 1, '2020-06-03 05:05:05', 'PENDING', '2020-06-15 04:04:04');
insert into rent_request(advertisement_id, sender_id, end_date_time, rent_request_status, start_date_time)
values (2, 1, '2020-06-12 05:05:05', 'RESERVED', '2020-06-22 04:04:04');
insert into rent_request(advertisement_id, sender_id, end_date_time, rent_request_status, start_date_time)
values (3, 1, '2020-05-25 05:05:05', 'PAID', '2020-05-20 04:04:04');
insert into rent_request(advertisement_id, sender_id, end_date_time, rent_request_status, start_date_time)
values (3, 1, '2020-05-13 05:05:05', 'PAID', '2020-05-19 04:04:04');

insert into rate(value, advertisement_id, client_id, rent_request_id)
values(9, 3, 1, 3);

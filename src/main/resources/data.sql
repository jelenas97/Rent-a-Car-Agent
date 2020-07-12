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
values ('ROLE_AGENT');



insert into users(id,type, password, username, email, status, first_name, last_name, address, business_registration_number)
values (3,'AGENT', '$2a$10$yNKbCm5ETrM/bNnJMcmIxe/95qU6vpqnxn2/i9pAv5PZsrPbnJLvK', 'agent', 'bertrand162@ifchuck.com',
        'ACTIVE', 'Agent', 'Agentski', 'Balzakova 30', '555');

insert into user_authority(user_id, authority_id)
values(3, 1);


insert into price_list(price_per_day, price_per_km, cdw, creator_id)
values (1000, 20, 100, 3);
insert into price_list(price_per_day, price_per_km, cdw, creator_id)
values (2000, 50, 200, 3);
insert into price_list(price_per_day, price_per_km, cdw, creator_id)
values (1500, 30, 200, 3);
insert into price_list(price_per_day, price_per_km, cdw, creator_id)
values (1200, 25, 0, 3);

insert into car(micro_id, available_tracking, kid_seats, mileage, name, rate, car_brand_id, car_class_id,
                transmission_type_id, car_model_id)
values (1, 1, 2, 1000, 'Sián', 5.0, 1, 1, 3, 1);
insert into advertisement(micro_id, discount, place, kilometres_limit, car_id, owner_id, price_list_id, start_date,
                          end_date)
values (1, 20, 'Novi Sad', 5000, 1, 3, 1, '2020-05-03', '2020-10-03');

insert into car(micro_id, available_tracking, kid_seats, mileage, name, rate, car_brand_id, car_class_id,
                transmission_type_id, car_model_id)
values (2, 1, 0, 10000, 'Batmobile', 10.0, 2, 2, 2,
        2);
insert into advertisement(micro_id, discount, place, kilometres_limit, car_id, owner_id, price_list_id, start_date,
                          end_date)
values (2, 10, 'Novi Sad', 5000, 2, 3, 2, '2020-05-03', '2020-10-03');
--
insert into car(micro_id, available_tracking, kid_seats, mileage, name, rate, car_brand_id, car_class_id,
                transmission_type_id, car_model_id)
values (3, 1, 0, 10000,
        'NOVO', 10.0, 1, 1, 1,
        1);
insert into advertisement(micro_id, discount, place, kilometres_limit, car_id, owner_id, price_list_id, start_date,
                          end_date)
values (3, 20, 'Novi Sad', 5000, 3, 3, 1, '2020-05-03', '2020-10-03');
--

insert into car_fuel_type(car_id, fuel_type_id)
values (1, 3);
insert into car_fuel_type(car_id, fuel_type_id)
values (2, 3);
insert into car_fuel_type(car_id, fuel_type_id)
values (3, 3);



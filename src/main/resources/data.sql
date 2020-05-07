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

insert into car_model(name, active, car_brand_id)
values ('FKP 37', 1, 1);
insert into car_model(name, active, car_brand_id)
values ('Bat 39', 1, 2);

insert into car_class(name, active)
values ('Sport car', 1);
insert into car_class(name, active)
values ('Super car', 1);



insert into transmission_type(name, active)
values ('Manual', 1);
insert into transmission_type(name, active)
values ('Automatic', 1);
insert into transmission_type(name, active)
values ('Semi-Automatic', 1);

insert into users(type, password, username, email,status) values ('CLIENT',123456789,'petar', 'pera@gmail.com','ACTIVE');
insert into users(type, password, username, email,status) values ('CLIENT',123456789,'nikola', 'nikola@gmail.com','ACTIVE');
insert into users(type, password, username, email,status) values ('AGENT',123456789,'agent', 'agent@gmail.com','ACTIVE');
insert into users(type, password, username, email,status) values ('AGENT',123456789,'agent2', 'agent2@gmail.com','ACTIVE');
insert into users(type, password, username, email,status) values ('AGENT',123456789,'agent3', 'agent3@gmail.com','ACTIVE');
insert into users(type, password, username, email,status) values ('ADMIN',123456789,'admin', 'admin@gmail.com','ACTIVE');
insert into users(type, password, username, email,status) values ('ADMIN',123456789,'admin2', 'admin2@gmail.com','ACTIVE');
insert into users(type, password, username, email, status)
values ('CLIENT', 123456789, 'client', 'client@gmail.com', 'ACTIVE');


insert into car(available_tracking, image, kid_seats, mileage, name, rate, advertisement_id, car_brand_id, car_class_id, transmission_type_id,car_model_id)
values (1, 'https://media.nu.nl/m/zsbx1zkaavd4_sqr256.jpg/lamborghini-onthult-zijn-eerste-hybride-sportwagen-sian.jpg',
        2, 1000, 'Sián', 5.0, 1, 1, 1, 3, 1);

insert into car(available_tracking, image, kid_seats, mileage, name, rate, advertisement_id, car_brand_id, car_class_id, transmission_type_id,car_model_id)
values (1, 'https://liquipedia.net/commons/images/7/74/RL_%2789_Batmobile.jpg', 0, 10000, 'Batmobile', 10.0, 2, 2, 2, 2,
        2);
insert into car(available_tracking, image, kid_seats, mileage, name, rate, advertisement_id, car_brand_id, car_class_id,
                transmission_type_id, car_model_id)
values (1, 'https://liquipedia.net/commons/images/7/74/RL_%2789.jpg', 0, 10000, 'NOVO', 10.0, 2, 2, 2, 2,
        2);


insert into price_list(price_per_day, price_per_km, agent_id) values (1000,20,3);
insert into price_list(price_per_day, price_per_km, agent_id) values (2000,50,3);

insert into advertisement(cdw, discount,place, kilometres_limit, car_id, owner_id, price_list_id,start_date,end_date)
values (1, 20, 'Novi Sad', 5000, 1, 3, 1, '2020-05-03', '2020-06-03');
insert into advertisement(cdw, discount, place, kilometres_limit, car_id, owner_id, price_list_id, start_date, end_date)
values (0, 20, 'Novi Sad', 5000, 2, 1, 1, '2020-05-03', '2020-06-03');
insert into advertisement(cdw, discount, place, kilometres_limit, car_id, owner_id, price_list_id, start_date, end_date)
values (0, 20, 'Beograd', 5000, 3, 1, 1, '2020-05-03', '2020-06-03');


insert into car_fuel_type(car_id, fuel_type_id) values (1,3);
insert into car_fuel_type(car_id, fuel_type_id) values (2,3);

insert into comment(content, date, advertisement_id, user_id, status) values ('Really good car!','2020-05-03',1,1,'UNPROCESSED');
insert into comment(content, date, advertisement_id, user_id, status) values ('Awesome car!','2020-05-04',1,1,'UNPROCESSED');
insert into comment(content, date, advertisement_id, user_id, status) values ('STUPID CAR!!!!','2020-05-05',1,2,'UNPROCESSED');

insert into rent_request(advertisement_id, sender_id, end_date_time, rent_request_status, start_date_time)
values (1, 8, '2020-05-03 05:05:05', 'PENDING', '2021-05-15 04:04:04');
insert into rent_request(advertisement_id, sender_id, end_date_time, rent_request_status, start_date_time)
values (2, 8, '2020-05-12 05:05:05', 'RESERVED', '2020-05-22 04:04:04');
insert into rent_request(advertisement_id, sender_id, end_date_time, rent_request_status, start_date_time)
values (3, 8, '2021-05-05 05:05:05', 'PAID', '2021-04-04 04:04:04');

# insert into advertisement_rent_requests(advertisements_id, rent_requests_id)
# values (1, 1);
# insert into advertisement_rent_requests(advertisements_id, rent_requests_id)
# values (2, 2);
# insert into advertisement_rent_requests(advertisements_id, rent_requests_id)
# values (3, 3);
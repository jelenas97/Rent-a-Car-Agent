insert into fuel_type(name,active) values('Petrol',true);
insert into fuel_type(name,active) values('Gas',true);
insert into fuel_type(name,active) values('Diesel',true);


insert into car_brand(name,active) values('Lamborghini',true);
insert into car_brand(name,active) values('Special',true);

insert into car_model(name,active,car_brand_id) values('FKP 37',true,1);
insert into car_model(name,active,car_brand_id) values('Bat 39',true,2);

insert into car_class(name,active) values('Sport car',true);
insert into car_class(name,active) values('Super car',true);



insert into transmission_type(name,active) values('Manual',true);
insert into transmission_type(name,active) values('Automatic',true);
insert into transmission_type(name,active) values('Semi-Automatic',true);

insert into users(type, password, username, email,status) values ('USER',123456789,'petar', 'pera@gmail.com','ACTIVE');
insert into users(type, password, username, email,status) values ('USER',123456789,'nikola', 'nikola@gmail.com','ACTIVE');
insert into users(type, password, username, email,status) values ('AGENT',123456789,'agent', 'agent@gmail.com','ACTIVE');
insert into users(type, password, username, email,status) values ('AGENT',123456789,'agent2', 'agent2@gmail.com','ACTIVE');
insert into users(type, password, username, email,status) values ('AGENT',123456789,'agent3', 'agent3@gmail.com','ACTIVE');
insert into users(type, password, username, email,status) values ('ADMIN',123456789,'admin', 'admin@gmail.com','ACTIVE');
insert into users(type, password, username, email,status) values ('ADMIN',123456789,'admin2', 'admin2@gmail.com','ACTIVE');




insert into car(available_tracking, image, kid_seats, mileage, name, rate, advertisement_id, car_brand_id, car_class_id, transmission_type_id,car_model_id)
values(true,'https://media.nu.nl/m/zsbx1zkaavd4_sqr256.jpg/lamborghini-onthult-zijn-eerste-hybride-sportwagen-sian.jpg',2,1000,'Sián',5.0,1,1,1,3,1);

insert into car(available_tracking, image, kid_seats, mileage, name, rate, advertisement_id, car_brand_id, car_class_id, transmission_type_id,car_model_id)
values(true,'https://liquipedia.net/commons/images/7/74/RL_%2789_Batmobile.jpg',0,10000,'Batmobile',10.0,2,2,2,2,2);


insert into price_list(price_per_day, price_per_km, agent_id) values (1000,20,3);
insert into price_list(price_per_day, price_per_km, agent_id) values (2000,50,3);

insert into advertisement(cdw, discount,place, kilometres_limit, car_id, owner_id, price_list_id,start_date,end_date)
values(true,20,'Novi Sad',5000,1,3,1,'2020-05-03','2020-06-03');

insert into advertisement(cdw, discount,place, kilometres_limit, car_id, owner_id, price_list_id,start_date,end_date)
values(true,10,'Novi Sad',10000,2,3,2,'2020-05-03','2020-06-03');

insert into car_fuel_type(car_id, fuel_type_id) values (1,3);
insert into car_fuel_type(car_id, fuel_type_id) values (2,3);

insert into comment(content, date, advertisement_id, user_id, status) values ('Really good car!','2020-05-03',1,1,'UNPROCESSED');
insert into comment(content, date, advertisement_id, user_id, status) values ('Awesome car!','2020-05-04',1,1,'UNPROCESSED');
insert into comment(content, date, advertisement_id, user_id, status) values ('STUPID CAR!!!!','2020-05-05',1,2,'UNPROCESSED');
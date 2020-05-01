# insert into user(type, address, city, country, email, enabled, last_password_reset_date, name, password, password_changed, phone_number, serial_number, surname, version, activated_account, clinic_id, medical_record_id)
#  values ()

insert into car_brand(name,active) values('BMW',true);
insert into car_brand(name,active) values('Audi',true);
insert into car_brand(name,active) values('Mercedes',true);
insert into car_brand(name,active) values('Tesla',true);

insert into car_model(name,active,car_brand_id) values('model1',true,1);
insert into car_model(name,active,car_brand_id) values('model2',true,1);
insert into car_model(name,active,car_brand_id) values('model3',true,2);

insert into car_class(name,active) values('SUV',true);
insert into car_class(name,active) values('Old timer',true);

insert into fuel_type(name,active) values('Petrol',true);
insert into fuel_type(name,active) values('Gas',true);
insert into fuel_type(name,active) values('Diesel',true);

insert into transmission_type(name,active) values('Manual',true);
insert into transmission_type(name,active) values('Automatic',true);
insert into transmission_type(name,active) values('Semi-Automatic',true);

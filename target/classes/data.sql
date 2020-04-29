# insert into user(type, address, city, country, email, enabled, last_password_reset_date, name, password, password_changed, phone_number, serial_number, surname, version, activated_account, clinic_id, medical_record_id)
#  values ()

insert into car_brand(name,active) values('BMW',true);
insert into car_brand(name,active) values('Audi',true);
insert into car_brand(name,active) values('Mercedes',true);
insert into car_brand(name,active) values('Tesla',true);

insert into car_model(name) value('M5');
insert into car_model(name) value('R8');
insert into car_model(name) value('S5');

insert into car_class(name) value('SUV');
insert into car_class(name) value('Old timer');

insert into fuel_type(name) value('Petrol');
insert into fuel_type(name) value('Gas');
insert into fuel_type(name) value('Diesel');

insert into transmission_type(name) value('Manual');
insert into transmission_type(name) value('Automatic');
insert into transmission_type(name) value('Semi-Automatic');





insert into Car (name) value ('Car 1');

#insert into advertisement(cdw, discount, kilometres_limit, car_id, owner_id, price_list_id) values (true,20,2000,1,1,null);
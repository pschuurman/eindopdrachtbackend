insert into roles(rolename) values ('ADMINISTRATIEF MEDEWERKER'), ('MONTEUR'), ('KASSA MEDEWERKER');
insert into customers(id, first_name, last_name , street, house_number, postal_code) values (1001, 'Peter', 'Schuurman', 'Madameperenlaan', 31, '3452 EP');
insert into customers(id, first_name, last_name , street, house_number, postal_code) values (1002, 'Jaap', 'Schuurman', 'Madameperenlaan', 31, '3452 EP');
insert into customers(id, first_name, last_name , street, house_number, postal_code) values (1003, 'Kees', 'Schuurman', 'Madameperenlaan', 31, '3452 EP');
insert into cars(id, brand, type, customer_id) values (2002, 'Mazda', 'Premacy', 1001);
insert into cars(id, brand, type, customer_id) values (2003, 'Toyota', 'Avensis', 1001);
insert into cars(id, brand, type, customer_id) values (2004, 'Ford', 'C-Max', 1003);



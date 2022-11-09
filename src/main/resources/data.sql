insert into roles(rolename) values ('ADMINISTRATIEF MEDEWERKER'), ('MONTEUR'), ('KASSA MEDEWERKER');

insert into customers(id, first_name, last_name , street, house_number, postal_code) values (1001, 'Peter', 'Schuurman', 'Madameperenlaan', 31, '3452 EP');
insert into customers(id, first_name, last_name , street, house_number, postal_code) values (1002, 'Jaap', 'Schuurman', 'Madameperenlaan', 31, '3452 EP');
insert into customers(id, first_name, last_name , street, house_number, postal_code) values (1003, 'Kees', 'Schuurman', 'Madameperenlaan', 31, '3452 EP');

insert into cars(id, brand, type) values (2002, 'Mazda', 'Premacy');
insert into cars(id, brand, type) values (2003, 'Toyota', 'Avensis');
insert into cars(id, brand, type) values (2004, 'Ford', 'C-Max');

insert into invoices(id, part_name, price, car_id) values (3001, 'Uitlaat', 150, 2002);
insert into invoices(id, part_name, price, car_id) values (3002, 'Banden', 250, 2002);
insert into invoices(id, part_name, price, car_id) values (3003, 'Ruitenwissers', 50, 2002);

insert into parts(id, part_name, price) values (4001, 'Uitlaat', 150);
insert into parts(id, part_name, price) values (4002, 'Banden', 250);
insert into parts(id, part_name, price) values (4003, 'Ruitenwissers', 50);

insert into repairs(id, part_name, price) values (5001, 'Uitlaat', 150);
insert into repairs(id, part_name, price) values (5002, 'Banden', 250);
insert into repairs(id, part_name, price) values (5003, 'Ruitenwissers', 50);

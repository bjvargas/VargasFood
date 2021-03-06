set foreign_key_checks = 0;

delete from city;
delete from kitchen;
delete from state;
delete from payment;
delete from collection_group;
delete from collection_group_permission;
delete from permission;
delete from product;
delete from restaurant;
delete from restaurant_payment;
delete from user;
delete from user_collection_group;

set foreign_key_checks = 1;

alter table city auto_increment = 1;
alter table kitchen auto_increment = 1;
alter table state auto_increment = 1;
alter table payment auto_increment = 1;
alter table collection_group auto_increment = 1;
alter table permission auto_increment = 1;
alter table product auto_increment = 1;
alter table restaurant auto_increment = 1;
alter table user auto_increment = 1;

insert into kitchen (id, name) values (1, 'Tailandesa');
insert into kitchen (id, name) values (2, 'Indiana');
insert into kitchen (id, name) values (3, 'Brasileira');
insert into kitchen (id, name) values (4, 'Argentina');

insert into state (id, name) values (1, 'RS');

insert into city (id, name, state_id) values (1, 'Esteio', 1);
insert into city (id, name, state_id) values (2, 'Sapucaia do Sul', 1);

insert into restaurant (name, shipping_fee, kitchen_id, address_city_id, address_zip_code, address_road, address_number, address_complement, register_date, update_date) values ('Bar 30', 0, 3, 1, '93200000', 'Rua Das Oliveiras', '220', 'Casa 3', utc_timestamp, utc_timestamp);
insert into restaurant (name, shipping_fee, kitchen_id, register_date, update_date) values ('Thai Gourmet', 10, 1, utc_timestamp, utc_timestamp);
insert into restaurant (name, shipping_fee, kitchen_id, register_date, update_date) values ('Thai Delivery', 9.50, 1, utc_timestamp, utc_timestamp);
insert into restaurant (name, shipping_fee, kitchen_id, register_date, update_date) values ('Tuk Tuk Comida Indiana', 15, 2, utc_timestamp, utc_timestamp);
insert into restaurant (id, name, shipping_fee, kitchen_id, register_date, update_date) values (5, 'Java Steakhouse', 12, 3, utc_timestamp, utc_timestamp);
insert into restaurant (id, name, shipping_fee, kitchen_id, register_date, update_date) values (6, 'Lanchonete do Tio Sam', 11, 4, utc_timestamp, utc_timestamp);
insert into restaurant (id, name, shipping_fee, kitchen_id, register_date, update_date) values (7, 'Bar da Maria', 6, 4, utc_timestamp, utc_timestamp);


insert into payment (id, description) values (1, 'Cart??o de Cr??dito');
insert into payment (id, description) values (2, 'Cart??o de D??bito');
insert into payment (id, description) values (3, 'Dinheiro');
insert into payment (id, description) values (4, 'Pix');

insert into restaurant_payment (restaurant_id, payment_id) values (1, 1), (1, 2), (1, 3), (2, 3), (3, 2), (3, 3), (4, 1), (4, 2), (5, 1), (5, 2), (6, 3);

insert into product (name, description, price, active, restaurant_id) values ('Porco com molho agridoce', 'Deliciosa carne su??na ao molho especial', 78.90, 1, 1);
insert into product (name, description, price, active, restaurant_id) values ('Camar??o tailand??s', '16 camar??es grandes ao molho picante', 110, 1, 1);

insert into product (name, description, price, active, restaurant_id) values ('Salada picante com carne grelhada', 'Salada de folhas com cortes finos de carne bovina grelhada e nosso molho especial de pimenta vermelha', 87.20, 1, 2);

insert into product (name, description, price, active, restaurant_id) values ('Garlic Naan', 'P??o tradicional indiano com cobertura de alho', 21, 1, 3);
insert into product (name, description, price, active, restaurant_id) values ('Murg Curry', 'Cubos de frango preparados com molho curry e especiarias', 43, 1, 3);

insert into product (name, description, price, active, restaurant_id) values ('Bife Ancho', 'Corte macio e suculento, com dois dedos de espessura, retirado da parte dianteira do contrafil??', 79, 1, 4);
insert into product (name, description, price, active, restaurant_id) values ('T-Bone', 'Corte muito saboroso, com um osso em formato de T, sendo de um lado o contrafil?? e do outro o fil?? mignon', 89, 1, 4);

insert into product (name, description, price, active, restaurant_id) values ('Sandu??che X-Tudo', 'Sandub??o com muito queijo, hamburger bovino, bacon, ovo, salada e maionese', 19, 1, 5);

insert into product (name, description, price, active, restaurant_id) values ('Espetinho de Cupim', 'Acompanha farinha, mandioca e vinagrete', 8, 1, 6);

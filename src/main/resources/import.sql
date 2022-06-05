insert into kitchen (id, name) values (1, 'Tailandesa');
insert into kitchen (id, name) values (2, 'Indiana');
insert into kitchen (id, name) values (3, 'Brasileira');

insert into state (id, name) values (1, 'RS');

insert into city (id, name, state_id) values (1, 'Esteio', 1);
insert into city (id, name, state_id) values (2, 'Sapucaia do Sul', 1);

insert into restaurant (name, shipping_fee, kitchen_id, address_city_id, address_zip_code, address_road, address_number, address_complement, register_date, update_date) values ('Bar 30', 0, 3, 1, '93200000', 'Rua Das Oliveiras', '220', 'Casa 3', utc_timestamp, utc_timestamp);
insert into restaurant (name, shipping_fee, kitchen_id, register_date, update_date) values ('Thai Gourmet', 10, 1, utc_timestamp, utc_timestamp);
insert into restaurant (name, shipping_fee, kitchen_id, register_date, update_date) values ('Thai Delivery', 9.50, 1, utc_timestamp, utc_timestamp);
insert into restaurant (name, shipping_fee, kitchen_id, register_date, update_date) values ('Tuk Tuk Comida Indiana', 15, 2, utc_timestamp, utc_timestamp);


insert into payment (id, description) values (1, 'Cartão de Crédito');
insert into payment (id, description) values (2, 'Cartão de Débito');
insert into payment (id, description) values (3, 'Dinheiro');
insert into payment (id, description) values (4, 'Pix');

insert into restaurant_payment (restaurant_id, payment_id) values (1, 1), (1, 2), (1, 3), (1, 4), (2, 3), (3, 2), (3, 3);


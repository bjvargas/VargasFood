insert into kitchen (id, name) values (1, 'Tailandesa');
insert into kitchen (id, name) values (2, 'Indiana');
insert into restaurant (name, shipping_fee, kitchen_id) values ('Thai Gourmet', 10, 1);
insert into restaurant (name, shipping_fee, kitchen_id) values ('Thai Delivery', 9.50, 1);
insert into restaurant (name, shipping_fee, kitchen_id) values ('Tuk Tuk Comida Indiana', 15, 2);

insert into state (id, name) values (1, 'RS');
insert into city (id, name, state_id) values (1, 'Esteio', 1);
insert into city (id, name, state_id) values (2, 'Sapucaia do Sul', 1);

insert into payment (id, description) values (1, 'Cartão de Crédito');
insert into payment (id, description) values (2, 'Cartão de Débito');
insert into payment (id, description) values (3, 'Dinheiro');
insert into payment (id, description) values (4, 'Pix');


insert into restaurant_payment (restaurant_id, payment_id) values (1, 1), (1, 2), (1, 3), (1, 4), (2, 3), (3, 2), (3, 3);


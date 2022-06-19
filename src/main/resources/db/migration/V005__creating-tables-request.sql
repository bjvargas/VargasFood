create table request (
    id bigint not null auto_increment,
    subtotal decimal(10,2) not null,
    shippingFee decimal(10,2) not null,
    total_price decimal(10,2) not null,

    restaurant_id bigint not null,
    user_customer_id bigint not null,
    payment_id bigint not null,

    address_city_id bigint(20) not null,
    address_zip_code varchar(9) not null,
    address_road varchar(100) not null,
    address_number varchar(20) not null,
    address_complement varchar(60) null,

    status varchar(10) not null,
    register_date datetime not null,
    confirmation_date datetime null,
    cancel_date datetime null,
    delivery_date datetime null,

    primary key (id),

    constraint fk_request_address_city foreign key (address_city_id) references city (id),
    constraint fk_request_restaurant foreign key (restaurant_id) references restaurant (id),
    constraint fk_request_user_customer foreign key (user_customer_id) references user (id),
    constraint fk_request_payment foreign key (payment_id) references payment (id)
) engine=InnoDB default charset=utf8;

create table item_request (
    id bigint not null auto_increment,
    quantity smallint(6) not null,
    unity_price decimal(10,2) not null,
    total_price decimal(10,2) not null,
    obs varchar(255) null,
    request_id bigint not null,
    product_id bigint not null,

    primary key (id),
    unique key uk_item_request_product (request_id, product_id),

    constraint fk_item_request_request foreign key (request_id) references request (id),
    constraint fk_item_request_product foreign key (product_id) references product (id)
) engine=InnoDB default charset=utf8;
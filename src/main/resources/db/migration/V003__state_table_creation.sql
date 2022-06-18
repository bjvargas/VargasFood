create table city (
                      id bigint not null auto_increment,
                      name varchar(80) not null,
                      state varchar(80) not null,

                      primary key (id)
) engine=InnoDB default charset=utf8;

create table state (
                        id bigint not null auto_increment,
                        name varchar(80) not null,

                        primary key (id)
) engine=InnoDB default charset=utf8;

alter table city add column state_id bigint not null;

update city c set c.state_id = (select e.id from state e where e.name = c.state);

alter table city add constraint fk_city_state
    foreign key (state_id) references state (id);

alter table city drop column state;

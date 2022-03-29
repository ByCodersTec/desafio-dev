create table if not exists core.operation_type (
    id integer primary key,
    description text not null,
    type char not null
);

create table if not exists core.cnab (
    id serial primary key,
    operation_type_id int not null,
    "date" timestamp not null,
    "value" varchar(10) not null,
    document_id varchar(11) not null,
    card_number varchar(12) not null,
    store_owner varchar(14) not null,
    store_name varchar(19) not null,
    constraint cnab_operation_type_fk foreign key(operation_type_id) references core.operation_type(id)
);
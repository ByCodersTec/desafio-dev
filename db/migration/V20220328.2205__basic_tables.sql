create table if not exists core.operation_type (
    id integer primary key,
    description text not null,
    type char not null
);

create table if not exists core.cnab (
    id serial primary key,
    operation_type_id int not null,
    "date" date not null,
    document_id text not null,
    "value" integer not null,
    card_number text not null,
    store_owner text not null,
    store_name text not null,
    constraint cnab_operation_type_fk foreign key(operation_type_id) references core.operation_type(id)
);
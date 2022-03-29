CREATE TABLE cnabimport (
    id SERIAL PRIMARY KEY,
    type_transaction character varying,
    create_at character varying,
    transaction_value character varying,
    cpf character varying(11),
    credit_card character varying(12),
    time_create character varying,
    owner character varying(14),
    store_name character varying(19)
);

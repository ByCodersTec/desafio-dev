CREATE SCHEMA public AUTHORIZATION postgres;

COMMENT ON SCHEMA public IS 'standard public schema';

-- DROP SEQUENCE public.hibernate_sequence;

CREATE SEQUENCE public.hibernate_sequence
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;

-- Permissions

ALTER SEQUENCE public.hibernate_sequence OWNER TO postgres;
GRANT ALL ON SEQUENCE public.hibernate_sequence TO postgres;
-- public.cnab definition

-- Drop table

-- DROP TABLE public.cnab;

CREATE TABLE public.cnab (
	id int8 NOT NULL,
	cpf varchar(255) NULL,
	datahoratransacao timestamp NULL,
	nomeloja varchar(255) NULL,
	numerocartao varchar(255) NULL,
	representanteloja varchar(255) NULL,
	tipo int4 NULL,
	valor numeric(19, 2) NULL,
	CONSTRAINT cnab_pkey PRIMARY KEY (id)
);

-- Permissions

ALTER TABLE public.cnab OWNER TO postgres;
GRANT ALL ON TABLE public.cnab TO postgres;


-- public.tipotransacao definition

-- Drop table

-- DROP TABLE public.tipotransacao;

CREATE TABLE public.tipotransacao (
	tipo int4 NOT NULL,
	descricao varchar(255) NULL,
	natureza varchar(255) NULL,
	sinal varchar(255) NULL,
	CONSTRAINT tipotransacao_pkey PRIMARY KEY (tipo)
);

-- Permissions

ALTER TABLE public.tipotransacao OWNER TO postgres;
GRANT ALL ON TABLE public.tipotransacao TO postgres;




-- Permissions

GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO public;
INSERT INTO public.tipotransacao
(tipo, descricao, natureza, sinal )
VALUES(1,'Débito', 'Entrada', '+');

INSERT INTO public.tipotransacao
(tipo, descricao, natureza, sinal )
VALUES(2, 'Boleto', 'Saída', '-');

INSERT INTO public.tipotransacao
(tipo, descricao, natureza, sinal )
VALUES(3, 'Financiamento', 'Saída', '-');

INSERT INTO public.tipotransacao
(tipo, descricao, natureza, sinal )
VALUES(4, 'Crédito', 'Entrada', '+');

INSERT INTO public.tipotransacao
(tipo, descricao, natureza, sinal )
VALUES(5,'Recebimento Empréstimo', 'Entrada', '+');

INSERT INTO public.tipotransacao
(tipo, descricao, natureza, sinal )
VALUES(6, 'Vendas', 'Entrada', '+');

INSERT INTO public.tipotransacao
(tipo, descricao, natureza, sinal )
VALUES(7, 'Recebimento TED', 'Entrada', '+');

INSERT INTO public.tipotransacao
(tipo, descricao, natureza, sinal )
VALUES(8, 'Recebimento DOC', 'Entrada', '+');

INSERT INTO public.tipotransacao
(tipo, descricao, natureza, sinal )
VALUES(9,'Aluguel', 'Saída', '-');
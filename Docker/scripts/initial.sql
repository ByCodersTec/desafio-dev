Drop table

DROP TABLE public.cnab;

CREATE TABLE public.cnab (
	cpf varchar(255) NULL,
	datahoratransacao timestamp NULL,
	nomeloja varchar(255) NULL,
	numerocartao varchar(255) NULL,
	representanteloja varchar(255) NULL,
	valor numeric(19, 2) NULL,
	tipo_cnab_id int8 NOT NULL,
	CONSTRAINT cnab_pkey PRIMARY KEY (tipo_cnab_id)
);


ALTER TABLE public.cnab ADD CONSTRAINT fknv4wb2vh062t6ym6ujo7cek9p FOREIGN KEY (tipo_cnab_id) REFERENCES public.tipotransacao(cnab_id);

Drop table

DROP TABLE public.tipotransacao;

CREATE TABLE public.tipotransacao (
	cnab_id int8 NOT NULL,
	descricao varchar(255) NULL,
	natureza varchar(255) NULL,
	sinal varchar(255) NULL,
	CONSTRAINT tipotransacao_pkey PRIMARY KEY (cnab_id)
);

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
VALUES(5,'Recebimento Empreéstimo', 'Entrada', '+');

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
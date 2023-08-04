INSERT INTO public.tipo_transacao(tipo, descricao, natureza, operacao) VALUES (1, 'Débito', 'Entrada', 'SOMAR') ON CONFLICT (tipo) DO NOTHING;
INSERT INTO public.tipo_transacao(tipo, descricao, natureza, operacao) VALUES (2, 'Boleto', 'Saída', 'SUBTRAIR') ON CONFLICT (tipo) DO NOTHING;
INSERT INTO public.tipo_transacao(tipo, descricao, natureza, operacao) VALUES (3, 'Financiamento', 'Saída', 'SUBTRAIR') ON CONFLICT (tipo) DO NOTHING;
INSERT INTO public.tipo_transacao(tipo, descricao, natureza, operacao) VALUES (4, 'Crédito', 'Entrada', 'SOMAR') ON CONFLICT (tipo) DO NOTHING;
INSERT INTO public.tipo_transacao(tipo, descricao, natureza, operacao) VALUES (5, 'Recebimento Empréstimo', 'Entrada', 'SOMAR') ON CONFLICT (tipo) DO NOTHING;
INSERT INTO public.tipo_transacao(tipo, descricao, natureza, operacao) VALUES (6, 'Vendas', 'Entrada', 'SOMAR') ON CONFLICT (tipo) DO NOTHING;
INSERT INTO public.tipo_transacao(tipo, descricao, natureza, operacao) VALUES (7, 'Recebimento TED', 'Entrada', 'SOMAR') ON CONFLICT (tipo) DO NOTHING;
INSERT INTO public.tipo_transacao(tipo, descricao, natureza, operacao) VALUES (8, 'Recebimento DOC', 'Entrada', 'SOMAR') ON CONFLICT (tipo) DO NOTHING;
INSERT INTO public.tipo_transacao(tipo, descricao, natureza, operacao) VALUES (9, 'Aluguel', 'Saída', 'SUBTRAIR') ON CONFLICT (tipo) DO NOTHING;
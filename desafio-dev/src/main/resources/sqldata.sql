INSERT INTO tb_transaction_type (description, transaction_type, signal, dt_insert)
VALUES
    ('Débito', 'Entrada', '+', CURRENT_TIMESTAMP),
    ('Boleto', 'Saída', '-', CURRENT_TIMESTAMP),
    ('Financiamento', 'Saída', '-', CURRENT_TIMESTAMP),
    ('Crédito', 'Entrada', '+', CURRENT_TIMESTAMP),
    ('Recebimento Empréstimo', 'Entrada', '+', CURRENT_TIMESTAMP),
    ('Vendas', 'Entrada', '+', CURRENT_TIMESTAMP),
    ('Recebimento TED', 'Entrada', '+', CURRENT_TIMESTAMP),
    ('Recebimento DOC', 'Entrada', '+', CURRENT_TIMESTAMP),
    ('Aluguel', 'Saída', '-', CURRENT_TIMESTAMP);
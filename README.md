# interview

## Backend

### Observações
> Configurado autoflush: True na session connection, para resolver problema de store_id no bulk insert.
> O correto seria o bulk insert ser realizado por uma task do celery, e não em uma request.

> 

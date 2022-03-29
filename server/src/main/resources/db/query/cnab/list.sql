select
    c.id,
    date,
    document_id,
    value,
    card_number,
    store_owner,
    store_name,
    operation_type_id,
    ot.description operation_type_description,
    ot.type operation_type_type
from
    core.cnab c
    inner join core.operation_type ot on c.operation_type_id = ot.id
limit
    (:SIZE + 1) offset (:PAGE * :SIZE)
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
where
	coalesce(:CARD_NUMBER, '') = ''
	or card_number like concat('%', :CARD_NUMBER)
	and coalesce(:DOCUMENT_ID, '') = ''
	or document_id = :DOCUMENT_ID
limit
	:SIZE offset :PAGE * :SIZE
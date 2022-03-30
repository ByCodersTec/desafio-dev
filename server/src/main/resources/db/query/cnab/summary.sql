with cnabs as (
	select
		*
	from
		core.cnab c
		inner join core.operation_type ot on c.operation_type_id = ot.id
	where
		coalesce(:CARD_NUMBER, '') = ''
		or card_number like concat('%', :CARD_NUMBER)
		and coalesce(:DOCUMENT_ID, '') = ''
		or document_id = :DOCUMENT_ID
)
select
	sum("value") total_value,
	count(1) total_operations
from
	cnabs
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
	sum(
		case
			when "type" = 'I' then "value"
			else -1 * "value"
		end
	) totalValue,
	count(1) totalOperations
from
	cnabs
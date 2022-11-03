<?php

namespace App\Http\Dtos\TypeTransaction;

use Spatie\DataTransferObject\DataTransferObject;

class CreateTypeTransactionDto extends DataTransferObject
{
	public string $transaction_name;
	public string $operation_type;
	public int $transaction_code;

	public function __construct(array $data)
	{
		$this->transaction_name = !empty($data['transaction_name']) ? $data['transaction_name'] : "";
		$this->operation_type = !empty($data['operation_type']) ? $data['operation_type'] : "";
		$this->transaction_code = !empty($data['transaction_code']) ? $data['transaction_code'] : 0;
	}
}

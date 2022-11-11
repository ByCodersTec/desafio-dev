<?php

namespace App\Http\Dtos\CnabFile;

use Spatie\DataTransferObject\DataTransferObject;

class CreateCnabFileDto extends DataTransferObject
{
	public string $transaction_date;
	public float $transaction_value;
	public string $transaction_card;
	public string $transaction_hour;
	public string $type_id;
	public string $company_id;


	public function __construct(array $data)
	{
		$this->transaction_date = !empty($data['transaction_date']) ? $data['transaction_date'] : "";
		$this->transaction_value = !empty($data['transaction_value']) ? $data['transaction_value'] : 0;
		$this->transaction_card = !empty($data['transaction_card']) ? $data['transaction_card'] : "";
		$this->transaction_hour = !empty($data['transaction_hour']) ? $data['transaction_hour'] : "";
		$this->type_id = !empty($data['type_id']) ? $data['type_id'] : "";
		$this->company_id = !empty($data['company_id']) ? $data['company_id'] : "";
	}
}

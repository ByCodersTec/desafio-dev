<?php
namespace App\Http\Dtos\Company;

use Spatie\DataTransferObject\DataTransferObject;

class CreateCompanyDto extends DataTransferObject
{
	public string $legal_person_name;
	public string $person_name;
	public string $person_cpf;

	public function __construct(array $data)
	{
		$this->legal_person_name = !empty($data['legal_person_name']) ? $data['legal_person_name'] : "";
		$this->person_name = !empty($data['person_name']) ? $data['person_name'] : "";
		$this->person_cpf = !empty($data['person_cpf']) ? $data['person_cpf'] : "";
	}
}

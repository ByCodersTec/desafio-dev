<?php

namespace App\Repositories\Interfaces\Company;

use App\Http\Dtos\Company\CreateCompanyDto;
use App\Models\Company;


interface CompanyRepositoryInterface
{
	public function create(CreateCompanyDto $createCompanyDto): ?Company;
	public function findCompanyByCpfPerson(string $cpf): ?Company;
}

<?php

namespace App\Repositories\Interfaces\Company;

use App\Http\Dtos\Company\CreateCompanyDto;
use App\Models\Company;
use Illuminate\Database\Eloquent\Collection;

interface CompanyRepositoryInterface
{
	public function create(CreateCompanyDto $createCompanyDto): ?Company;
	public function findCompanyByCpfPerson(string $cpf): ?Company;
	public function indexCompanyWithCnabFile(): ?Collection;
	public function showCompanyWithCnabFile(string $id): ?Company;
}
